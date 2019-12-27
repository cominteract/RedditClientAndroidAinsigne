package com.andreinsigne.redditclientapp.ui.redditoauth


import com.andreinsigne.redditclientapp.manager.APIManager
import com.andreinsigne.redditclientapp.manager.AuthManager


/**
 * RedditOAuthView interface for updating the view in the fragments
 **/
interface RedditOAuthView {

}

/**
 * RedditOAuthContract interface for delegating implementations from the RedditOAuthServices
 **/
interface RedditOAuthContract {

}

/**
 * RedditOAuthPresenter interface for implementing the RedditOAuthPresenter
 **/
interface RedditOAuthPresenter {

}


/**
 * RedditOAuthPresenter implementation based on the presenter protocol.
 **/
class RedditOAuthPresenterImplementation(
    view: RedditOAuthView, apiManager: APIManager,
    authManager: AuthManager
) : RedditOAuthPresenter, RedditOAuthContract {

    var view: RedditOAuthView

    var service: RedditOAuthServices

    /**
     * initializes with the RedditOAuthFragment as the RedditOAuthView for updating
     * and authManager for consuming the authentication api and apiManager for consuming the api
     * related to data
     **/
    init {
        service = RedditOAuthServices(apiManager, authManager)
        service.contract = this
        this.view = view
    }


}
