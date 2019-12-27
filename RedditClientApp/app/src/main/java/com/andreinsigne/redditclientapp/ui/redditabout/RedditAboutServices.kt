package com.andreinsigne.redditclientapp.ui.redditabout

import android.util.Log
import com.andreinsigne.redditclientapp.R
import com.andreinsigne.redditclientapp.manager.APIManager
import com.andreinsigne.redditclientapp.manager.AuthManager
import com.andreinsigne.redditclientapp.manager.MeRetrieved
import com.andreinsigne.redditclientapp.model.Me
import com.andreinsigne.redditclientapp.utils.APIEndpoint

/**
 * RedditAboutServices for implementing the services needed for the presenter.
 **/
class RedditAboutServices(
    apiManager: APIManager,
    authManager: AuthManager
) {
    /**
     * apiManager used in consuming the api related to data whether mock or from aws to be initialized
     **/
    var apiManager: APIManager = apiManager
    /**
     * authManager used in consuming the authentication api whether mock or from aws to be initialized
     **/
    var authManager: AuthManager = authManager
    /**
     * RedditAboutView reference of the RedditAboutFragment
     * as RedditAboutView type. Must be weak
     **/
    var contract: RedditAboutContract? = null

    /// startInfo starts the api to get the info about the user using the MeRetrieved closure callback
    ///
    /// - Parameter user: as String the user to get the info about
    fun startInfo(user : String)
    {
        val meretrieved = object : MeRetrieved {
            override fun didRetrievedError(error: Error, revoked: Boolean) {
                Log.d(" Level up "," Level up wrong")
            }

            override fun didRetrieveMe(me: Me) {
                Log.d(" Level up "," Level up ")
                contract?.retrievedMe(me)
            }
        }
        apiManager.startAPI(APIEndpoint.About,  user, false,  meretrieved)
    }
}
