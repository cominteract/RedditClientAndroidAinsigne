package com.andreinsigne.redditclientapp.ui.redditcommunity


import com.andreinsigne.redditclientapp.manager.APIManager
import com.andreinsigne.redditclientapp.manager.AuthManager
import com.andreinsigne.redditclientapp.model.FeedChildrenListing
import com.andreinsigne.redditclientapp.model.FeedListing


/**
 * RedditCommunityView interface for updating the view in the fragments
 **/
interface RedditCommunityView {
    // retrievedAll after retrieving the listings related to all
    ///
    /// - Parameter listing: as FeedListing the listing retrieved
    fun retrievedAllUpdateView(listing : FeedListing)
    /// retrievedSubreddits after retrieving the listings related to subreddits
    ///
    /// - Parameter listing: as List<FeedChildrenListing> the listing retrieved
    fun retrievedSubredditsUpdateView(listing : List<FeedChildrenListing>)

    // retrievedPopular after retrieving the listings related to popular
    ///
    /// - Parameter listing: as FeedListing the listing retrieved
    fun retrievedPopularUpdateView(listing : FeedListing)

    fun moveToDetails(sr : String, feedChildrenListing: FeedChildrenListing)

    fun moveToPopular()

    fun moveToAll()
}

/**
 * RedditCommunityContract interface for delegating implementations from the RedditCommunityServices
 **/
interface RedditCommunityContract {
    // retrievedAll after retrieving the listings related to all
    ///
    /// - Parameter listing: as FeedListing the listing retrieved
    fun retrievedAll(listing : FeedListing)
    // retrievedPopular after retrieving the listings related to popular
    ///
    /// - Parameter listing: as FeedListing the listing retrieved
    fun retrievedPopular(listing : FeedListing)
    /// retrievedSubreddits after retrieving the listings related to subreddits
    ///
    /// - Parameter listing: as List<FeedChildrenListing> the listing retrieved
    fun retrievedSubreddits(listing : List<FeedChildrenListing>)
}

/**
 * RedditCommunityPresenter interface for implementing the RedditCommunityPresenter
 **/
interface RedditCommunityPresenter {
    fun startAPI()
}


/**
 * RedditCommunityPresenter implementation based on the presenter protocol.
 **/
class RedditCommunityPresenterImplementation(
    view: RedditCommunityView, apiManager: APIManager,
    authManager: AuthManager
) : RedditCommunityPresenter, RedditCommunityContract {

    override fun retrievedAll(listing: FeedListing) {
        view.retrievedAllUpdateView(listing)
    }
    override fun retrievedPopular(listing: FeedListing) {
        view.retrievedPopularUpdateView(listing)
    }

    override fun startAPI() {
        service.startListing()
    }


    var view: RedditCommunityView

    var service: RedditCommunityServices

    /**
     * initializes with the RedditCommunityFragment as the RedditCommunityView for updating
     * and authManager for consuming the authentication api and apiManager for consuming the api
     * related to data
     **/
    init {
        service = RedditCommunityServices(apiManager, authManager)
        service.contract = this
        this.view = view
    }

    override fun retrievedSubreddits(listing: List<FeedChildrenListing>) {
        view.retrievedSubredditsUpdateView(listing)
    }
}
