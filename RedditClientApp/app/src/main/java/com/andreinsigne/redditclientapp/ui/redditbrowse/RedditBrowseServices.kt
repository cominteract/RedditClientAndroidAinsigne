package com.andreinsigne.redditclientapp.ui.redditbrowse

import com.andreinsigne.redditclientapp.R
import com.andreinsigne.redditclientapp.manager.APIManager
import com.andreinsigne.redditclientapp.manager.AuthManager

/**
 * RedditBrowseServices for implementing the services needed for the presenter.
 **/
class RedditBrowseServices(
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
     * RedditBrowseView reference of the RedditBrowseFragment
     * as RedditBrowseView type. Must be weak
     **/
    var contract: RedditBrowseContract? = null


}
