package com.andreinsigne.redditclientapp.ui.redditmenu

import com.andreinsigne.redditclientapp.R
import com.andreinsigne.redditclientapp.manager.APIManager
import com.andreinsigne.redditclientapp.manager.AuthManager

/**
 * RedditMenuServices for implementing the services needed for the presenter.
 **/
class RedditMenuServices(
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
     * RedditMenuView reference of the RedditMenuFragment
     * as RedditMenuView type. Must be weak
     **/
    var contract: RedditMenuContract? = null


}
