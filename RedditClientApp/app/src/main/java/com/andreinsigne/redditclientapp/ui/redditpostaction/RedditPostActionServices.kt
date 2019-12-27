package com.andreinsigne.redditclientapp.ui.redditpostaction

import android.util.Log
import com.andreinsigne.redditclientapp.R
import com.andreinsigne.redditclientapp.manager.APIManager
import com.andreinsigne.redditclientapp.manager.AuthManager
import com.andreinsigne.redditclientapp.manager.ListingRetrieved
import com.andreinsigne.redditclientapp.manager.PostSuccess
import com.andreinsigne.redditclientapp.model.FeedListing
import com.andreinsigne.redditclientapp.utils.APIEndpoint
import com.andreinsigne.redditclientapp.utils.Config
import com.andreinsigne.redditclientapp.utils.PostType

/**
 * RedditPostActionServices for implementing the services needed for the presenter.
 **/
class RedditPostActionServices(
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
     * RedditPostActionView reference of the RedditPostActionFragment
     * as RedditPostActionView type. Must be weak
     **/
    var contract: RedditPostActionContract? = null

    /// startListing starts the api process to retrieve the subreddits subscribed using the ListingRetrieved closure callback
    fun startListing() {
        val retrieved = object : ListingRetrieved {
            override fun didRetrievedError(error: Error, revoked: Boolean) {

            }

            override fun didRetrievedListing(feedListing: FeedListing, apiEndpoint: APIEndpoint) {
                if(feedListing.data != null && feedListing.data!!.children != null) {
                    contract?.retrievedSubreddits(feedListing.data!!.children!!.toList())
                    Config.updateRefreshedCom("Yes")
                }
            }

        }

        Config.updateRefreshedCom("Not")
        if(authManager.isLogged())
        {
            apiManager.startAPI(APIEndpoint.MineSubreddit, null, false, retrieved)
        }
    }


    /// postSuccess returns the closure callback to determine whether sending post request is successful or not
    ///
    /// - Returns: as postSuccess the closure callback
    fun postSuccess() : PostSuccess
    {
        val retrieved = object : PostSuccess{
            override fun didRetrievedError(error: Error, revoked: Boolean) {
            }

            override fun didRetrievePost(message: String, apiEndpoint: APIEndpoint) {
                contract?.postedSuccessfully(message)
            }
        }
        return retrieved
    }


    /// sendText submits the text or link api request using the service connected to api manager using the closure callback PostSuccess
    ///
    /// - Parameters:
    ///   - title: as String the title of the post to submit
    ///   - text: as String the content of the post to submit
    ///   - sr: as  String the subreddit to post the message or link to
    ///   - postType: as PostType to identify whether it is a link or text
    fun sendText(title : String, text: String, sr : String, postType : PostType)
    {
        val params = "json,self,true,true,$sr,$title,$text"
        apiManager.postType = postType
        apiManager.startAPI(APIEndpoint.SubmitLink, params, false, postSuccess() )
    }

    /// sendLink submits the text or link api request using the service connected to api manager using the closure callback PostSuccess
    ///
    /// - Parameters:
    ///   - title: as String the title of the post to submit
    ///   - url: as String the content of the post to submit
    ///   - sr: as  String the subreddit to post the message or link to
    ///   - postType: as PostType to identify whether it is a link or text

    fun sendLink(title : String, url : String, sr : String, postType : PostType)
    {
        val params = "json,link,true,true,$sr,$title,$url"
        apiManager.postType = postType
        apiManager.startAPI(APIEndpoint.SubmitLink,  params, false,  postSuccess() )
    }

    /// sendVideo submits the image or video api request using the service connected to api manager using the closure callback PostSuccess
    ///
    /// - Parameters:
    ///   - title: as String the title of the post to submit
    ///   - text: as String the content of the post to submit
    ///   - sr: as  String the subreddit to post the image or video to
    ///   - postType: as PostType to identify whether it is a image or video
    fun sendVideo(title : String, data : String, sr : String, postType : PostType)
    {

        //let params = "json,video,true,true,\(sr),\(data),\(url)"
        //self.apiManager.startAPI(endPoint: APIEndpoint.SubmitLink, input: params, isJson: false, retrieved: postSuccesss() )
    }

    /// sendImage submits the image or video api request using the service connected to api manager using the closure callback PostSuccess
    ///
    /// - Parameters:
    ///   - title: as String the title of the post to submit
    ///   - text: as String the content of the post to submit
    ///   - sr: as  String the subreddit to post the image or video to
    ///   - postType: as PostType to identify whether it is a image or video
    fun sendImage(title : String, data : String, sr : String, postType : PostType)
    {
        //let params = "json,image,true,true,\(sr),\(data),\(url)"
        // self.apiManager.startAPI(endPoint: APIEndpoint.SubmitLink, input: params, isJson: false, retrieved: postSuccesss() )
    }


}
