package com.andreinsigne.redditclientapp.ui.redditdetails

import android.util.Log
import com.andreinsigne.redditclientapp.R
import com.andreinsigne.redditclientapp.manager.*
import com.andreinsigne.redditclientapp.model.FeedListing
import com.andreinsigne.redditclientapp.model.Rules
import com.andreinsigne.redditclientapp.model.Subreddit
import com.andreinsigne.redditclientapp.utils.APIEndpoint

/**
 * RedditDetailsServices for implementing the services needed for the presenter.
 **/
class RedditDetailsServices(
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
     * RedditDetailsView reference of the RedditDetailsFragment
     * as RedditDetailsView type. Must be weak
     **/
    var contract: RedditDetailsContract? = null

    /// startLPosts starts the api process to retrieve the posts and info about the subreddit using the ListingRetrieved, RulesRetrieved and SubredditRetrieved closure callback
    ///
    /// - Parameter sr: as String the subreddit as parameter
    fun startPosts(sr : String)
    {
        val retrieved = object : ListingRetrieved {
            override fun didRetrievedError(error: Error, revoked: Boolean) {

            }

            override fun didRetrievedListing(feedListing: FeedListing, apiEndpoint: APIEndpoint) {
                contract?.retrievedPosts(feedListing, apiEndpoint)
            }

        }


        val subretrieved = object : SubredditRetrieved {
            override fun didRetrievedError(error: Error, revoked: Boolean) {
            }

            override fun didRetrieveSubreddit(subreddit: Subreddit, apiEndpoint: APIEndpoint) {
                contract?.retrievedAboutSubreddit(subreddit , apiEndpoint)
            }

        }

        apiManager.startAPI(APIEndpoint.Input, sr , false, retrieved)
        apiManager.startAPI(APIEndpoint.AbtSub,sr, false, subretrieved)


        val ruleretrieved = object : RulesRetrieved {
            override fun didRetrievedError(error: Error, revoked: Boolean) {
                Log.d(" AI Error ", " AI Error ")
            }

            override fun didRetrieveRules(rules: Rules, apiEndpoint: APIEndpoint) {
                contract?.retrievedAboutSubRules(rules)
            }

        }
        apiManager.startAPI( APIEndpoint.AbtSubRules, sr, false, ruleretrieved)

    }
}
