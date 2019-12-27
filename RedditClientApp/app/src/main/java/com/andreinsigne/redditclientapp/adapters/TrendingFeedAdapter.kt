package com.andreinsigne.redditclientapp.adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andreinsigne.redditclientapp.R
import com.andreinsigne.redditclientapp.model.FeedChildrenListing
import com.andreinsigne.redditclientapp.model.FeedListing
import kotlinx.android.synthetic.main.adapter_reddit_feed.view.*
import android.graphics.BitmapFactory
import com.andreinsigne.redditclientapp.ui.redditfeed.RedditFeedPresenter
import com.andreinsigne.redditclientapp.ui.redditfeed.RedditFeedView
import com.andreinsigne.redditclientapp.ui.redditfeedview.RedditFeedViewView
import com.andreinsigne.redditclientapp.utils.*
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.adapter_query_searched.view.*
import kotlinx.android.synthetic.main.adapter_trending_search_feed.view.*

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL
import kotlin.time.hours


class TrendingFeedAdapter(feedChildrenListing_: ArrayList<FeedChildrenListing>) : RecyclerView.Adapter<TrendingFeedAdapter.FeedDataHolder>()  {

    val feedChildrenListing = feedChildrenListing_
    var view: RedditFeedViewView? = null

    var searches = Config.getRecentsSearch()

    private val searchedPosition = 0
    private val subPosition = 1

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FeedDataHolder {
        var inflatedView : View? = null
        inflatedView = if(p1 == searchedPosition)
            LayoutInflater.from(p0.context)
                .inflate(R.layout.adapter_query_searched, p0, false)
        else
            LayoutInflater.from(p0.context)
                .inflate(R.layout.adapter_trending_search_feed, p0, false)
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

        return feedChildrenListing.size
    }


    /**
     * FeedDataHolder as custom view holder which will define and bind the
     * data of the app to be displayed on the view
     * @param feedView_ as View that contains information of the feed such as details , subreddit name and the content
     */
    open inner class FeedDataHolder(feedView_: View) : RecyclerView.ViewHolder(feedView_) {

        val feedView = feedView_
        fun bind(child : FeedChildrenListing) {



            Glide.with(feedView.context).load(Constants.getLink(child)).into(feedView.iv_reddit_trending_icon)
            Glide.with(feedView.context).load(Constants.getLink(child)).into(feedView.iv_reddit_trending_image)
            feedView.tv_trending_community.text = "${child.data?.link_flair_text}"
            feedView.tv_trending_msg.text = "${child.data?.title}"
            feedView.tv_trending_name.text = "${child.data?.subreddit_name_prefixed}"
        }

        fun bind(position: Int)
        {
            feedView.tv_query_title.text = searches!![position]
        }
    }

    override fun onBindViewHolder(holder: FeedDataHolder, position: Int) {


        if(searches != null && searches!!.isNotEmpty() && position < searches!!.size)
        {
            if(position < searches!!.size)
                holder.bind(position)
            else if(feedChildrenListing.size > (position - searches!!.size))
                holder.bind(feedChildrenListing[position - searches!!.size])
        }
        else if(feedChildrenListing.size > position) {
            holder.bind(feedChildrenListing[position])
        }


    }
}