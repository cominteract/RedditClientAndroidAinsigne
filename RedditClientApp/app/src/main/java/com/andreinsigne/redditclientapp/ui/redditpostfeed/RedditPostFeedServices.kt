package com.andreinsigne.redditclientapp.ui.redditpostfeed

import com.andreinsigne.redditclientapp.R
import com.andreinsigne.redditclientapp.manager.APIManager
import com.andreinsigne.redditclientapp.manager.AuthManager

/**
 * RedditPostFeedServices for implementing the services needed for the presenter.
 **/
class RedditPostFeedServices(
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
     * RedditPostFeedView reference of the RedditPostFeedFragment
     * as RedditPostFeedView type. Must be weak
     **/
    var contract: RedditPostFeedContract? = null


}
