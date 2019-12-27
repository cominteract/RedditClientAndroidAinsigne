package com.andreinsigne.redditclientapp.ui.redditfeedview

import android.util.Log
import com.andreinsigne.redditclientapp.R
import com.andreinsigne.redditclientapp.manager.APIManager
import com.andreinsigne.redditclientapp.manager.AuthManager
import com.andreinsigne.redditclientapp.manager.ListingRetrieved
import com.andreinsigne.redditclientapp.manager.MeRetrieved
import com.andreinsigne.redditclientapp.model.FeedChildrenListing
import com.andreinsigne.redditclientapp.model.FeedDataListing
import com.andreinsigne.redditclientapp.model.FeedListing
import com.andreinsigne.redditclientapp.model.Me
import com.andreinsigne.redditclientapp.ui.redditfeed.RedditFeedContract
import com.andreinsigne.redditclientapp.utils.APIEndpoint
import com.andreinsigne.redditclientapp.utils.Config
import com.andreinsigne.redditclientapp.utils.EndPoints

/**
 * RedditFeedViewServices for implementing the services needed for the presenter.
 **/
class RedditFeedViewServices(
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
     * RedditFeedViewView reference of the RedditFeedViewFragment
     * as RedditFeedViewView type. Must be weak
     **/
    var contract: RedditFeedViewContract? = null



}
