package com.andreinsigne.redditclientapp.ui.redditdetailcomments

import android.util.Log
import com.andreinsigne.redditclientapp.R
import com.andreinsigne.redditclientapp.manager.APIManager
import com.andreinsigne.redditclientapp.manager.AuthManager
import com.andreinsigne.redditclientapp.manager.CommentsRetrieved
import com.andreinsigne.redditclientapp.model.Comments
import com.andreinsigne.redditclientapp.utils.APIEndpoint

/**
 * RedditDetailCommentsServices for implementing the services needed for the presenter.
 **/
class RedditDetailCommentsServices(
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
     * RedditDetailCommentsView reference of the RedditDetailCommentsFragment
     * as RedditDetailCommentsView type. Must be weak
     **/
    var contract: RedditDetailCommentsContract? = null

    fun startComments(sr : String,id : String)
    {
        val commentsRetrieved = object : CommentsRetrieved {
            override fun didRetrievedError(error: Error, revoked: Boolean) {

            }

            override fun didRetrievedComments(comments: List<Comments>) {
                contract?.retrievedComments(comments.toList())
            }

        }
        val subreddit = "$sr,$id"
        apiManager.startAPI(APIEndpoint.ArticleComments, subreddit, false, commentsRetrieved)
    }
}
