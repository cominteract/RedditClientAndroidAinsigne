package com.andreinsigne.redditclientapp.ui.redditcommunity

import android.util.Log
import com.andreinsigne.redditclientapp.R
import com.andreinsigne.redditclientapp.manager.APIManager
import com.andreinsigne.redditclientapp.manager.AuthManager
import com.andreinsigne.redditclientapp.model.FeedListing
import com.andreinsigne.redditclientapp.utils.APIEndpoint
import com.andreinsigne.redditclientapp.utils.Config
import com.andreinsigne.redditclientapp.manager.ListingRetrieved as ListingRetrieved1

/**
 * RedditCommunityServices for implementing the services needed for the presenter.
 **/
class RedditCommunityServices(
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
     * RedditCommunityView reference of the RedditCommunityFragment
     * as RedditCommunityView type. Must be weak
     **/
    var contract: RedditCommunityContract? = null

/// startListing starts the api process to retrieve the subreddits subscribed using the ListingRetrieved closure callback
    fun startListing() {
        val retrieved = object : ListingRetrieved1 {
            override fun didRetrievedError(error: Error, revoked: Boolean) {

            }

            override fun didRetrievedListing(feedListing: FeedListing, apiEndpoint: APIEndpoint) {
                if(apiEndpoint == APIEndpoint.MineSubreddit && feedListing.data != null && feedListing.data!!.children != null) {
                    contract?.retrievedSubreddits(feedListing.data!!.children!!.toList())
                    Config.updateRefreshedCom("Yes")
                }
                if(apiEndpoint == APIEndpoint.SubPopular) {
                    contract?.retrievedPopular(feedListing)
                }
                if(apiEndpoint == APIEndpoint.SubAll) {
                    contract?.retrievedAll(feedListing)
                }
            }

        }

        Config.updateRefreshedCom("Not")
        if(authManager.isLogged())
        {
            apiManager.startAPI(APIEndpoint.MineSubreddit, null, false, retrieved)
        }
        apiManager.startAPI(APIEndpoint.SubPopular, null, false, retrieved)
        apiManager.startAPI(APIEndpoint.SubAll, null, false, retrieved)
    }
}
