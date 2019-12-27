package com.andreinsigne.redditclientapp.ui.redditfeedview


import com.andreinsigne.redditclientapp.manager.APIManager
import com.andreinsigne.redditclientapp.manager.AuthManager
import com.andreinsigne.redditclientapp.model.FeedChildrenListing
import com.andreinsigne.redditclientapp.model.FeedListing
import com.andreinsigne.redditclientapp.model.SearchSubreddit


/**
 * RedditFeedViewView interface for updating the view in the fragments
 **/
interface RedditFeedViewView {


    fun moveToComments(sr : String, feedChildrenListing: FeedChildrenListing)

    fun moveToDetails(sr : String, feedChildrenListing: FeedChildrenListing)

    fun moveToUser(username : String)

    fun moveToFullscreen(sr : String, feedChildrenListing: FeedChildrenListing)
}

/**
 * RedditFeedViewContract interface for delegating implementations from the RedditFeedViewServices
 **/
interface RedditFeedViewContract {

}

/**
 * RedditFeedViewPresenter interface for implementing the RedditFeedViewPresenter
 **/
interface RedditFeedViewPresenter {

}


/**
 * RedditFeedViewPresenter implementation based on the presenter protocol.
 **/
class RedditFeedViewPresenterImplementation(
    view: RedditFeedViewView, apiManager: APIManager,
    authManager: AuthManager
) : RedditFeedViewPresenter, RedditFeedViewContract {


    var view: RedditFeedViewView

    var service: RedditFeedViewServices



    /**
     * initializes with the RedditFeedViewFragment as the RedditFeedViewView for updating
     * and authManager for consuming the authentication api and apiManager for consuming the api
     * related to data
     **/
    init {
        service = RedditFeedViewServices(apiManager, authManager)
        service.contract = this
        this.view = view
    }


}
