package com.andreinsigne.redditclientapp.ui.redditchat


import com.andreinsigne.redditclientapp.manager.APIManager
import com.andreinsigne.redditclientapp.manager.AuthManager


/**
 * RedditChatView interface for updating the view in the fragments
 **/
interface RedditChatView {

}

/**
 * RedditChatContract interface for delegating implementations from the RedditChatServices
 **/
interface RedditChatContract {

}

/**
 * RedditChatPresenter interface for implementing the RedditChatPresenter
 **/
interface RedditChatPresenter {

}


/**
 * RedditChatPresenter implementation based on the presenter protocol.
 **/
class RedditChatPresenterImplementation(
    view: RedditChatView, apiManager: APIManager,
    authManager: AuthManager
) : RedditChatPresenter, RedditChatContract {

    var view: RedditChatView

    var service: RedditChatServices

    /**
     * initializes with the RedditChatFragment as the RedditChatView for updating
     * and authManager for consuming the authentication api and apiManager for consuming the api
     * related to data
     **/
    init {
        service = RedditChatServices(apiManager, authManager)
        service.contract = this
        this.view = view
    }


}
