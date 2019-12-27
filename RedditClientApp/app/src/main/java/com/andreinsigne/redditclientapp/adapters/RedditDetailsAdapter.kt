package com.andreinsigne.redditclientapp.adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andreinsigne.redditclientapp.R
import kotlinx.android.synthetic.main.adapter_reddit_feed.view.*
import android.graphics.BitmapFactory
import android.widget.TextView
import com.andreinsigne.redditclientapp.model.*
import com.andreinsigne.redditclientapp.ui.redditfeedview.RedditFeedViewView
import com.andreinsigne.redditclientapp.utils.*
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.adapter_detail_choices.view.*
import kotlinx.android.synthetic.main.adapter_reddit_detail_feed.view.*

import kotlinx.android.synthetic.main.adapter_reddit_header.view.*
import kotlinx.android.synthetic.main.adapter_reddit_rules.view.*


import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL
import kotlin.time.hours


class RedditDetailsAdapter(subreddit_: Subreddit) : RecyclerView.Adapter<RedditDetailsAdapter.RedditDetailHolder>()  {

    val subreddit = subreddit_
    var feedListing : FeedListing? = null
    var rules : Rules? = null
    var view: RedditFeedViewView? = null
    var isPost = true
    val headerView = 0
    val choiceView = 1
    val feedView = 2

    /**
     * getItemViewType returns the view type to display a different viewholder depending on the one returned
     * one for the headerview used in the user profile only at the top and other for displaying the apps
     * @param position as Int which is the current position of the view
     */
    override fun getItemViewType(position: Int): Int {
        return if (position == 0)
            headerView
        else if (position == 1)
            choiceView
        else feedView
    }




    override fun onCreateViewHolder(p0: ViewGroup,  viewType: Int): RedditDetailHolder {
        var inflatedView : View? = null
        Log.d(" AI CreateView "," AI CreateView ")
        when (viewType) {
            headerView -> {
                inflatedView = LayoutInflater.from(p0.context)
                    .inflate(R.layout.adapter_reddit_header, p0, false)
                return RedditHeaderHolder(inflatedView)
            }
            choiceView -> {
                inflatedView = LayoutInflater.from(p0.context)
                    .inflate(R.layout.adapter_detail_choices, p0, false)
                return ChoicesDataHolder(inflatedView)
            }
            else -> {
                    inflatedView = LayoutInflater.from(p0.context)
                        .inflate(R.layout.adapter_reddit_detail_feed, p0, false)
                return FeedDataHolder(inflatedView)
            }
        }
    }

    override fun getItemCount(): Int {
        val children = feedListing?.data?.children
        val rules = rules?.rules
        if(children != null && isPost) {
            return children.size + 2
        }
        else if(rules != null && !isPost) {
            return rules.size + 2
        }
        return 2
    }

    open inner class RedditDetailHolder(detailView_: View) : RecyclerView.ViewHolder(detailView_)
    {

    }

    inner class RedditHeaderHolder(detailView_: View) : RedditDetailHolder(detailView_)
    {
        val detailView = detailView_

        fun bind(subreddit: Subreddit)
        {

            Glide.with(detailView.context).load(subreddit.data?.banner_img).into(detailView.iv_reddit_header_bg)
            Glide.with(detailView.context).load(subreddit.data?.icon_img).into(detailView.iv_reddit_default_pic)
            detailView.tv_reddit_header_name.text = subreddit.data?.display_name_prefixed
            detailView.tv_reddit_desc.text = subreddit.data?.public_description
            detailView.tv_reddit_subscribers.text = subreddit.data?.subscribers?.toString()
            detailView.tv_reddit_online.text = subreddit.data?.active_user_count?.toString()

        }
    }

    fun updateAdapter()
    {
        this.notifyDataSetChanged()
    }

    inner class ChoicesDataHolder(choicesView_: View) : RedditDetailHolder(choicesView_)
    {
        val choicesView = choicesView_
        fun bind()
        {
            choicesView.btn_choices_menu.setOnClickListener {
                isPost = false
                updateAdapter()
            }
            choicesView.btn_choices_post.setOnClickListener {
                isPost = true
                updateAdapter()
            }
        }
    }

    /**
     * FeedDataHolder as custom view holder which will define and bind the
     * data of the app to be displayed on the view
     * @param feedView_ as View that contains information of the feed such as details , subreddit name and the content
     */
    inner class FeedDataHolder(feedView_: View) : RedditDetailHolder(feedView_) {

        val feedView = feedView_

        fun bindFeed()
        {
            if(isPost) {
                feedView.layout_posts.visibility = View.VISIBLE
                feedView.layout_text.visibility = View.GONE
            }
            else
            {
                feedView.layout_posts.visibility = View.GONE
                feedView.layout_text.visibility = View.VISIBLE
            }

        }

        fun bindRules(rules: SubRules)
        {
            feedView.tv_reddit_rules_text.text = rules.description
        }

        fun bind(child : FeedChildrenListing) {
            feedView.tv_hour.text = child.data?.created_utc?.toDateFromUTC()



            Glide.with(feedView.context).load(Constants.getLink(child)).into(feedView.iv_reddit_display_image)

            feedView.tv_num_comments.text = child.data?.num_comments.toString()
            feedView.tv_num_upvotes.text = child.data?.ups.toString()
            if(child.data?.num_comments != null && child.data?.num_comments!! > 1000)
            {
                val comD = child.data?.num_comments!!.toDouble()
                feedView.tv_num_comments.text = comD.toString()
            }
            if(child.data?.ups != null && child.data?.ups!! > 1000)
            {
                val upsD = child.data?.ups!!.toDouble()
                feedView.tv_num_upvotes.text = upsD.toString()
            }
            feedView.tv_reddit_name.text = child.data?.subreddit_name_prefixed
            feedView.tv_user_name.text = child.data?.display_name_prefixed
            feedView.tv_reddit_title.text = child.data?.title
            feedView.tv_reddit_name.setOnClickListener {
                if(child.data?.subreddit_name_prefixed != null)
                    view?.moveToDetails(child.data?.subreddit_name_prefixed!!, child)
            }
        }
    }

    override fun onBindViewHolder(holder: RedditDetailHolder, position: Int) {

        if(position == 0)
            (holder as RedditHeaderHolder).bind(subreddit)
        else if(position == 1)
            (holder as ChoicesDataHolder).bind()

        else if(rules?.rules != null && feedListing?.data != null && position >= 2) {
            val data = feedListing?.data
            if (data?.children != null) {
                if(isPost)
                    (holder as FeedDataHolder).bind(data.children!![position - 2])
                else
                    (holder as FeedDataHolder).bindRules(rules!!.rules!![position - 2])
                (holder).bindFeed()
            }
        }

    }
}