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
import com.andreinsigne.redditclientapp.ui.redditcommunity.RedditCommunityView
import com.andreinsigne.redditclientapp.utils.*
import kotlinx.android.synthetic.main.adapter_reddit_activities.view.*
import kotlinx.android.synthetic.main.adapter_reddit_community.view.*
import kotlinx.android.synthetic.main.adapter_reddit_community.view.tv_community
import kotlinx.android.synthetic.main.adapter_reddit_community.view.tv_community_desc
import kotlinx.android.synthetic.main.adapter_reddit_search.view.*

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL


class FilterFeedAdapter(feedListing_: List<FeedChildrenListing>?, isCommunity_ : Boolean) : RecyclerView.Adapter<FilterFeedAdapter.FeedDataHolder>()  {

    val feedListing = feedListing_
    var isCommunity = isCommunity_
    var adjusted =  if(isCommunity) 3 else  0
    var view : RedditCommunityView? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FeedDataHolder {
        val inflatedView = LayoutInflater.from(p0.context)
            .inflate(R.layout.adapter_reddit_community, p0, false)
        return FeedDataHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        val children = feedListing
        if(children != null)
            return children.size + adjusted
        return adjusted
    }


    /**
     * FeedDataHolder as custom view holder which will define and bind the
     * data of the app to be displayed on the view
     * @param feedView_ as View that contains information of the feed such as details , subreddit name and the content
     */
    open inner class FeedDataHolder(feedView_: View) : RecyclerView.ViewHolder(feedView_) {

        val feedView = feedView_
        fun bind(child : FeedChildrenListing) {
                feedView.tv_community.text = child.data?.display_name_prefixed
                feedView.tv_community_desc.text = child.data?.public_description
                feedView.tv_community_desc.setOnClickListener {
                    if(child.data?.display_name_prefixed != null)
                        view?.moveToDetails(child.data?.display_name_prefixed!!,child)
                }

        }
        fun bind(position : Int)
        {
            feedView.tv_community.text = Constants.filterChoices[position]
            feedView.tv_community_desc.text = Constants.filterDescChoices[position]
            if(position == 1)
                feedView.tv_community_desc.setOnClickListener {
                    view?.moveToPopular()
                }
            if(position == 2)
                feedView.tv_community_desc.setOnClickListener {
                    view?.moveToAll()
                }
        }
    }

    override fun onBindViewHolder(holder: FeedDataHolder, position: Int) {

            if(position < adjusted) {
                holder.bind(position)
            }
            else {
                if(feedListing != null && feedListing.size > (position - adjusted))
                    holder.bind(feedListing[position - adjusted])
            }
    }
}