package com.andreinsigne.redditclientapp.ui.redditfeed


import com.andreinsigne.redditclientapp.manager.APIManager
import com.andreinsigne.redditclientapp.manager.AuthManager
import com.andreinsigne.redditclientapp.model.FeedListing
import com.andreinsigne.redditclientapp.model.SearchSubreddit


/**
 * RedditFeedView interface for updating the view in the fragments
 **/
interface RedditFeedView {
    /// retrievedNews after retrieving the listings
    ///
    /// - Parameter listing: as FeedListing the listing retrieved
    fun retrievedNewsUpdateView(listing : FeedListing)
    // retrievedPopular after retrieving the listings related to popular
    ///
    /// - Parameter listing: as FeedListing the listing retrieved
    fun retrievedPopularUpdateView(listing : FeedListing)
    /// retrievedHome after retrieving the listings related to user preferences
    ///
    /// - Parameter listing: as FeedListing the listing retrieved
    fun retrievedHomeUpdateView(listing : FeedListing)

    /// retrievedBest after retrieving the listings related to bast
    ///
    /// - Parameter listing: as FeedListing the listing retrieved
    fun retrievedBestUpdateView(listing : FeedListing)

    /// retrievedTrending after retrieving the listings related to trending
    ///
    /// - Parameter listing: as FeedListing the listing retrieved
    fun retrievedTrendingUpdateView(listing : FeedListing)
    /// retrievedSubscribed after retrieving the listings subreddit subscribed
    ///
    /// - Parameter listing: as FeedListing the listing retrieved
    fun retrievedSubscribedUpdateView(listing : FeedListing)
    /// retrievedSearched after retrieving the listings subreddit searched
    ///
    /// - Parameter listing: as FeedListing the listing retrieved
    fun retrieveSearchedUpdateView(searched : SearchSubreddit)


    fun moveToDetails(sr : String)

}

/**
 * RedditFeedContract interface for delegating implementations from the RedditFeedServices
 **/
interface RedditFeedContract {

    /// retrievedBest after retrieving the listings related to bast
    ///
    /// - Parameter listing: as FeedListing the listing retrieved
    fun retrievedBest(listing : FeedListing)

    /// retrievedNews after retrieving the listings
    ///
    /// - Parameter listing: as FeedListing the listing retrieved
    fun retrievedNews(listing : FeedListing)
    // retrievedPopular after retrieving the listings related to popular
    ///
    /// - Parameter listing: as FeedListing the listing retrieved
    fun retrievedPopular(listing : FeedListing)
    /// retrievedHome after retrieving the listings related to user preferences
    ///
    /// - Parameter listing: as FeedListing the listing retrieved
    fun retrievedHome(listing : FeedListing)
    /// retrievedTrending after retrieving the listings related to trending
    ///
    /// - Parameter listing: as FeedListing the listing retrieved
    fun retrievedTrending(listing : FeedListing)
    /// retrievedSubscribed after retrieving the listings subreddit subscribed
    ///
    /// - Parameter listing: as FeedListing the listing retrieved
    fun retrievedSubscribed(listing : FeedListing)
    /// retrievedSearched after retrieving the listings subreddit searched
    ///
    /// - Parameter listing: as FeedListing the listing retrieved
    fun retrieveSearched(searched : SearchSubreddit)
}

/**
 * RedditFeedPresenter interface for implementing the RedditFeedPresenter
 **/
interface RedditFeedPresenter {
    fun startAPI()

    fun startSearch(sr : String)

}


/**
 * RedditFeedPresenter implementation based on the presenter protocol.
 **/
class RedditFeedPresenterImplementation(
    view: RedditFeedView, apiManager: APIManager,
    authManager: AuthManager
) : RedditFeedPresenter, RedditFeedContract {


    var view: RedditFeedView

    var service: RedditFeedServices

    /**
     * initializes with the RedditFeedFragment as the RedditFeedView for updating
     * and authManager for consuming the authentication api and apiManager for consuming the api
     * related to data
     **/
    init {
        service = RedditFeedServices(apiManager, authManager)
        service.contract = this
        this.view = view
    }

    override fun retrievedNews(listing: FeedListing) {
        view.retrievedNewsUpdateView(listing)
    }

    override fun retrievedPopular(listing: FeedListing) {
        view.retrievedPopularUpdateView(listing)
    }

    override fun retrievedHome(listing: FeedListing) {
        view.retrievedHomeUpdateView(listing)
    }

    override fun retrievedTrending(listing: FeedListing) {
        view.retrievedTrendingUpdateView(listing)
    }

    override fun retrievedSubscribed(listing: FeedListing) {
        view.retrievedSubscribedUpdateView(listing)
    }

    override fun retrieveSearched(searched: SearchSubreddit) {
        view.retrieveSearchedUpdateView(searched)
    }

    override fun retrievedBest(listing: FeedListing) {
        view.retrievedBestUpdateView(listing)
    }

    override fun startAPI()
    {
        service.startListing()
    }

    override fun startSearch(sr: String) {
        service.startSearch(sr)

    }

}
