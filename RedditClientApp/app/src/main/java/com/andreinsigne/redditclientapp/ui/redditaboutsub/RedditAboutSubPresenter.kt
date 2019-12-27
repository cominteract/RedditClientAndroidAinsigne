package com.andreinsigne.redditclientapp.ui.redditaboutsub


import com.andreinsigne.redditclientapp.manager.APIManager
import com.andreinsigne.redditclientapp.manager.AuthManager


/**
 * RedditAboutSubView interface for updating the view in the fragments
 **/
interface RedditAboutSubView {

}

/**
 * RedditAboutSubContract interface for delegating implementations from the RedditAboutSubServices
 **/
interface RedditAboutSubContract {

}

/**
 * RedditAboutSubPresenter interface for implementing the RedditAboutSubPresenter
 **/
interface RedditAboutSubPresenter {

}


/**
 * RedditAboutSubPresenter implementation based on the presenter protocol.
 **/
class RedditAboutSubPresenterImplementation(
    view: RedditAboutSubView, apiManager: APIManager,
    authManager: AuthManager
) : RedditAboutSubPresenter, RedditAboutSubContract {

    var view: RedditAboutSubView

    var service: RedditAboutSubServices

    /**
     * initializes with the RedditAboutSubFragment as the RedditAboutSubView for updating
     * and authManager for consuming the authentication api and apiManager for consuming the api
     * related to data
     **/
    init {
        service = RedditAboutSubServices(apiManager, authManager)
        service.contract = this
        this.view = view
    }


}
