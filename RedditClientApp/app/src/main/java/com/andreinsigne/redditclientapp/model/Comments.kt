package com.andreinsigne.redditclientapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Comments(var data : CommentsData? = null,
                    var kind : String? = null)
@Serializable
data class CommentsData(var children : ArrayList<CommentsChildren>? = null)
@Serializable
data class CommentsChildren(var data : CommentsChildrenData? = null)
@Serializable
data class CommentsChildrenData(var body : String?  = null,
                                var utc_created : Double?  = 0.0,
                                var subreddit : String? = null,
                                var selftext : String? = null,
                                var subreddit_name_prefixed : String? = null,
                                var ups : Double? = 0.0,
                                var id : String?  = null,
                                var author : String? = null,
                                var created_utc : Double? = 0.0,
                                var subreddit_subscribers : Int? = 0,
                                var num_comments : Double? = 0.0,
                                var total_awards_received : Int? = 0,
                                //var children : [CommentsChildren]?

    // var replies : CommentReplies?
    // var replies : ReplyData?
                                var link_id : String? = null)
@Serializable
data class Replies(var kind : String? = null,
                   var isEdited: Boolean = false,
                   var test1 : String? = null,
                   var data : CommentsData? = null)
