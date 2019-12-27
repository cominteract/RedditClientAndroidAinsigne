package com.andreinsigne.redditclientapp.ui.redditpostfeed


import com.andreinsigne.redditclientapp.manager.APIManager
import com.andreinsigne.redditclientapp.manager.AuthManager


/**
 * RedditPostFeedView interface for updating the view in the fragments
 **/
interface RedditPostFeedView {

}

/**
 * RedditPostFeedContract interface for delegating implementations from the RedditPostFeedServices
 **/
interface RedditPostFeedContract {

}

/**
 * RedditPostFeedPresenter interface for implementing the RedditPostFeedPresenter
 **/
interface RedditPostFeedPresenter {

}


/**
 * RedditPostFeedPresenter implementation based on the presenter protocol.
 **/
class RedditPostFeedPresenterImplementation(
    view: RedditPostFeedView, apiManager: APIManager,
    authManager: AuthManager
) : RedditPostFeedPresenter, RedditPostFeedContract {

    var view: RedditPostFeedView

    var service: RedditPostFeedServices

    /**
     * initializes with the RedditPostFeedFragment as the RedditPostFeedView for updating
     * and authManager for consuming the authentication api and apiManager for consuming the api
     * related to data
     **/
    init {
        service = RedditPostFeedServices(apiManager, authManager)
        service.contract = this
        this.view = view
    }


}
