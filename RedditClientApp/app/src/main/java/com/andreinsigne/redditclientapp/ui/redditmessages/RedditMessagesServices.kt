package com.andreinsigne.redditclientapp.ui.redditmessages

import com.andreinsigne.redditclientapp.R
import com.andreinsigne.redditclientapp.manager.APIManager
import com.andreinsigne.redditclientapp.manager.AuthManager

/**
 * RedditMessagesServices for implementing the services needed for the presenter.
 **/
class RedditMessagesServices(
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
     * RedditMessagesView reference of the RedditMessagesFragment
     * as RedditMessagesView type. Must be weak
     **/
    var contract: RedditMessagesContract? = null


}
