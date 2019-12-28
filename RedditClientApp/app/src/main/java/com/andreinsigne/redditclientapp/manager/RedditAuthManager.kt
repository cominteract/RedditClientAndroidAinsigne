package com.andreinsigne.redditclientapp.manager

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import com.andreinsigne.redditclientapp.model.TokenResponse
import com.andreinsigne.redditclientapp.utils.Config
import com.andreinsigne.redditclientapp.utils.oneHourPassed
import com.andreinsigne.redditclientapp.utils.toDate
import kotlinx.serialization.json.Json
import kotlinx.serialization.toUtf8Bytes
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.io.DataOutputStream
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets
import java.util.*


/**
 * Implements the authmanager abstract class (which derives from the authprotocol) and backend
 * for authenticating
 **/
class RedditAuthManager : AuthManager() {

    override fun startAuth(authRetrieve: TokenRetrieve) {
        Config.context.tokenRetrieve = authRetrieve
        val commaSeparatedScopeString = Config.scopes.joinToString(",")
        val urlString =  "https://www.reddit.com/api/v1/authorize.compact?client_id=" + Config.client_id + "&response_type=code&state=" + Config.state +
                "&redirect_uri=" + Config.redirect_uri + "&duration=permanent&scope=" + commaSeparatedScopeString
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(urlString)
        )
        Config.context.startActivity(intent)
    }

    override fun isLogged(): Boolean {
        Log.d(" Is it logged ? "," Is it logged AuthToken ${Config.getOauthToken() != null}  ----" +
                "  --- RefreshDate " +
                "${Config.getRefreshDate()!= null}----" + "One hour passed ${Config.getRefreshDate()?.oneHourPassed()}")
        return Config.getOauthToken() != null && Config.getRefreshDate()!= null
                && !Config.getRefreshDate()!!.oneHourPassed()
        //Config.getRefreshDate()?.toDate() > Date()
    }

    override fun refreshAuth(authRetrieve: TokenRetrieve) {
        if(Config.getRefreshToken() != null) {
            val connection = URL("${Config.baseAuthURL}/api/v1/access_token").openConnection() as HttpURLConnection

            val param = "grant_type=refresh_token&refresh_token=${Config.getRefreshToken()}"
            val postData: ByteArray = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                param.toByteArray(StandardCharsets.UTF_8)
            } else {
                param.toUtf8Bytes()
            }

            val client = "${Config.client_id}:"
            val authData: ByteArray = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                client.toByteArray(StandardCharsets.UTF_8)
            } else {
                client.toUtf8Bytes()
            }
            val base64Encoded = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Base64.getEncoder().encodeToString(authData)
            } else {
                android.util.Base64.encodeToString(authData, android.util.Base64.DEFAULT)
            }
            connection.setRequestProperty("User-Agent", Config.userAgent)
            connection.addRequestProperty("Authorization", "Basic $base64Encoded")
            connection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
            connection.requestMethod = "POST"
            try {

                var result: String = ""
                doAsync {
                    val outputStream = DataOutputStream(connection.outputStream)
                    outputStream.write(postData)
                    outputStream.flush()
                    connection.inputStream.bufferedReader().use { reader ->
                        result = reader.readText()
                        val json = Json.nonstrict
                        Log.d(" Basic Auth Result ", " $result $param")
                        uiThread {
                            authRetrieve.didRetrieveToken(json.parse(TokenResponse.serializer(), result))
                        }
                    }
                }
            } catch (exception: Exception) {

            }
        }
    }
}
