package com.andreinsigne.redditclientapp.ui.redditdetails


import com.andreinsigne.redditclientapp.manager.APIManager
import com.andreinsigne.redditclientapp.manager.AuthManager
import com.andreinsigne.redditclientapp.manager.ListingRetrieved
import com.andreinsigne.redditclientapp.manager.SubredditRetrieved
import com.andreinsigne.redditclientapp.model.FeedListing
import com.andreinsigne.redditclientapp.model.Rules
import com.andreinsigne.redditclientapp.model.Subreddit
import com.andreinsigne.redditclientapp.utils.APIEndpoint


/**
 * RedditDetailsView interface for updating the view in the fragments
 **/
interface RedditDetailsView {
    fun retrievedPostsUpdateView(listing: FeedListing,endPoint: APIEndpoint)
    fun retrievedAboutSubredditUpdateView(subreddit : Subreddit ,endPoint: APIEndpoint)
    fun retrievedAboutSubRulesUpdateView(rules: Rules)
}

/**
 * RedditDetailsContract interface for delegating implementations from the RedditDetailsServices
 **/
interface RedditDetailsContract {
    fun retrievedPosts(listing: FeedListing,endPoint: APIEndpoint)
    fun retrievedAboutSubreddit(subreddit : Subreddit ,endPoint: APIEndpoint)
    fun retrievedAboutSubRules(rules: Rules)
}

/**
 * RedditDetailsPresenter interface for implementing the RedditDetailsPresenter
 **/
interface RedditDetailsPresenter {
    fun startAPI(sr : String)
}


/**
 * RedditDetailsPresenter implementation based on the presenter protocol.
 **/
class RedditDetailsPresenterImplementation(
    view: RedditDetailsView, apiManager: APIManager,
    authManager: AuthManager
) : RedditDetailsPresenter, RedditDetailsContract {
    override fun retrievedPosts(listing: FeedListing, endPoint: APIEndpoint) {
        view.retrievedPostsUpdateView(listing,endPoint)
    }

    override fun retrievedAboutSubreddit(subreddit: Subreddit, endPoint: APIEndpoint) {
        view.retrievedAboutSubredditUpdateView(subreddit, endPoint)
    }

    override fun retrievedAboutSubRules(rules: Rules) {
        view.retrievedAboutSubRulesUpdateView(rules)
    }

    override fun startAPI(sr: String) {
        service.startPosts(sr)
    }

    var view: RedditDetailsView

    var service: RedditDetailsServices

    /**
     * initializes with the RedditDetailsFragment as the RedditDetailsView for updating
     * and authManager for consuming the authentication api and apiManager for consuming the api
     * related to data
     **/
    init {
        service = RedditDetailsServices(apiManager, authManager)
        service.contract = this
        this.view = view
    }


}
