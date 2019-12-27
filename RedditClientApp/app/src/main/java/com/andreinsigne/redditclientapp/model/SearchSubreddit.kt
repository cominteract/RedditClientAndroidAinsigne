package com.andreinsigne.redditclientapp.model

import kotlinx.serialization.Serializable

@Serializable
data class SearchSubreddit (var subreddits : ArrayList<SubredditList>? = null)
@Serializable
data class SubredditList(var icon_img : String? = null,
                         var name : String? = null,
                         var active_user_count : Double? = 0.0,
                         var subscriber_count : Double? = 0.0)