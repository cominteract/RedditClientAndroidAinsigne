package com.andreinsigne.redditclientapp.ui.redditbrowse


import com.andreinsigne.redditclientapp.manager.APIManager
import com.andreinsigne.redditclientapp.manager.AuthManager


/**
 * RedditBrowseView interface for updating the view in the fragments
 **/
interface RedditBrowseView {

}

/**
 * RedditBrowseContract interface for delegating implementations from the RedditBrowseServices
 **/
interface RedditBrowseContract {

}

/**
 * RedditBrowsePresenter interface for implementing the RedditBrowsePresenter
 **/
interface RedditBrowsePresenter {

}


/**
 * RedditBrowsePresenter implementation based on the presenter protocol.
 **/
class RedditBrowsePresenterImplementation(
    view: RedditBrowseView, apiManager: APIManager,
    authManager: AuthManager
) : RedditBrowsePresenter, RedditBrowseContract {

    var view: RedditBrowseView

    var service: RedditBrowseServices

    /**
     * initializes with the RedditBrowseFragment as the RedditBrowseView for updating
     * and authManager for consuming the authentication api and apiManager for consuming the api
     * related to data
     **/
    init {
        service = RedditBrowseServices(apiManager, authManager)
        service.contract = this
        this.view = view
    }


}
