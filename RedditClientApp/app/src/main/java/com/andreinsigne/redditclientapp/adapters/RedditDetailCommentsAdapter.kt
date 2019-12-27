package com.andreinsigne.redditclientapp.adapters


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.andreinsigne.redditclientapp.R
import com.andreinsigne.redditclientapp.model.Comments
import com.andreinsigne.redditclientapp.model.FeedChildrenListing
import com.andreinsigne.redditclientapp.ui.redditdetailcomments.RedditDetailCommentsView
import com.andreinsigne.redditclientapp.utils.Constants
import com.andreinsigne.redditclientapp.utils.roundTo
import com.andreinsigne.redditclientapp.utils.toDateFromUTC
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.adapter_reddit_comments.view.*
import kotlinx.android.synthetic.main.adapter_reddit_feed.view.*


class RedditDetailCommentsAdapter(feedChildrenListing_ : FeedChildrenListing, comments_ : List<Comments>) : RecyclerView.Adapter<RedditDetailCommentsAdapter.RedditDetailHolder>()  {

    val feedChildrenListing = feedChildrenListing_

    var comments = comments_

    var view : RedditDetailCommentsView? = null

    val headerView = 0

    val feedView = 1

    /**
     * getItemViewType returns the view type to display a different viewholder depending on the one returned
     * one for the headerview used in the user profile only at the top and other for displaying the apps
     * @param position as Int which is the current position of the view
     */
    override fun getItemViewType(position: Int): Int {
        return if (position == 0)
            headerView
        else feedView
    }




    override fun onCreateViewHolder(p0: ViewGroup,  viewType: Int): RedditDetailHolder {
        var inflatedView : View? = null
        return when (viewType) {
            headerView -> {
                inflatedView = LayoutInflater.from(p0.context)
                    .inflate(R.layout.adapter_reddit_feed, p0, false)
                FeedDataHolder(inflatedView)
            }

            else -> {
                inflatedView = LayoutInflater.from(p0.context)
                    .inflate(R.layout.adapter_reddit_comments, p0, false)
                RedditCommentHolder(inflatedView)
            }
        }
    }

    override fun getItemCount(): Int {
        return comments.size + 1
    }

    open inner class RedditDetailHolder(detailView_: View) : RecyclerView.ViewHolder(detailView_)
    {

    }

    inner class RedditCommentHolder(commentView_: View) : RedditDetailHolder(commentView_)
    {
        val commentView = commentView_

        fun bind(comment : Comments)
        {
            if(comment.data?.children != null && comment.data?.children!!.size > 0) {
                commentView.tv_comment_hour.text =
                    comment.data?.children!![0].data?.created_utc?.toDateFromUTC()
                commentView.tv_comment_posted.text = comment.data?.children!![0].data?.body
                commentView.tv_comment_username.text = comment.data?.children!![0].data?.author
                commentView.tv_comment_up.text = comment.data?.children!![0].data?.ups.toString()
                if(comment.data?.children!![0].data?.ups != null && comment.data?.children!![0].data?.ups!! > 1000)
                {
                    val ups = (comment.data?.children!![0].data?.ups!! / 1000).roundTo(0)
                    commentView.tv_comment_up.text = "$ups k"
                }
            }
//            doAsync {
//                val bgurl = URL(subreddit.data?.banner_img)
//                val bgstream = BitmapFactory.decodeStream(bgurl.openConnection().getInputStream())
//                val dpurl = URL(subreddit.data?.banner_img)
//                val dpstream = BitmapFactory.decodeStream(dpurl.openConnection().getInputStream())
//                uiThread {
//                    detailView.iv_reddit_header_bg.setImageBitmap(bgstream)
//                    detailView.iv_reddit_default_pic.setImageBitmap(dpstream)
//                    detailView.tv_reddit_header_name.text = subreddit.data?.display_name_prefixed
//                    detailView.tv_reddit_desc.text = subreddit.data?.public_description
//                    detailView.tv_reddit_subscribers.text = subreddit.data?.subscribers?.toString()
//                    detailView.tv_reddit_online.text = subreddit.data?.active_user_count?.toString()
//                }
//            }

        }
    }

    fun updateAdapter()
    {
        this.notifyDataSetChanged()
    }


    /**
     * FeedDataHolder as custom view holder which will define and bind the
     * data of the app to be displayed on the view
     * @param feedView_ as View that contains information of the feed such as details , subreddit name and the content
     */
    inner class FeedDataHolder(feedView_: View) : RedditDetailHolder(feedView_) {

        private val feedView = feedView_




        fun bind(child : FeedChildrenListing) {




            Glide.with(feedView.context).load(Constants.getLink(child)).into(feedView.iv_reddit_display_image)
            feedView.tv_hour.text = child.data?.created_utc?.toDateFromUTC()
            feedView.tv_num_comments.text = child.data?.num_comments.toString()
            feedView.tv_num_upvotes.text = child.data?.ups.toString()
            feedView.tv_num_awards.text = child.data?.total_awards_received.toString()
            if(child.data?.num_comments != null && child.data?.num_comments!! > 1000)
            {
                val comD = (child.data?.num_comments!!/1000).toDouble().roundTo(0)
                feedView.tv_num_comments.text = "$comD k"
            }
            if(child.data?.ups != null && child.data?.ups!! > 1000)
            {
                val upsD = (child.data?.ups!!/1000).toDouble().roundTo(0)
                feedView.tv_num_upvotes.text = "$upsD k"
            }
            feedView.tv_reddit_name.text = child.data?.subreddit_name_prefixed
            feedView.tv_user_name.text = child.data?.display_name_prefixed
            feedView.tv_reddit_title.text = child.data?.title



            feedView.tv_reddit_name.setOnClickListener {
                if(child.data?.subreddit_name_prefixed != null)
                    view?.moveToDetails(child)
            }
            feedView.tv_user_name.setOnClickListener {
                if(child.data?.display_name_prefixed != null)
                    view?.moveToUser(child)
            }
            feedView.iv_reddit_display_image.setOnClickListener {
                view?.moveToPreview(child)
            }
        }
    }

    override fun onBindViewHolder(holder: RedditDetailHolder, position: Int) {

        if(position == 0)
            (holder as FeedDataHolder).bind(feedChildrenListing)
        else if(position >= 1)
            (holder as RedditCommentHolder).bind(comments[position - 1])
//            val data = feedListing?.data
//            if (data?.children != null) {
//                if(isPost)
//                    (holder as FeedDataHolder).bind(data.children!![position - 2])
//                else
//                    (holder as FeedDataHolder).bindRules(rules!!.rules!![position - 2])
//                (holder).bindFeed()
//            }
//        }

    }
}