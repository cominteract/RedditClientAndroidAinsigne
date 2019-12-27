package com.andreinsigne.redditclientapp.ui.redditfeed

import android.util.Log
import com.andreinsigne.redditclientapp.R
import com.andreinsigne.redditclientapp.manager.*
import com.andreinsigne.redditclientapp.model.*
import com.andreinsigne.redditclientapp.utils.APIEndpoint
import com.andreinsigne.redditclientapp.utils.Config
import com.andreinsigne.redditclientapp.utils.EndPoints

/**
 * RedditFeedServices for implementing the services needed for the presenter.
 **/
class RedditFeedServices(
    apiManager: APIManager,
    authManager: AuthManager
) {
    /**
     * apiManager used in consuming the api related to data whether mock or from aws to be initialized
     **/
    var apiManager: APIManager = apiManager
    /**
     * authManager used in consuming the authentication api whether mock or from aws to be initialized
     **/
    var authManager: AuthManager = authManager
    /**
     * RedditFeedView reference of the RedditFeedFragment
     * as RedditFeedView type. Must be weak
     **/
    var contract: RedditFeedContract? = null

    /// manageHomeFeed appends the children so they can be displayed as merge of all the subreddits subscribed
    ///
    /// - Parameters:
    ///   - feeds: as [FeedListing] children to append with
    fun manageHomeFeed(feeds : ArrayList<FeedListing>, dele : RedditFeedContract?)
    {
        var feedListing = FeedListing( "", null)
        for(feed in feeds)
        {
            if(feed.data?.children != null && feed.data!!.children!!.size > 0)
            {
                var children = feed.data!!.children!!
                var count = 5
                if (feed.data!!.children!!.size < 5)
                {
                    count = feed.data!!.children!!.size - 1
                }
                if (feedListing.data == null)
                {
                    feedListing.data = FeedDataListing(children.subList(0,count).toList() as ArrayList<FeedChildrenListing>)
                }
                else
                {
                    feedListing.data?.children?.addAll(children.subList(0,count).toList() as ArrayList<FeedChildrenListing>)
                }
            }
        }
        dele?.retrievedHome(feedListing)
    }

    /// startLListing starts the api process to retrieve the home, popular, news feed,
// subreddits subscribed using the ListingRetrieved closure callback and the MeRetrieved to save the user info
    fun startListing()
    {
        var feedsCount = 0
        var feeds = ArrayList<FeedListing>()
        val retrieved = object : ListingRetrieved {
            override fun didRetrievedError(error: Error, revoked: Boolean) {

            }
            override fun didRetrievedListing(feedListing: FeedListing, apiEndpoint: APIEndpoint) {
                if (apiEndpoint == APIEndpoint.News){
                    contract?.retrievedNews(feedListing)
                }
                else if (apiEndpoint == APIEndpoint.MineSubreddit && feedListing.data?.children != null) {
                    var children = feedListing.data?.children
                    for (child in children!!)
                    {
                        if (child.data?.display_name_prefixed != null)
                        {
                            val name = child.data?.display_name_prefixed
                            apiManager.startAPI(APIEndpoint.Input, name ,  false, this)
                        }
                    }
                    feedsCount = feedListing.data!!.children!!.size
                    contract?.retrievedSubscribed(feedListing)
                }
                else if(apiEndpoint == APIEndpoint.Input)
                {

                    feeds.add(feedListing)
                    if (feedsCount == feeds.size)
                    {
                        Config.updateRefreshed("Yes")
                        manageHomeFeed(feeds, contract)
                    }
                }
                else if (apiEndpoint == APIEndpoint.Default){
                    contract?.retrievedBest(feedListing)
                }
                else if (apiEndpoint == APIEndpoint.Trending){
                    contract?.retrievedTrending(feedListing)
                }
                else if (apiEndpoint == APIEndpoint.Popular) {
                    contract?.retrievedPopular(feedListing)
                }
                else {
                    contract?.retrievedHome(feedListing)
                }
            }
        }
        if (authManager.isLogged())
        {
            apiManager.startAPI(APIEndpoint.MineSubreddit, null ,  false, retrieved)
        }
        else
        {
            Config.updateRefreshed("Not")
            apiManager.startAPI(APIEndpoint.Home , EndPoints.endpoint( APIEndpoint.Best, null)  ,  true, retrieved)
        }
        apiManager.startAPI(APIEndpoint.Popular,  EndPoints.endpoint( APIEndpoint.Hot, null) ,  true,  retrieved)
        apiManager.startAPI(APIEndpoint.News,  EndPoints.endpoint( APIEndpoint.Hot, null) ,  true,  retrieved)
        apiManager.startAPI(APIEndpoint.Default, EndPoints.endpoint( APIEndpoint.Hot, null) ,  true, retrieved)
        apiManager.startAPI(APIEndpoint.Trending, EndPoints.endpoint( APIEndpoint.Hot, null) ,  true,  retrieved)
        if (authManager.isLogged())
        {
            val meretrieved = object : MeRetrieved
            {
                override fun didRetrieveMe(me: Me) {
                    if(me.name != null)
                    {
                        Config.updateUser(me.name!!)
                    }

                }
                override fun didRetrievedError(error: Error, revoked: Boolean) {

                }
            }
            apiManager.startAPI(APIEndpoint.Me, null,  false, meretrieved)

        }
    }

    /// starts the api to search subreddits using the SubredditSearched closure callback to identify whether search is successful
    ///
    /// - Parameter input: as String the query to be searched
    fun startSearch(input : String)
    {
        val retrieved = object : SubredditSearched {
            override fun didRetrieveSearchedSub(
                searchSub: SearchSubreddit,
                apiEndpoint: APIEndpoint
            ) {
                contract?.retrieveSearched(searchSub)
            }

            override fun didRetrievedError(error: Error, revoked: Boolean) {

            }
        }
        apiManager.startAPI(APIEndpoint.SearchSub, input, false, retrieved)
    }
}
