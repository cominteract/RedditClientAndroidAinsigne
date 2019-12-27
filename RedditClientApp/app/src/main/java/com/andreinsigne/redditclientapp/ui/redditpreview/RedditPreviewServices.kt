package com.andreinsigne.redditclientapp.ui.redditpreview

import com.andreinsigne.redditclientapp.R
import com.andreinsigne.redditclientapp.manager.APIManager
import com.andreinsigne.redditclientapp.manager.AuthManager

/**
 * RedditPreviewServices for implementing the services needed for the presenter.
 **/
class RedditPreviewServices(
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
     * RedditPreviewView reference of the RedditPreviewFragment
     * as RedditPreviewView type. Must be weak
     **/
    var contract: RedditPreviewContract? = null


}
