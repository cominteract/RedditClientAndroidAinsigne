package com.andreinsigne.redditclientapp.ui.redditdetailcomments


import android.util.Log
import com.andreinsigne.redditclientapp.manager.APIManager
import com.andreinsigne.redditclientapp.manager.AuthManager
import com.andreinsigne.redditclientapp.model.Comments
import com.andreinsigne.redditclientapp.model.FeedChildrenListing


/**
 * RedditDetailCommentsView interface for updating the view in the fragments
 **/
interface RedditDetailCommentsView {
    fun retrievedCommentsUpdateView(comments : List<Comments>)

    fun moveToDetails(feedChildrenListing_ : FeedChildrenListing)

    fun moveToUser(feedChildrenListing_ : FeedChildrenListing)

    fun moveToPreview(feedChildrenListing_ : FeedChildrenListing)
}

/**
 * RedditDetailCommentsContract interface for delegating implementations from the RedditDetailCommentsServices
 **/
interface RedditDetailCommentsContract {
    fun retrievedComments(comments : List<Comments>)
}

/**
 * RedditDetailCommentsPresenter interface for implementing the RedditDetailCommentsPresenter
 **/
interface RedditDetailCommentsPresenter {
    fun startAPI(sr : String,id : String)
}


/**
 * RedditDetailCommentsPresenter implementation based on the presenter protocol.
 **/
class RedditDetailCommentsPresenterImplementation(
    view: RedditDetailCommentsView, apiManager: APIManager,
    authManager: AuthManager
) : RedditDetailCommentsPresenter, RedditDetailCommentsContract {



    var view: RedditDetailCommentsView

    var service: RedditDetailCommentsServices

    /**
     * initializes with the RedditDetailCommentsFragment as the RedditDetailCommentsView for updating
     * and authManager for consuming the authentication api and apiManager for consuming the api
     * related to data
     **/
    init {
        service = RedditDetailCommentsServices(apiManager, authManager)
        service.contract = this
        this.view = view
    }

    override fun retrievedComments(comments: List<Comments>) {

        view.retrievedCommentsUpdateView(comments)
    }

    override fun startAPI(sr: String,id : String) {
        service.startComments(sr,id)
    }
}
