package com.andreinsigne.redditclientapp.ui.redditmessages


import com.andreinsigne.redditclientapp.manager.APIManager
import com.andreinsigne.redditclientapp.manager.AuthManager


/**
 * RedditMessagesView interface for updating the view in the fragments
 **/
interface RedditMessagesView {

}

/**
 * RedditMessagesContract interface for delegating implementations from the RedditMessagesServices
 **/
interface RedditMessagesContract {

}

/**
 * RedditMessagesPresenter interface for implementing the RedditMessagesPresenter
 **/
interface RedditMessagesPresenter {

}


/**
 * RedditMessagesPresenter implementation based on the presenter protocol.
 **/
class RedditMessagesPresenterImplementation(
    view: RedditMessagesView, apiManager: APIManager,
    authManager: AuthManager
) : RedditMessagesPresenter, RedditMessagesContract {

    var view: RedditMessagesView

    var service: RedditMessagesServices

    /**
     * initializes with the RedditMessagesFragment as the RedditMessagesView for updating
     * and authManager for consuming the authentication api and apiManager for consuming the api
     * related to data
     **/
    init {
        service = RedditMessagesServices(apiManager, authManager)
        service.contract = this
        this.view = view
    }


}
