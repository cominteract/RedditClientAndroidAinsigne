package com.andreinsigne.redditclientapp.ui.redditmenu


import com.andreinsigne.redditclientapp.manager.APIManager
import com.andreinsigne.redditclientapp.manager.AuthManager


/**
 * RedditMenuView interface for updating the view in the fragments
 **/
interface RedditMenuView {

}

/**
 * RedditMenuContract interface for delegating implementations from the RedditMenuServices
 **/
interface RedditMenuContract {

}

/**
 * RedditMenuPresenter interface for implementing the RedditMenuPresenter
 **/
interface RedditMenuPresenter {

}


/**
 * RedditMenuPresenter implementation based on the presenter protocol.
 **/
class RedditMenuPresenterImplementation(
    view: RedditMenuView, apiManager: APIManager,
    authManager: AuthManager
) : RedditMenuPresenter, RedditMenuContract {

    var view: RedditMenuView

    var service: RedditMenuServices

    /**
     * initializes with the RedditMenuFragment as the RedditMenuView for updating
     * and authManager for consuming the authentication api and apiManager for consuming the api
     * related to data
     **/
    init {
        service = RedditMenuServices(apiManager, authManager)
        service.contract = this
        this.view = view
    }


}
