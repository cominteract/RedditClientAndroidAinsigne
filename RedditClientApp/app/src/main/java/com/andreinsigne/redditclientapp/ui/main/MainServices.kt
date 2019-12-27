package com.andreinsigne.redditclientapp.ui.main

import android.util.Log
import com.andreinsigne.redditclientapp.R
import com.andreinsigne.redditclientapp.manager.APIManager
import com.andreinsigne.redditclientapp.manager.AuthManager
import com.andreinsigne.redditclientapp.manager.TokenRetrieve
import com.andreinsigne.redditclientapp.model.TokenResponse
import com.andreinsigne.redditclientapp.utils.Config
import com.andreinsigne.redditclientapp.utils.toDate
import com.andreinsigne.redditclientapp.utils.toStringFormat
import java.util.*

/**
 * MainServices for implementing the services needed for the presenter.
 **/
class MainServices(
    apiManager: APIManager,
    authManager: AuthManager
)  {
    /**
     * apiManager used in consuming the api related to data whether mock or from aws to be initialized
     **/
    var apiManager: APIManager = apiManager
    /**
     * authManager used in consuming the authentication api whether mock or from aws to be initialized
     **/
    var authManager: AuthManager = authManager
    /**
     * RedditAuthSignView reference of the RedditAuthSignFragment
     * as RedditAuthSignView type. Must be weak
     **/
    var contract: MainContract? = null

    fun isLogged() : Boolean
    {
        return authManager.isLogged()
    }

    fun refreshAuth()
    {
        var tokenRetrieve = object : TokenRetrieve {
            override fun didRetrieveToken(tokenResponse: TokenResponse) {
                if(tokenResponse.access_token != null)
                    Config.updateToken(tokenResponse.access_token!!)
                Log.d(" Updatng refresh token "," Updatng refresh token ${tokenResponse.access_token}")
                if(tokenResponse.refresh_token != null)
                    Config.updateRefreshToken(tokenResponse.refresh_token!!)
                val cal = Calendar.getInstance()
                cal.time = Date()
                if(cal.get(Calendar.HOUR_OF_DAY) + 1 == 24)
                    cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) + 1)
                cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) + 1)
                Log.d(" Updating from ${Config.getRefreshDate()}"," ${cal.time.toStringFormat()}")
                Config.updateRefreshDate(cal.time.toStringFormat())

                contract?.tokenRefreshed()
            }
            override fun didEncounterError(error: Error, revoked: Boolean) {

            }
        }
        if (Config.getOauthToken() != null && !authManager.isLogged())
        {
            authManager.refreshAuth(tokenRetrieve)
        }
        else
        {
            Log.d(" Not refreshing "," Not refreshing ${authManager.isLogged()}")
        }

    }

}
