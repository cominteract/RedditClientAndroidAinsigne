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
import com.andreinsigne.redditclientapp.utils.*
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.adapter_reddit_activities.view.*

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL


class ActivitiesFeedAdapter(feedListing_: List<FeedChildrenListing>) : RecyclerView.Adapter<ActivitiesFeedAdapter.FeedDataHolder>()  {

    val feedListing = feedListing_

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FeedDataHolder {
        val inflatedView = LayoutInflater.from(p0.context)
            .inflate(R.layout.adapter_reddit_activities, p0, false)
        return FeedDataHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        val children = feedListing
        if(children != null)
            return children.size
        return 0
    }


    /**
     * FeedDataHolder as custom view holder which will define and bind the
     * data of the app to be displayed on the view
     * @param feedView_ as View that contains information of the feed such as details , subreddit name and the content
     */
    open inner class FeedDataHolder(feedView_: View) : RecyclerView.ViewHolder(feedView_) {

        val feedView = feedView_
        fun bind(child : FeedChildrenListing) {

            Glide.with(feedView.context).load(Constants.getLink(child)).into(feedView.iv_reddit_activity_icon)

            feedView.tv_activity_timestamp.text = child.data?.created_utc?.toDateFromUTC()

            feedView.tv_activity_community.text = child.data?.subreddit_name_prefixed
            feedView.tv_activity_msg.text = child.data?.body


        }
    }

    override fun onBindViewHolder(holder: FeedDataHolder, position: Int) {
        if(feedListing != null) {
            holder.bind(feedListing!![position])
        }
    }
}