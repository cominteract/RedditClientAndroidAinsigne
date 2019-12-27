package com.andreinsigne.redditclientapp.ui.redditauthsign


import com.andreinsigne.redditclientapp.manager.APIManager
import com.andreinsigne.redditclientapp.manager.AuthManager


/**
 * RedditAuthSignView interface for updating the view in the fragments
 **/
interface RedditAuthSignView {

}

/**
 * RedditAuthSignContract interface for delegating implementations from the MainServices
 **/
interface RedditAuthSignContract {
    fun authTokenReceived(token : String?)
}

/**
 * RedditAuthSignPresenter interface for implementing the RedditAuthSignPresenter
 **/
interface RedditAuthSignPresenter {
    fun startAuth()
}


/**
 * RedditAuthSignPresenter implementation based on the presenter protocol.
 **/
class RedditAuthSignPresenterImplementation(
    view: RedditAuthSignView, apiManager: APIManager,
    authManager: AuthManager
) : RedditAuthSignPresenter, RedditAuthSignContract {

    override fun authTokenReceived(token: String?) {

    }

    var view: RedditAuthSignView

    var service: RedditAuthSignServices

    /**
     * initializes with the RedditAuthSignFragment as the RedditAuthSignView for updating
     * and authManager for consuming the authentication api and apiManager for consuming the api
     * related to data
     **/
    init {
        service = RedditAuthSignServices(apiManager, authManager)
        service.contract = this
        this.view = view
    }

    override fun startAuth() {
        service.startAuth()
    }
}
