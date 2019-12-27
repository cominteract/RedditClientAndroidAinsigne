package com.andreinsigne.redditclientapp.manager


import com.andreinsigne.redditclientapp.interfaces.APIInterface
import com.andreinsigne.redditclientapp.model.*
import com.andreinsigne.redditclientapp.utils.APIEndpoint
import com.andreinsigne.redditclientapp.utils.PostType

/** error retrieved which is the parent class to return the error
 * from the fetch query through closure callback
 **/
interface ErrorRetrieved{
    fun didRetrievedError(error : Error, revoked : Boolean)
}

/** listing retrieved which implements error retrieved
 * to return the error from the fetch query through closure callback
 **/
interface ListingRetrieved : ErrorRetrieved{
    fun didRetrievedListing(feedListing: FeedListing, apiEndpoint: APIEndpoint)
}

/** comments retrieved which implements error retrieved
 * to return the error from the fetch query through closure callback
 **/
interface CommentsRetrieved : ErrorRetrieved{
    fun didRetrievedComments(comments : List<Comments>)
}

/** rules retrieved which implements error retrieved
 * to return the error from the fetch query through closure callback
 **/
interface RulesRetrieved : ErrorRetrieved{
    fun didRetrieveRules(rules : Rules, apiEndpoint: APIEndpoint)
}

/** subreddit searched which implements error retrieved
 * to return the error from the fetch query through closure callback
 **/
interface SubredditSearched : ErrorRetrieved{
    fun didRetrieveSearchedSub(searchSub : SearchSubreddit, apiEndpoint: APIEndpoint)
}

/** subreddit retrieved which implements error retrieved
 * to return the error from the fetch query through closure callback
 **/
interface SubredditRetrieved : ErrorRetrieved{
    fun didRetrieveSubreddit(subreddit : Subreddit, apiEndpoint: APIEndpoint)
}

/** post success which implements error retrieved
 * to return the error from the fetch query through closure callback
 **/
interface PostSuccess : ErrorRetrieved{
    fun didRetrievePost(message : String, apiEndpoint: APIEndpoint)
}

/** me info retrieved which implements error retrieved
 * to return the error from the fetch query through closure callback
 **/
interface MeRetrieved : ErrorRetrieved{
    fun didRetrieveMe(me : Me)
}

/**
 * serves as an abstract class for the api protocol and its implementation
 **/
open class APIManager : APIInterface {
    var postType = PostType.Text
    override fun startAPI(endPoint: APIEndpoint, input: String?, isJson: Boolean, retrieved: ErrorRetrieved) {

    }
}
