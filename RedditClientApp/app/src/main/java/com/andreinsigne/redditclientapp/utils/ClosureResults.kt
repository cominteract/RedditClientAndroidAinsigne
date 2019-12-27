package com.andreinsigne.redditclientapp.utils

import android.util.Log
import com.andreinsigne.redditclientapp.manager.*
import com.andreinsigne.redditclientapp.model.*
import com.andreinsigne.redditclientapp.model.Me
import com.andreinsigne.redditclientapp.utils.APIEndpoint.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.json.JsonException
import kotlinx.serialization.list

class ClosureResults {
    companion object{
        /// apiResults consolidates the endpoints to return what type of closure they would need for the response
        ///
        /// - Parameters:
        ///   - apiEndpoint: as APIEndpoint the endpoint for the closure callback to be used
        ///   - data: as Data the response in data form to be decoded
        ///   - retrieved: as ErrorRetrieved the parent class to be downcasted for returning the results through callbacks
        fun apiResults(apiEndpoint : APIEndpoint, data : String, retrieved : ErrorRetrieved)
        {// (retrieved as ListingRetrieved).didRetrievedListing()

            //val json = Json(JsonConfiguration.Stable)
            val json = Json.nonstrict
            when(apiEndpoint)
            {
                News,Home,Popular, Trending, SubredditCollections, MineSubreddit, SubByTopic,
                SrNames, Notifications, MsgInbox, SubredditPosts,SubPopular,SubNew, UserOverview,
                Best, TrendingSub, Default, UserPopular, Input,SubredditInfo, SubAll ->
                {
                    try {
                        (retrieved as ListingRetrieved).
                            didRetrievedListing(json.parse(FeedListing.serializer(), data), apiEndpoint)
                        } catch (ignored: JsonException) {
                        Log.d("Error","Error $ignored")
                        retrieved.didRetrievedError(Error(""),false)
                    }
                }
                AbtSub ->
                {
                    try {
                        (retrieved as SubredditRetrieved).
                            didRetrieveSubreddit(json.parse(Subreddit.serializer(), data), apiEndpoint)
                    } catch (ignored: JsonException) {
                        retrieved.didRetrievedError(Error(""),false)
                    }
                }
                ArticleComments ->
                {
                    try {

                        (retrieved as CommentsRetrieved).
                            didRetrievedComments(json.parse(Comments.serializer().list,data))
                    } catch (ignored: JsonException) {
                        retrieved.didRetrievedError(Error(""),false)
                    }
                }
                SubmitLink -> {

                        if(data.toMap()["json"] !=null)
                            if(data.toMap()["json"].toString().toMap()["data"].toString() !=null)
                                if(data.toMap()["json"].toString().toMap()["data"].toString().toMap()["url"] !=null)
                                {
                                    (retrieved as PostSuccess).
                                        didRetrievePost("Posted Successfully",apiEndpoint)
                                }
                                else
                                {

                                    (retrieved as PostSuccess).
                                        didRetrievedError(Error(""), false)
                                }

                }
                AbtSubRules -> {

                    try {
                        (retrieved as RulesRetrieved).
                            didRetrieveRules(json.parse(Rules.serializer(), data), apiEndpoint)
                    } catch (ignored: JsonException) {
                        retrieved.didRetrievedError(Error(" AI Am i here "),false)
                    }
                }
                SearchSub -> {
                    try {
                        (retrieved as SubredditSearched).
                            didRetrieveSearchedSub(json.parse(SearchSubreddit.serializer(),data),apiEndpoint)
                    } catch (ignored: JsonException) {
                        retrieved.didRetrievedError(Error(""),false)
                    }
                }
                else -> {
                    try {
                        (retrieved as MeRetrieved).didRetrieveMe(json.parse(Me.serializer(),data))
                    } catch (ignored: JsonException) {
                        retrieved.didRetrievedError(Error(""),false)
                    }
                }
            }
        }
    }

}