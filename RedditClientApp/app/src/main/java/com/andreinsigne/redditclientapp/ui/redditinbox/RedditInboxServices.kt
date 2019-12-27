package com.andreinsigne.redditclientapp.ui.redditinbox

import android.util.Log
import com.andreinsigne.redditclientapp.R
import com.andreinsigne.redditclientapp.manager.APIManager
import com.andreinsigne.redditclientapp.manager.AuthManager
import com.andreinsigne.redditclientapp.manager.ListingRetrieved
import com.andreinsigne.redditclientapp.model.FeedChildrenListing
import com.andreinsigne.redditclientapp.model.FeedListing
import com.andreinsigne.redditclientapp.utils.APIEndpoint
import com.andreinsigne.redditclientapp.utils.Config

/**
 * RedditInboxServices for implementing the services needed for the presenter.
 **/
class RedditInboxServices(
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
     * RedditInboxView reference of the RedditInboxFragment
     * as RedditInboxView type. Must be weak
     **/
    var contract: RedditInboxContract? = null
    /// filterInbox filter the Listing through whether it is an inbox or not
    ///
    /// - Parameter children: as FeedChildrenListing? the listing to filter to
    /// - Returns: as Bool whether current listing is an inbox or not
    fun filterInbox(children : FeedChildrenListing?) : Boolean
    {
        if (children != null && children.data?.was_comment != null)
        {
            return true
        }
        return false
    }

    /// filterActivity filter the Listing through whether it is an activity or not
    ///
    /// - Parameter children: as FeedChildrenListing? the listing to filter to
    /// - Returns: as Bool whether current listing is an activity or not
    fun filterActivity(children : FeedChildrenListing?) : Boolean
    {
        if (children != null && children.data?.was_comment != null)
        {
            return false
        }
        return true
    }

    /// startListing starts the api process to retrieve the msginbox and overview endpoints for the listings using the ListingRetrieved closure callback
    fun startListing()
    {
        val retrieved = object : ListingRetrieved
        {
            override fun didRetrievedError(error: Error, revoked: Boolean) {
                Log.d("Fail retrieve inbox","Fail in retrieving inbox 1")
            }

            override fun didRetrievedListing(feedListing: FeedListing, apiEndpoint: APIEndpoint) {
                if(apiEndpoint == APIEndpoint.MsgInbox && feedListing.data?.children != null)
                {
                    var children = feedListing.data?.children
                    val activity: List<FeedChildrenListing>?
                    activity = children?.filter {
                        filterInbox(it)
                    }
                    val inbox: List<FeedChildrenListing>?
                    inbox = children?.filter {
                        filterActivity(it)
                    }
                    if (inbox != null)
                        contract?.retrievedInbox(inbox )
                    else
                        Log.d("Fail retrieve inbox","Fail in retrieving inbox ")
                    if (activity != null)
                        contract?.retrievedActivities(activity )
                    else
                        Log.d("Fail retrieve inbox","Fail in retrieving inbox ")
                    Config.updateRefreshedInbox("Yes")
                }
                else
                    Log.d("Fail retrieve inbox","Fail in retrieving inbox 2")
            }

        }

        Config.updateRefreshedInbox("Not")
        if(Config.getUser() != null){

            var username = Config.getUser()
            apiManager.startAPI(APIEndpoint.MsgInbox,  username,  false,  retrieved)
        }

    }

}
