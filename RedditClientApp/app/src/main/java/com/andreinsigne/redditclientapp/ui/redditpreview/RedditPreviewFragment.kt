package com.andreinsigne.redditclientapp.ui.redditpreview

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.andreinsigne.redditclientapp.R
import com.andreinsigne.redditclientapp.base.BaseFragment
import com.andreinsigne.redditclientapp.model.FeedChildrenListing
import com.andreinsigne.redditclientapp.ui.redditdetailcomments.RedditDetailCommentsFragment
import com.andreinsigne.redditclientapp.utils.Constants
import com.andreinsigne.redditclientapp.utils.UINavigation
import com.andreinsigne.redditclientapp.utils.toDateFromUTC
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_reddit_preview.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL


/**
 * A simple [Fragment] subclass.

 * Use the [RedditPreview.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class RedditPreviewFragment : BaseFragment(), RedditPreviewView {

    /**
     * injector as RedditPreviewInjector injects the presenter with the services and data needed for the
     * implementation
     **/
    var injector = RedditPreviewInjectorImplementation()
    /**
     * presenter as RedditPreviewPresenter injected automatically to call implementations
     **/
    var presenter: RedditPreviewPresenter? = null

    var feedChildrenListing : FeedChildrenListing? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reddit_preview, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(feedChildrenListing != null)
        {
            tv_reddit_preview_desc.text = feedChildrenListing?.data?.title
            tv_preview_comment.text = feedChildrenListing?.data?.num_comments.toString()
            if(feedChildrenListing?.data?.num_comments != null && feedChildrenListing?.data?.num_comments!! > 1000)
                tv_preview_comment.text = (feedChildrenListing?.data?.num_comments!! / 1000).toString()
            tv_preview_upvote.text = feedChildrenListing?.data?.ups.toString()
            if(feedChildrenListing?.data?.ups != null && feedChildrenListing?.data?.ups!! > 1000)
                tv_preview_upvote.text = (feedChildrenListing?.data?.ups!! / 1000).toString()
            tv_reddit_preview_name.text = feedChildrenListing?.data?.subreddit_name_prefixed
            tv_reddit_preview_timestamp.text = feedChildrenListing?.data?.created_utc?.toDateFromUTC()
            tv_reddit_preview_user_name.text = feedChildrenListing?.data?.author
            tv_reddit_preview_name.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("subreddit",feedChildrenListing?.data?.subreddit_name_prefixed)
                addOnTopWithBundle(UINavigation.FeedDetailsKey, bundle)
            }
            tv_reddit_preview_user_name.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("username", feedChildrenListing?.data?.author)
                addOnTopWithBundle(UINavigation.AboutUserKey, bundle)
            }
            tv_preview_comment.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("subreddit",feedChildrenListing?.data?.subreddit_name_prefixed)
                bundle.putString("id",feedChildrenListing?.data?.id)
                val redditComments = RedditDetailCommentsFragment.newInstance()
                redditComments.arguments = bundle
                redditComments.feedChildrenListing = feedChildrenListing
                activity!!.supportFragmentManager
                    .beginTransaction().setCustomAnimations(
                        R.anim.slide_in_from_right, R.anim.slide_out_to_left,
                        R.anim.slide_in_from_left, R.anim.slide_out_to_right
                    )
                    .replace(R.id.fragment_container, redditComments, UINavigation.CommentsKey)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null)
                    .commit()
            }
            iv_preview_comment.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("subreddit",feedChildrenListing?.data?.subreddit_name_prefixed)
                bundle.putString("id",feedChildrenListing?.data?.id)
                val redditComments = RedditDetailCommentsFragment.newInstance()
                redditComments.arguments = bundle
                redditComments.feedChildrenListing = feedChildrenListing
                activity!!.supportFragmentManager
                    .beginTransaction().setCustomAnimations(
                        R.anim.slide_in_from_right, R.anim.slide_out_to_left,
                        R.anim.slide_in_from_left, R.anim.slide_out_to_right
                    )
                    .replace(R.id.fragment_container, redditComments, UINavigation.CommentsKey)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null)
                    .commit()
            }
            if(context != null)
                Glide.with(context!!).load(Constants.getLink(feedChildrenListing!!)).into(iv_preview_image)

        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment RedditPreview.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            RedditPreviewFragment()
    }
}
