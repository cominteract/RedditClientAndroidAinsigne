package com.andreinsigne.redditclientapp.ui.redditinbox


import com.andreinsigne.redditclientapp.manager.APIManager
import com.andreinsigne.redditclientapp.manager.AuthManager
import com.andreinsigne.redditclientapp.model.FeedChildrenListing
import com.andreinsigne.redditclientapp.model.FeedListing


/**
 * RedditInboxView interface for updating the view in the fragments
 **/
interface RedditInboxView {
    // retrievedInbox after retrieving the listings related to inbox
    ///
    /// - Parameter listing: as List<FeedChildrenListing> the listing retrieved
    fun retrievedInboxUpdateView(listing : List<FeedChildrenListing>)
    // retrievedActivities after retrieving the listings related to activities
    ///
    /// - Parameter listing: as List<FeedChildrenListing> the listing retrieved
    fun retrievedActivitiesUpdateView(listing : List<FeedChildrenListing>)
}

/**
 * RedditInboxContract interface for delegating implementations from the RedditInboxServices
 **/
interface RedditInboxContract {
    /// retrievedInbox after retrieving the listings related to inbox
    ///
    /// - Parameter listing: as List<FeedChildrenListing> the listing retrieved
    fun retrievedInbox(listing : List<FeedChildrenListing>)
    // retrievedActivities after retrieving the listings related to activities
    ///
    /// - Parameter listing: as List<FeedChildrenListing> the listing retrieved
    fun retrievedActivities(listing : List<FeedChildrenListing>)
}

/**
 * RedditInboxPresenter interface for implementing the RedditInboxPresenter
 **/
interface RedditInboxPresenter {
    fun startAPI()
}


/**
 * RedditInboxPresenter implementation based on the presenter protocol.
 **/
class RedditInboxPresenterImplementation(
    view: RedditInboxView, apiManager: APIManager,
    authManager: AuthManager
) : RedditInboxPresenter, RedditInboxContract {

    override fun retrievedInbox(listing: List<FeedChildrenListing>) {
        view.retrievedInboxUpdateView(listing)
    }

    override fun retrievedActivities(listing: List<FeedChildrenListing>) {
        view.retrievedActivitiesUpdateView(listing)
    }

    var view: RedditInboxView

    var service: RedditInboxServices

    /**
     * initializes with the RedditInboxFragment as the RedditInboxView for updating
     * and authManager for consuming the authentication api and apiManager for consuming the api
     * related to data
     **/
    init {
        service = RedditInboxServices(apiManager, authManager)
        service.contract = this
        this.view = view
    }

    override fun startAPI()
    {
        service.startListing()
    }
}
