package com.andreinsigne.redditclientapp.model

import kotlinx.serialization.Serializable

@Serializable
data class RedditPost (var json : RedditPostJsonResponse? = null)
@Serializable
data class RedditPostJsonResponse(var errors : ArrayList<RedditPostError>? = null,
                                  var url : String? = null)
@Serializable
data class RedditPostError (var ars : String? = null)