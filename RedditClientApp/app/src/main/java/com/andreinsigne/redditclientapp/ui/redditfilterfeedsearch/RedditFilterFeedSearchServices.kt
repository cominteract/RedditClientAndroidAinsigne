package com.andreinsigne.redditclientapp.ui.redditfilterfeedsearch

import com.andreinsigne.redditclientapp.R
import com.andreinsigne.redditclientapp.manager.APIManager
import com.andreinsigne.redditclientapp.manager.AuthManager

/**
 * RedditFilterFeedSearchServices for implementing the services needed for the presenter.
 **/
class RedditFilterFeedSearchServices(
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
     * RedditFilterFeedSearchView reference of the RedditFilterFeedSearchFragment
     * as RedditFilterFeedSearchView type. Must be weak
     **/
    var contract: RedditFilterFeedSearchContract? = null


}
