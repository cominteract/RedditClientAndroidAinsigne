package com.andreinsigne.redditclientapp.model

import kotlinx.serialization.Serializable

@Serializable
data class FeedListing ( var kind : String? = null, var data: FeedDataListing? = null)
@Serializable
data class FeedDataListing ( var children : ArrayList<FeedChildrenListing>? = null)
@Serializable
data class FeedChildrenListing (var kind  : String? = null,var data : FeedChildDataListing? = null)
@Serializable
data class FeedChildDataListing (
                                 var kind : String? = null,
                                 var author : String? = null,
                                 var author_fullname : String? = null,
                                 var subreddit_name_prefixed : String? = null,
                                 var display_name : String? = null,
                                 var title : String? = null,
                                 var body : String? = null,
                                 var display_name_prefixed: String? = null,
                                 var public_description : String? = null,
                                 var subscribers : Int = 0,
                                 var banner_background_image : String? = null,
                                 var name : String? = null,
                                 var subreddit_id : String? = null,
                                 var id : String? = null,
                                 var num_comments : Int? = 0,
                                 var created_utc : Double? = 0.0,
                                 var score : Int? = 0,
                                 var ups : Int? = 0,
                                 var downs : Int? = 0,
                                 var thumbnail : String? = null,
                                 var total_awards_received : Int? = null,
                                 var url : String? = null,
                                 var was_comment : Boolean? = false,
                                 var post_hint : String? = null,
                                 var link_flair_text : String? = null,
                                 var preview : FeedChildPreviewListing? = null)
@Serializable
data class FeedChildPreviewListing(var images : ArrayList<FeedChildImagesListing>?)

@Serializable
data class FeedChildImagesListing(var resolutions : ArrayList<ImageProperty>?)

@Serializable
data class ImageProperty(var url : String? = null, var width : Int? =  0, var height : Int? = 0)