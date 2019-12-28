package com.andreinsigne.redditclientapp.utils

import android.content.Context
import android.os.Build
import android.preference.PreferenceManager
import kotlinx.serialization.toUtf8Bytes
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets
import java.util.*
import java.util.Base64.getEncoder
import kotlin.collections.ArrayList
import android.R.attr.data
import android.util.Log
import com.andreinsigne.redditclientapp.manager.TokenRetrieve
import com.andreinsigne.redditclientapp.model.TokenResponse
import kotlinx.serialization.json.Json
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import android.R.attr.password
import com.andreinsigne.redditclientapp.ui.main.MainActivity
import java.io.DataOutputStream
import java.util.Base64.getEncoder




class Config {
    companion object{
        val baseAuthURL = "https://www.reddit.com"
        val baseAPIURL = "https://oauth.reddit.com"
        val redirect_uri = "rditai://response"
        val client_id = "Q6SUp3vgRqczhQ"
        val scopes = arrayOf("identity", "edit", "flair", "history", "modconfig", "modflair", "modlog", "modposts", "modwiki", "mysubreddits", "privatemessages", "read", "report", "save", "submit", "subscribe", "vote", "wikiedit", "wikiread")
        val state = "aicodebase"
        val recentsearch_key = "recentsearch"
        val interest_key = "interest"
        val token_key = "oauthtoken"
        val username_key = "username"
        val reftoken_key = "refoauthtoken"
        val refdate_key = "refdate"
        val ref_key = "refkey"
        val refcom_key = "refcomkey"
        val refinbox_key = "refinboxkey"
        val version: String = "0.0.0"
        val bundleIdentifier: String = "com.andreinsigne.redditclientapp"
        val developerName: String = "KleinBarbarossa"
        var userAgent: String = "ios:$bundleIdentifier:v$version(by /u/$developerName)"


        lateinit var context: MainActivity





        /// updates the recent search results
        ///
        /// - Parameters:
        ///   - value: new value to update as [String]
        ///   - key: the identifier from the Keys as String
        fun updateRecentSearched(value : ArrayList<String>)
        {
            var pref = (PreferenceManager
                .getDefaultSharedPreferences(context))
            val editor = pref!!.edit()
            var recents = ""
            for (i in value)
            {
                recents += "$i,"
            }
            recents.removeRange(recents.length - 1 , recents.length - 1)
            editor.putString(recentsearch_key, recents)
            editor.apply()
        }

        /// returns the recent search results saved
        ///
        /// - Returns: recents as String after saving
        fun getRecentsSearch() : Array<String>?
        {
            var pref = (PreferenceManager
                .getDefaultSharedPreferences(context))
            return pref.getString(recentsearch_key,null)?.split(",")?.toTypedArray()
        }

        /// updates the new interest
        ///
        /// - Parameters:
        ///   - value: new value to update as [String]
        ///   - key: the identifier from the Keys as String
        fun updateInterest(value : ArrayList<String>)
        {
            var pref = (PreferenceManager
                .getDefaultSharedPreferences(context))
            val editor = pref!!.edit()
            var interests = ""
            for (i in value)
            {
                interests += "$i,"
            }
            interests.removeRange(interests.length - 1 , interests.length - 1)
            editor.putString(interest_key, interests)
            editor.apply()
        }

        /// returns the interest results saved
        ///
        /// - Returns: interests as String after saving
        fun getInterests() : Array<String>?
        {
            var pref = (PreferenceManager
                .getDefaultSharedPreferences(context))
            return pref.getString(interest_key,null)?.split(",")?.toTypedArray()
        }

        /// updates the new username
        ///
        /// - Parameters:
        ///   - value: new value to update as String
        ///   - key: the identifier from the Keys as String
        fun updateUser(value : String)
        {
            var pref = (PreferenceManager
                .getDefaultSharedPreferences(context))
            val editor = pref!!.edit()
            editor.putString(username_key,value)
            editor.apply()
        }

        /// returns the username when saved
        ///
        /// - Returns: username as String after saving
        fun getUser() : String?
        {
            var pref = (PreferenceManager
                .getDefaultSharedPreferences(context))
            return pref.getString(username_key,null)
        }

        /// updates the new token
        ///
        /// - Parameters:
        ///   - value: new value to update as String
        ///   - key: the identifier from the Keys as String
        fun updateToken(value: String)
        {
            var pref = (PreferenceManager
                .getDefaultSharedPreferences(context))
            val editor = pref!!.edit()
            editor.putString(token_key,value)
            editor.apply()
        }

        /// returns the oauth token when saved
        ///
        /// - Returns: oauth token as String after saving
        fun getOauthToken() : String?
        {
            var pref = (PreferenceManager
                .getDefaultSharedPreferences(context))
            return pref.getString(token_key,null)
        }

        /// updates the new refresh token
        ///
        /// - Parameters:
        ///   - value: new value to update as String
        ///   - key: the identifier from the Keys as String
        fun updateRefreshToken(value: String)
        {
            var pref = (PreferenceManager
                .getDefaultSharedPreferences(context))
            val editor = pref!!.edit()
            editor.putString(reftoken_key,value)
            editor.apply()
        }

        /// returns the oauth ref token when saved
        ///
        /// - Returns: oauth ref token as String after saving
        fun getRefreshToken() : String?
        {
            var pref = (PreferenceManager
                .getDefaultSharedPreferences(context))
            return pref.getString(reftoken_key,null)
        }

        /// updates the refreshDate
        ///
        /// - Parameters:
        ///   - value: new value to update as String
        ///   - key: the identifier from the Keys as String
        fun updateRefreshDate(value: String)
        {
            var pref = (PreferenceManager
                .getDefaultSharedPreferences(context))
            val editor = pref!!.edit()
            editor.putString(refdate_key,value)
            editor.apply()
        }

        /// returns the refreshDate when saved
        ///
        /// - Returns: oauth ref token as String after saving
        fun getRefreshDate() : String?
        {
            var pref = (PreferenceManager
                .getDefaultSharedPreferences(context))
            return pref.getString(refdate_key,null)
        }


        /// updates the refreshavailability
        ///
        /// - Parameters:
        ///   - value: new value to update as String
        ///   - key: the identifier from the Keys as String
        fun updateRefreshed(value: String)
        {
            var pref = (PreferenceManager
                .getDefaultSharedPreferences(context))
            val editor = pref!!.edit()
            editor.putString(ref_key,value)
            editor.apply()
        }

        /// returns the refreshavailability
        ///
        /// - Returns: oauth ref token as String after saving
        fun getRefreshed() : String?
        {
            var pref = (PreferenceManager
                .getDefaultSharedPreferences(context))
            return pref.getString(ref_key,null)
        }

        /// updates the refresh com availability
        ///
        /// - Parameters:
        ///   - value: new value to update as String
        ///   - key: the identifier from the Keys as String
        fun updateRefreshedCom(value: String)
        {
            var pref = (PreferenceManager
                .getDefaultSharedPreferences(context))
            val editor = pref!!.edit()
            editor.putString(refcom_key,value)
            editor.apply()
        }

        /// returns the refresh com availability
        ///
        /// - Returns: oauth ref token as String after saving
        fun getRefreshedCom() : String?
        {
            var pref = (PreferenceManager
                .getDefaultSharedPreferences(context))
            return pref.getString(refcom_key,null)
        }

        /// updates the refresh inbox availability
        ///
        /// - Parameters:
        ///   - value: new value to update as String
        ///   - key: the identifier from the Keys as String
        fun updateRefreshedInbox(value: String)
        {
            var pref = (PreferenceManager
                .getDefaultSharedPreferences(context))
            val editor = pref!!.edit()
            editor.putString(refinbox_key,value)
            editor.apply()
        }

        /// returns the refresh inbox availability
        ///
        /// - Returns: oauth ref token as String after saving
        fun getRefreshedInbox() : String?
        {
            var pref = (PreferenceManager
                .getDefaultSharedPreferences(context))
            return pref.getString(refinbox_key,null)
        }

        /// getToken retrieves the token through a closure callback using the code
        ///
        /// - Parameters:
        ///   - code: as String to be used in retrieving the token
        ///   - auth: as TokenRetrieve to return whether retrieving the token is successful or not
        fun getToken(code : String, tokenRetrieve: TokenRetrieve)
        {
            val connection = URL("$baseAuthURL/api/v1/access_token").openConnection() as HttpURLConnection

            val param = "grant_type=authorization_code&code=$code&redirect_uri=$redirect_uri"
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
                getEncoder().encodeToString(authData)
            } else {
                android.util.Base64.encodeToString(authData, android.util.Base64.DEFAULT)
            }
            connection.setRequestProperty("User-Agent", Config.userAgent)
            connection.addRequestProperty("Authorization", "Basic $base64Encoded")
            connection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
            connection.requestMethod = "POST"
            try {

                var result : String = ""
                doAsync {
                    val outputStream = DataOutputStream(connection.outputStream)
                    outputStream.write(postData)
                    outputStream.flush()
                    connection.inputStream.bufferedReader().use { reader ->



                        result = reader.readText()
                        val json = Json.nonstrict
                        Log.d(" Basic Auth Result ", " $result")
                        uiThread {
                            tokenRetrieve.didRetrieveToken(json.parse(TokenResponse.serializer(), result))
                        }

                    }
                }
            } catch (exception: Exception) {

            }
        }


    }
}