package com.andreinsigne.redditclientapp.utils

enum class APIEndpoint
{
    Me
    , Friends
    , Karma
    , Prefs
    , Trophies
    , Collection
    , AddPost
    , CreateCollection
    , SubredditCollections
    , Comment
    , FollowPost
    , SendReply
    , SubmitLink
    , TrendingSub
    , Gold
    , ArticleComments
    , Best
    , Hot
    , Popular
    , Home
    , News
    , New
    , Vote
    , Rising
    , Trending
    , Top
    , Controversial
    , Compose
    , DelMsg
    , ReadMsg
    , ReadAllMsg
    , MsgWhere
    , MsgInbox
    , MsgUnread
    , MsgSent
    , Search
    , SrNames
    , SearchSub
    , SearchProf
    , Subscribe
    , AbtSub
    , SubSearch
    , SubWhere
    , SubPopular
    , SubNew
    , SubGold
    , SubDefault
    , UserPopular
    , UserNew
    , Friend
    , FriendInfo
    , UserTrophies
    , About
    , UserOverview
    , UserComments
    , UserSubmitted
    , UserUpvoted
    , MineSubreddit
    , SubByTopic
    , Notifications
    , SubredditPosts
    , Default
    , Input
    , AbtSubRules
    , SubredditInfo
    , SubAll

}

