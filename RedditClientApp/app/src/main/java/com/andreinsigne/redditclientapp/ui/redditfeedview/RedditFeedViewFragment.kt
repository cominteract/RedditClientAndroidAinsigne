package com.andreinsigne.redditclientapp.ui.redditfeedview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.andreinsigne.redditclientapp.R
import com.andreinsigne.redditclientapp.adapters.FeedAdapter
import com.andreinsigne.redditclientapp.base.BaseFragment
import com.andreinsigne.redditclientapp.model.FeedChildrenListing
import com.andreinsigne.redditclientapp.model.FeedListing
import com.andreinsigne.redditclientapp.model.Responses
import com.andreinsigne.redditclientapp.model.SearchSubreddit
import com.andreinsigne.redditclientapp.ui.redditdetailcomments.RedditDetailCommentsFragment
import com.andreinsigne.redditclientapp.ui.redditpreview.RedditPreviewFragment
import com.andreinsigne.redditclientapp.utils.*
import kotlinx.android.synthetic.main.fragment_reddit_feed_view.*
import kotlinx.serialization.json.Json
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.sql.Time
import java.util.*
import kotlin.collections.ArrayList


/**
 * A simple [Fragment] subclass.

 * Use the [RedditFeedView.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class RedditFeedViewFragment : BaseFragment(), RedditFeedViewView {


    override fun moveToComments(sr: String, feedChildrenListing: FeedChildrenListing) {
        val bundle = Bundle()
        bundle.putString("subreddit",sr)
        bundle.putString("id",feedChildrenListing.data?.id)
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

    override fun moveToDetails(sr: String ,feedChildrenListing: FeedChildrenListing) {

        val bundle = Bundle()
        bundle.putString("subreddit",sr)

        addOnTopWithBundle(UINavigation.FeedDetailsKey, bundle)

    }

    override fun moveToUser(username: String) {

        val bundle = Bundle()
        bundle.putString("username", username)
        addOnTopWithBundle(UINavigation.AboutUserKey, bundle)

    }

    override fun moveToFullscreen(sr: String, feedChildrenListing: FeedChildrenListing) {

        val fullScreen = RedditPreviewFragment.newInstance()
        fullScreen.feedChildrenListing = feedChildrenListing
        activity!!.supportFragmentManager
            .beginTransaction().setCustomAnimations(
                R.anim.slide_in_from_right, R.anim.slide_out_to_left,
                R.anim.slide_in_from_left, R.anim.slide_out_to_right
            )
            .replace(R.id.fragment_container, fullScreen, UINavigation.PreviewKey)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .addToBackStack(null)
            .commit()
    }


    /**
     * injector as RedditFeedViewInjector injects the presenter with the services and data needed for the
     * implementation
     **/
    var injector = RedditFeedViewInjectorImplementation()
    /**
     * presenter as RedditFeedViewPresenter injected automatically to call implementations
     **/
    var presenter: RedditFeedViewPresenter? = null

    var children : ArrayList<FeedChildrenListing>? = null
    var adapter : FeedAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reddit_feed_view, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(this.context)
        layoutManager.orientation = RecyclerView.VERTICAL

        if(currentListings != null && arguments != null) {
            val pos = arguments!!.getInt("feed_type_pos")
            adapter = currentListings!![pos]?.let { FeedAdapter(it.data?.children!!) }
            rv_feed.adapter = adapter
            rv_feed.layoutManager = layoutManager
            adapter?.view = this

        }
        else if(children != null)
        {

            adapter = FeedAdapter(children!!)
            rv_feed.adapter = adapter
            rv_feed.layoutManager = layoutManager
            adapter?.view = this
        }


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment RedditFeedView.
         */

        var currentListings : ArrayList<FeedListing?>? = null



        @JvmStatic
        fun newInstance() : RedditFeedViewFragment
        {
            return RedditFeedViewFragment()
        }

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(position : Int, feedListings : ArrayList<FeedListing?>) : RedditFeedViewFragment{
            val args = Bundle()
            if(feedListings.size > position)
                currentListings = feedListings
            args.putString(Constants.feed_bundle, Constants.tabChoices[position])
            args.putInt("feed_type_pos",position)
            val redditFeedView = RedditFeedViewFragment()
            redditFeedView.arguments = args
            return redditFeedView
        }

    }
}
