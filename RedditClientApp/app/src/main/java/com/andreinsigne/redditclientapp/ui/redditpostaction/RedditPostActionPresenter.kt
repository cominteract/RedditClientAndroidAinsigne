package com.andreinsigne.redditclientapp.ui.redditpostaction


import com.andreinsigne.redditclientapp.manager.APIManager
import com.andreinsigne.redditclientapp.manager.AuthManager
import com.andreinsigne.redditclientapp.model.FeedChildrenListing
import com.andreinsigne.redditclientapp.utils.PostType


/**
 * RedditPostActionView interface for updating the view in the fragments
 **/
interface RedditPostActionView {
    /// retrievedSubreddits after retrieving the listings related to subreddits
    ///
    /// - Parameter listing: as List<FeedChildrenListing> the listing retrieved
    fun retrievedSubredditsUpdateView(listing : List<FeedChildrenListing>)

    fun postedSuccessfullyUpdateView(message : String)
}

/**
 * RedditPostActionContract interface for delegating implementations from the RedditPostActionServices
 **/
interface RedditPostActionContract {
    /// retrievedSubreddits after retrieving the listings related to subreddits
    ///
    /// - Parameter listing: as List<FeedChildrenListing> the listing retrieved
    fun retrievedSubreddits(listing : List<FeedChildrenListing>)

    fun postedSuccessfully(message : String)
}

/**
 * RedditPostActionPresenter interface for implementing the RedditPostActionPresenter
 **/
interface RedditPostActionPresenter {
    fun startAPI()
    /// postText submits the text or link api request using the service connected to api manager
    ///
    /// - Parameters:
    ///   - title: as String the title of the post to submit
    ///   - text: as String the content of the post to submit
    ///   - srname: as  String the subreddit to post the message or link to
    ///   - type: as PostType to identify whether it is a link or text
    fun postText(title : String, text : String, srname : String, type : PostType)
}


/**
 * RedditPostActionPresenter implementation based on the presenter protocol.
 **/
class RedditPostActionPresenterImplementation(
    view: RedditPostActionView, apiManager: APIManager,
    authManager: AuthManager
) : RedditPostActionPresenter, RedditPostActionContract {

    override fun postedSuccessfully(message: String) {
        view.postedSuccessfullyUpdateView(message)
    }

    override fun retrievedSubreddits(listing: List<FeedChildrenListing>) {
        view.retrievedSubredditsUpdateView(listing)
    }

    var view: RedditPostActionView

    var service: RedditPostActionServices

    /**
     * initializes with the RedditPostActionFragment as the RedditPostActionView for updating
     * and authManager for consuming the authentication api and apiManager for consuming the api
     * related to data
     **/
    init {
        service = RedditPostActionServices(apiManager, authManager)
        service.contract = this
        this.view = view
    }


    override fun startAPI() {
        service.startListing()
    }

    override fun postText(title: String, text: String, srname: String, type: PostType) {
        if(type == PostType.Text)
            service.sendText(title,text,srname,type)
        else if(type == PostType.Link)
            service.sendLink(title,text,srname,type)
    }
}