class EndPoints {
    companion object{
        /// method returns the method to be used in httprequest
        ///
        /// - Parameter endpoint: as APIEndpoint the endpoint to be identified what method is needed
        /// - Returns: as String the method to be used in http request
        fun method(endpoint : APIEndpoint) : String = when (endpoint) {

            APIEndpoint.AddPost,APIEndpoint.CreateCollection,APIEndpoint.Comment,
            APIEndpoint.FollowPost,APIEndpoint.SendReply,APIEndpoint.SubmitLink,APIEndpoint.Gold,APIEndpoint.Vote,
            APIEndpoint.Compose,APIEndpoint.ReadMsg,APIEndpoint.DelMsg,APIEndpoint.SearchSub,APIEndpoint.Subscribe -> "POST"
            else -> "GET"
        }



        /// endpoint returns the endpoint String from the given APIEndpoint Enum
        ///
        /// - Parameters:
        ///   - endpoint: as APIEndpoint the endpoint identifier
        ///   - input: as String the parameter added required for certain endpoints
        /// - Returns: as String the endpoint String from the endpoint
        fun endpoint(endpoint : APIEndpoint, input : String?) : String
        {

            when (endpoint) {
                APIEndpoint.Me ->
                    return "/api/v1/me"
                APIEndpoint.MineSubreddit ->
                    return "/subreddits/mine/subscriber"
                APIEndpoint.Karma ->
                    return "/api/v1/me/karma"
                APIEndpoint.Friends ->
                    return "/api/v1/me/friends"
                APIEndpoint.Prefs ->
                    return "/api/v1/me/prefs"
                APIEndpoint.Trophies ->
                    return "/api/v1/me/trophies"
                APIEndpoint.Collection ->
                    return "/api/v1/collections/collection"
                APIEndpoint.AddPost ->
                    return "/api/v1/collections/add_post_to_collection"
                APIEndpoint.CreateCollection ->
                    return "/api/v1/collections/create_collection"
                APIEndpoint.SubredditCollections ->
                    return "/api/v1/collections/subreddit_collections"
                APIEndpoint.Comment ->
                    return "/api/comment"
                APIEndpoint.FollowPost ->
                    return "/api/follow_post"
                APIEndpoint.SendReply ->
                    return "/api/sendreplies"
                APIEndpoint.SubmitLink ->
                    return "/api/submit"
                APIEndpoint.Vote ->
                    return "/api/vote"
                APIEndpoint.Gold ->
                    return "/api/v1/gold/gild/${input})"
                APIEndpoint.TrendingSub ->
                    return "/api/trending_subreddits"
                APIEndpoint.Best ->
                    return "/best"
                APIEndpoint.ArticleComments ->
                    return if (input?.split(",") != null) {
                        "/${input.split(",")[0]}/comments/${input.split(",")[1]}"
                    } else {
                        "/r/hockey/comments/di8bgq"
                    }
                APIEndpoint.Hot ->
                    return "/hot"
                APIEndpoint.New ->
                    return "/new"
                APIEndpoint.Trending ->
                    return "/r/trending${input}"
                APIEndpoint.Popular ->
                    return "/r/popular${input}"
                APIEndpoint.Home ->
                    return "/r/home${input}"
                APIEndpoint.News ->
                    return "/r/news${input}"
                APIEndpoint.Rising ->
                    return "/rising"
                APIEndpoint.SubByTopic ->
                    return "/api/subreddits_by_topic"
                APIEndpoint.Top ->
                    return "/top"
                APIEndpoint.Controversial ->
                    return "/controversial"
                APIEndpoint.Compose ->
                    return "/api/compose"
                APIEndpoint.DelMsg ->
                    return "/api/del_msg"
                APIEndpoint.ReadMsg ->
                    return "/api/read_message"
                APIEndpoint.ReadAllMsg ->
                    return "/api/read_all_messages"
                APIEndpoint.MsgWhere ->
                    return "/message/where"
                APIEndpoint.MsgInbox ->
                    return "/message/inbox"
                APIEndpoint.MsgUnread ->
                    return "/message/unread"
                APIEndpoint.MsgSent ->
                    return "/message/sent"
                APIEndpoint.Search ->
                    return "/search"
                APIEndpoint.SrNames ->
                    return "/api/recommend/sr/srnames"
                APIEndpoint.SearchSub ->
                    return "/api/search_subreddits?query=${input}"
                APIEndpoint.Subscribe ->
                    return "/api/subscribe"
                APIEndpoint.SearchProf ->
                    return "/profiles/search"
                APIEndpoint.AbtSub ->
                    return "/${input}/about"
                APIEndpoint.AbtSubRules ->
                    return "/${input}/about/rules"
                APIEndpoint.SubSearch ->
                    return "/subreddits/search"
                APIEndpoint.SubNew ->
                    return "/subreddits/new"
                APIEndpoint.SubPopular ->
                    return "/subreddits/popular"
                APIEndpoint.SubAll ->
                    return "/subreddits/default?show=all"
                APIEndpoint.SubGold ->
                    return "/subreddits/gold"
                APIEndpoint.SubDefault ->
                    return "/subreddits/default"
                APIEndpoint.UserPopular ->
                    return "/users/popular"
                APIEndpoint.UserNew ->
                    return "/users/new"
                APIEndpoint.Friend ->
                    return "/api/friend"
                APIEndpoint.FriendInfo ->
                    return "/api/v1/me/friends/"
                APIEndpoint.UserTrophies ->
                    return "/api/v1/me/${input}/trophies"
                APIEndpoint.About ->
                    return "/user/${input}/about"
                APIEndpoint.UserOverview ->
                    return "/user/${input}/overview"
                APIEndpoint.UserSubmitted ->
                    return "/user/${input}/submitted"
                APIEndpoint.UserUpvoted ->
                    return "/user/${input}/upvoted"
                APIEndpoint.UserComments ->
                    return "/user/${input}/comments"
                APIEndpoint.Notifications ->
                    return "/api/v1/me/notifications"
                APIEndpoint.SubredditPosts ->
                    return "/${input}"
                APIEndpoint.SubredditInfo ->
                    return "/${input}/api/info"
                APIEndpoint.Default ->
                    return if (input != null) {
                        "https://www.reddit.com/${input}/.json"
                    } else {
                        "https://www.reddit.com/best/.json"
                    }
                APIEndpoint.Input ->
                    return "/${input}"
                else -> return "/api/v1/me"
            }
        }
    }

    
}