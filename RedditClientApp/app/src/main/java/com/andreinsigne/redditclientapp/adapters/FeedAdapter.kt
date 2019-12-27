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

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL
import kotlin.time.hours


class FeedAdapter(feedChildrenListing_: ArrayList<FeedChildrenListing>) : RecyclerView.Adapter<FeedAdapter.FeedDataHolder>()  {

    val feedChildrenListing = feedChildrenListing_
    var view: RedditFeedViewView? = null
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FeedDataHolder {
        val inflatedView = LayoutInflater.from(p0.context)
            .inflate(R.layout.adapter_reddit_feed, p0, false)
        return FeedDataHolder(inflatedView)
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
            feedView.tv_hour.text = child.data?.created_utc?.toDateFromUTC()

            Glide.with(feedView.context).load(Constants.getLink(child)).into(feedView.iv_reddit_display_image)

            feedView.iv_reddit_display_image.setOnClickListener {
                if(child.data?.subreddit_name_prefixed != null)
                    view?.moveToFullscreen(child.data?.subreddit_name_prefixed!!, child)
            }
            feedView.tv_num_comments.text = child.data?.num_comments.toString()
            feedView.tv_num_upvotes.text = child.data?.ups.toString()
            feedView.tv_num_awards.text = child.data?.total_awards_received.toString()
            if(child.data?.num_comments != null && child.data?.num_comments!! > 1000)
            {
                val comD = (child.data?.num_comments!!.toDouble() / 1000).roundTo(0)
                feedView.tv_num_comments.text = comD
            }
            if(child.data?.ups != null && child.data?.ups!! > 1000)
            {
                val upsD = (child.data?.ups!!.toDouble() / 1000).roundTo(0)
                feedView.tv_num_upvotes.text = upsD
            }

            if(child.data?.subreddit_name_prefixed != null)
                feedView.tv_reddit_name.text = child.data?.subreddit_name_prefixed
            else
                feedView.tv_reddit_name.text = child.data?.display_name_prefixed
            if(child.data?.author != null)
                feedView.tv_user_name.text = child.data?.author
            else
                feedView.tv_user_name.text = child.data?.title
            feedView.tv_reddit_title.text = child.data?.title
            if(child.data?.public_description != null)
            feedView.tv_reddit_title.text = child.data?.public_description

            feedView.tv_num_comments.setOnClickListener {
                if(child.data?.subreddit_name_prefixed != null)
                    view?.moveToComments(child.data?.subreddit_name_prefixed!!, child)
            }

            feedView.iv_comment_icon.setOnClickListener {
                if(child.data?.subreddit_name_prefixed != null)
                    view?.moveToComments(child.data?.subreddit_name_prefixed!!, child)
            }

            feedView.tv_reddit_name.setOnClickListener {
                if(child.data?.subreddit_name_prefixed != null)
                    view?.moveToDetails(child.data?.subreddit_name_prefixed!!, child)
            }
            feedView.tv_user_name.setOnClickListener {
                if(child.data?.author!= null)
                    view?.moveToUser(child.data?.author!!)
            }
        }
    }

    override fun onBindViewHolder(holder: FeedDataHolder, position: Int) {

                holder.bind(feedChildrenListing[position])


    }
}