package com.andreinsigne.redditclientapp.ui.redditoauth

import com.andreinsigne.redditclientapp.R
import com.andreinsigne.redditclientapp.manager.APIManager
import com.andreinsigne.redditclientapp.manager.AuthManager
import com.andreinsigne.redditclientapp.manager.TokenRetrieve
import com.andreinsigne.redditclientapp.model.TokenResponse
import com.andreinsigne.redditclientapp.utils.Config
import java.text.SimpleDateFormat
import java.util.*

/**
 * RedditOAuthServices for implementing the services needed for the presenter.
 **/
class RedditOAuthServices(
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
     * RedditOAuthView reference of the RedditOAuthFragment
     * as RedditOAuthView type. Must be weak
     **/
    var contract: RedditOAuthContract? = null


}
