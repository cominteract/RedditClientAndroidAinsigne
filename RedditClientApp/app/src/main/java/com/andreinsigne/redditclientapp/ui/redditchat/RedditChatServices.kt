package com.andreinsigne.redditclientapp.ui.redditchat

import com.andreinsigne.redditclientapp.R
import com.andreinsigne.redditclientapp.manager.APIManager
import com.andreinsigne.redditclientapp.manager.AuthManager

/**
 * RedditChatServices for implementing the services needed for the presenter.
 **/
class RedditChatServices(
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
     * RedditChatView reference of the RedditChatFragment
     * as RedditChatView type. Must be weak
     **/
    var contract: RedditChatContract? = null


}
