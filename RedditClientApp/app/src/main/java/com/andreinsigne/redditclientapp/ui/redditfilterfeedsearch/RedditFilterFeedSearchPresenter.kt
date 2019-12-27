package com.andreinsigne.redditclientapp.ui.redditfilterfeedsearch


import com.andreinsigne.redditclientapp.manager.APIManager
import com.andreinsigne.redditclientapp.manager.AuthManager


/**
 * RedditFilterFeedSearchView interface for updating the view in the fragments
 **/
interface RedditFilterFeedSearchView {

}

/**
 * RedditFilterFeedSearchContract interface for delegating implementations from the RedditFilterFeedSearchServices
 **/
interface RedditFilterFeedSearchContract {

}

/**
 * RedditFilterFeedSearchPresenter interface for implementing the RedditFilterFeedSearchPresenter
 **/
interface RedditFilterFeedSearchPresenter {

}


/**
 * RedditFilterFeedSearchPresenter implementation based on the presenter protocol.
 **/
class RedditFilterFeedSearchPresenterImplementation(
    view: RedditFilterFeedSearchView, apiManager: APIManager,
    authManager: AuthManager
) : RedditFilterFeedSearchPresenter, RedditFilterFeedSearchContract {

    var view: RedditFilterFeedSearchView

    var service: RedditFilterFeedSearchServices

    /**
     * initializes with the RedditFilterFeedSearchFragment as the RedditFilterFeedSearchView for updating
     * and authManager for consuming the authentication api and apiManager for consuming the api
     * related to data
     **/
    init {
        service = RedditFilterFeedSearchServices(apiManager, authManager)
        service.contract = this
        this.view = view
    }


}
