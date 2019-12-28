package com.andreinsigne.redditclientapp.manager

import android.os.Build
import kotlinx.serialization.toUtf8Bytes
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets
import java.util.*
import android.icu.lang.UCharacter.GraphemeClusterBreak.V
import android.util.Log
import com.andreinsigne.redditclientapp.model.TokenResponse
import com.andreinsigne.redditclientapp.utils.*
import kotlinx.serialization.json.Json
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.io.DataOutputStream


/**
 * Implements the apimanager abstract class (which derives from the apiprotocol) and backend
 * for fetching the data
 **/
class RedditAPIManager : APIManager() {


    /// getPostField returns the post type
    ///
    /// - Returns: as String the post type to be used as parameter in sending POST Requests via submit endpoint
    fun getPostField() : String
    {
        return when(postType) {
            PostType.Image, PostType.Video -> "media"
            PostType.Text -> "self"
            else -> "url"
        }
    }

    fun paramToJson(params : String) : HashMap<String,String>?
    {
        val fields = arrayOf("api_type","kind","resubmit","sendreplies","sr","title",getPostField())


        val parameters = HashMap<String,String>()
        if(params.split(",").size != 7)
            return null
        for((count, element) in params.split(",").withIndex())
        {
            parameters[fields[count]] = element
        }
        return parameters
    }

    override fun startAPI(endPoint: APIEndpoint, input: String?, isJson: Boolean, retrieved: ErrorRetrieved) {

        val client = "${Config.client_id}:"
        var postData : ByteArray? = null
        val endpointString = EndPoints.endpoint(endPoint, input)
        val method = EndPoints.method(endPoint)


        var url = ""
        lateinit var connection : HttpURLConnection
        if(!isJson){
            url = "${Config.baseAPIURL}${endpointString}"

            if (method == "POST" && input != null){
                if (!paramToJson(input).isNullOrEmpty()){
                    //request?.httpBody = parameter.URLQuery.data(using: .utf8)
                    val param = paramToJson(input)?.URLQuery()
                    postData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        param?.toByteArray(StandardCharsets.UTF_8)
                    } else {
                        param?.toUtf8Bytes()
                    }
                }
            }
            connection = URL(url).openConnection() as HttpURLConnection
            connection.setRequestProperty("User-Agent", Config.userAgent)
            if (Config.getOauthToken() != null){
                connection.addRequestProperty("Authorization", "bearer ${Config.getOauthToken()}")

                //request?.addValue("bearer \(authToken)", forHTTPHeaderField: "Authorization")
            }
        }
        else{
            url = if (endPoint != APIEndpoint.Default){
                "${Config.baseAuthURL}${endpointString}.json"
                //request = URLRequest(url: URL(string: "\(Config.baseAuthURL)\(endpointString).json")!)
            } else{
                EndPoints.endpoint(APIEndpoint.Default, null)
                //request = URLRequest(url: URL(string: EndPoints.endpoint(endpoint: APIEndpoint.Default, input:
                //nil))!)
            }
            connection = URL(url).openConnection() as HttpURLConnection
            connection.setRequestProperty("User-Agent", Config.userAgent)
        }
        connection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
        connection.requestMethod = method
        doAsync {

            if (postData != null) {
                val outputStream = DataOutputStream(connection.outputStream)
                outputStream.write(postData)
                outputStream.flush()
            }

            var result = ""
            connection.inputStream.bufferedReader().use { reader ->
                result = reader.readText()
                ClosureResults.apiResults(endPoint, result, retrieved)
            }
        }
    }

}
