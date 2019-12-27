package com.andreinsigne.redditclientapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Subreddit (var data : SubredditData? = null)
@Serializable
data class SubredditData(var title : String? = null,
                         var icon_img : String? = null,
                         var active_user_count : Int = 0,
                         var subscribers : Int = 0,
                         var accounts_active : Int = 0,
                         var public_description : String? = null,
                         var created_utc : Double = 0.0,
                         var banner_img : String? = null,
                         var description : String? = null,
                         var display_name_prefixed : String? = null)