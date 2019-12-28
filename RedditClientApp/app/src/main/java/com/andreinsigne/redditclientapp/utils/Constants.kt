package com.andreinsigne.redditclientapp.utils

import com.andreinsigne.redditclientapp.model.FeedChildrenListing

enum class PostType
{
    Video
    ,Text
    ,Image
    ,Link
}

class Constants {
    companion object {
        val categories = arrayOf("Advice","Animals","Art", "DIY",
            "Electronics","Entertainment", "Fashion",
            "Food","Funny", "Gaming", "Health",
            "Memes", "Music", "NBA","NFL",
            "News", "Outdoors", "Photography",
            "Pics & Gifs", "Relationships","Science",
            "Sports", "TV","Tech", "Travel",
            "Video Games","Videos", "Vroom","Writing")
        val feed_bundle = "FeedBundle"
        val fake_build = "fake"
        val reddit_build = "reddit"
        val tabChoices = arrayOf("News","Home","Hot")
        val subredditTabChoices = arrayOf("Posts","About","Hot")
        val msgChoices = arrayOf("Inbox","Activity")
        val filterChoices = arrayOf("Browse Communities","Popular","All")
        val filterDescChoices = arrayOf("Explore by topic","The hottest posts in the internet","Even more top posts in reddit")


        /// getLink gets the link from a [FeedChildrenListing]
        ///
        /// - Parameters:
        ///   - child: [FeedChildrenListing for getting the link
        /// - Returns: as String the link url used to populate
        fun getLink(child : FeedChildrenListing) : String
        {
            if(child.data?.url != null && child.data?.url!!.contains("jpg"))
                return child.data?.url!!
            else if(child.data?.preview?.images != null && child.data?.preview?.images!!.size > 0){
                val image = child.data?.preview?.images!![0]
                val resolutions = image.resolutions?.size
                if(resolutions != null && resolutions > 0 && image.resolutions!![0].url != null) {
                    val url = image.resolutions!![0].url!!
                    return url
                }
            }
            else if (child.data?.thumbnail != null){
                if(child.data?.thumbnail != "self" || child.data?.thumbnail != "default" || child.data?.thumbnail != "spoiler")
                    return child.data?.thumbnail!!
            }
            else if (child.data?.banner_background_image != null)
                return child.data?.banner_background_image!!
            return ""
        }
    }


}