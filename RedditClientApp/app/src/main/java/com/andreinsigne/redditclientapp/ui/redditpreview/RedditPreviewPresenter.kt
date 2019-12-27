package com.andreinsigne.redditclientapp.ui.redditpreview


import com.andreinsigne.redditclientapp.manager.APIManager
import com.andreinsigne.redditclientapp.manager.AuthManager


/**
 * RedditPreviewView interface for updating the view in the fragments
 **/
interface RedditPreviewView {

}

/**
 * RedditPreviewContract interface for delegating implementations from the RedditPreviewServices
 **/
interface RedditPreviewContract {

}

/**
 * RedditPreviewPresenter interface for implementing the RedditPreviewPresenter
 **/
interface RedditPreviewPresenter {

}


/**
 * RedditPreviewPresenter implementation based on the presenter protocol.
 **/
class RedditPreviewPresenterImplementation(
    view: RedditPreviewView, apiManager: APIManager,
    authManager: AuthManager
) : RedditPreviewPresenter, RedditPreviewContract {

    var view: RedditPreviewView

    var service: RedditPreviewServices

    /**
     * initializes with the RedditPreviewFragment as the RedditPreviewView for updating
     * and authManager for consuming the authentication api and apiManager for consuming the api
     * related to data
     **/
    init {
        service = RedditPreviewServices(apiManager, authManager)
        service.contract = this
        this.view = view
    }


}
