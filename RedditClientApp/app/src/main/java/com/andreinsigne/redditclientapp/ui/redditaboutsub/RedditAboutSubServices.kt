package com.andreinsigne.redditclientapp.ui.redditaboutsub

import com.andreinsigne.redditclientapp.R
import com.andreinsigne.redditclientapp.manager.APIManager
import com.andreinsigne.redditclientapp.manager.AuthManager

/**
 * RedditAboutSubServices for implementing the services needed for the presenter.
 **/
class RedditAboutSubServices(
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
     * RedditAboutSubView reference of the RedditAboutSubFragment
     * as RedditAboutSubView type. Must be weak
     **/
    var contract: RedditAboutSubContract? = null


}
