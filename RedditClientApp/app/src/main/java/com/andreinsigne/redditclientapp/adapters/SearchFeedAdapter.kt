package com.andreinsigne.redditclientapp.adapters

import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andreinsigne.redditclientapp.R
import com.andreinsigne.redditclientapp.model.FeedChildrenListing
import com.andreinsigne.redditclientapp.model.SearchSubreddit
import com.andreinsigne.redditclientapp.model.SubredditList
import com.andreinsigne.redditclientapp.ui.redditfeed.RedditFeedView
import com.andreinsigne.redditclientapp.ui.redditfeedview.RedditFeedViewView
import com.andreinsigne.redditclientapp.utils.Config
import com.andreinsigne.redditclientapp.utils.Constants
import com.andreinsigne.redditclientapp.utils.roundTo
import com.andreinsigne.redditclientapp.utils.toDateFromUTC
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.adapter_interest_searched.view.*
import kotlinx.android.synthetic.main.adapter_reddit_search_feed.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

class SearchFeedAdapter(searched_: SearchSubreddit) : RecyclerView.Adapter<SearchFeedAdapter.FeedDataHolder>()  {

    val searched = searched_
    var view: RedditFeedView? = null
    var searches : Array<String>? = null

    private val searchedPosition = 0
    private val subPosition = 1

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FeedDataHolder {

        var inflatedView : View? = null
        inflatedView = if(p1 == searchedPosition)
            LayoutInflater.from(p0.context)
                .inflate(R.layout.adapter_interest_searched, p0, false)
        else
            LayoutInflater.from(p0.context)
                .inflate(R.layout.adapter_reddit_search_feed, p0, false)

        return FeedDataHolder(inflatedView)
    }



    override fun getItemViewType(position: Int): Int {

        return if(searches != null && searches!!.isNotEmpty() && position < searches!!.size) {
            searchedPosition
        } else {
            subPosition
        }
    }

    override fun getItemCount(): Int {
        val children = searched.subreddits

        if(children != null && searches != null)
            return children.size + searches!!.size
        else if(children != null)
            return children!!.size
        else return 0
    }


    /**
     * FeedDataHolder as custom view holder which will define and bind the
     * data of the app to be displayed on the view
     * @param feedView_ as View that contains information of the feed such as details , subreddit name and the content
     */
    open inner class FeedDataHolder(feedView_: View) : RecyclerView.ViewHolder(feedView_) {

        val feedView = feedView_
        fun bind(subreddit : SubredditList) {




            Glide.with(feedView.context).load(subreddit.icon_img).into(feedView.iv_reddit_search_icon)



            feedView.tv_search_timestamp.text = "${subreddit.active_user_count?.roundTo(0)} online"
            if(subreddit.active_user_count != null && subreddit.active_user_count!! > 1000 )
                feedView.tv_search_timestamp.text = "${(subreddit.active_user_count!! / 1000).roundTo(0)} online"

            feedView.tv_search_community.text = "${subreddit.subscriber_count?.roundTo(0)} subscribers"
            if(subreddit.subscriber_count != null && subreddit.subscriber_count!! > 1000 )
                feedView.tv_search_community.text = "${(subreddit.subscriber_count!! / 1000).roundTo(0)} subscribers"
            feedView.tv_search_msg.text = subreddit.name.toString()

            feedView.setOnClickListener {
                view?.moveToDetails("/r/${subreddit.name}")
            }
        }

        fun bind(position: Int)
        {
            feedView.tv_interest_title.text = searches?.get(position)
        }
    }

    override fun onBindViewHolder(holder: FeedDataHolder, position: Int) {

        if(searches != null && searches!!.isNotEmpty() && position < searches!!.size)
        {
            if(position < searches!!.size)
                holder.bind(position)
            else if(searched.subreddits != null && searched.subreddits!!.size > (position - searches!!.size))
                holder.bind(searched.subreddits!![position - searches!!.size])
        }
        else if(searched.subreddits != null && searched.subreddits!!.size > position) {
            holder.bind(searched.subreddits!![position])
        }

    }
}