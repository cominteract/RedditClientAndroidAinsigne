package com.andreinsigne.redditclientapp.ui.redditcommunity

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
import com.andreinsigne.redditclientapp.adapters.FilterFeedAdapter
import com.andreinsigne.redditclientapp.base.BaseFragment
import com.andreinsigne.redditclientapp.model.FeedChildrenListing
import com.andreinsigne.redditclientapp.model.FeedListing
import com.andreinsigne.redditclientapp.ui.redditfeedview.RedditFeedViewFragment
import com.andreinsigne.redditclientapp.utils.UINavigation
import kotlinx.android.synthetic.main.fragment_reddit_community.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


/**
 * A simple [Fragment] subclass.

 * Use the [RedditCommunity.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class RedditCommunityFragment : BaseFragment(), RedditCommunityView {

    var popularList : ArrayList<FeedChildrenListing>? = null

    var allList : ArrayList<FeedChildrenListing>? = null

    /**
     * injector as RedditCommunityInjector injects the presenter with the services and data needed for the
     * implementation
     **/
    var injector = RedditCommunityInjectorImplementation()
    /**
     * presenter as RedditCommunityPresenter injected automatically to call implementations
     **/
    var presenter: RedditCommunityPresenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reddit_community, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter?.startAPI()
    }

    override fun retrievedAllUpdateView(listing: FeedListing) {
        allList = listing.data?.children
    }

    override fun retrievedPopularUpdateView(listing: FeedListing) {
        popularList = listing.data?.children
    }

    override fun moveToAll() {
        val feedView = RedditFeedViewFragment.newInstance()
        Log.d(" All List "," All List $allList")
        feedView.children = allList
        activity!!.supportFragmentManager
            .beginTransaction().setCustomAnimations(
                R.anim.slide_in_from_right, R.anim.slide_out_to_left,
                R.anim.slide_in_from_left, R.anim.slide_out_to_right
            )
            .replace(R.id.fragment_container, feedView, UINavigation.PreviewKey)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .addToBackStack(null)
            .commit()
    }

    override fun moveToPopular() {
        val feedView = RedditFeedViewFragment.newInstance()
        Log.d(" Popular List "," Popular List $popularList")
        feedView.children = popularList
        activity!!.supportFragmentManager
            .beginTransaction().setCustomAnimations(
                R.anim.slide_in_from_right, R.anim.slide_out_to_left,
                R.anim.slide_in_from_left, R.anim.slide_out_to_right
            )
            .replace(R.id.fragment_container, feedView, UINavigation.PreviewKey)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .addToBackStack(null)
            .commit()
    }

    override fun moveToDetails(sr: String ,feedChildrenListing: FeedChildrenListing) {

        val bundle = Bundle()
        bundle.putString("subreddit",sr)

        addOnTopWithBundle(UINavigation.FeedDetailsKey, bundle)

    }

    override fun retrievedSubredditsUpdateView(listing: List<FeedChildrenListing>) {
        val layoutManager = LinearLayoutManager(this.context)
        layoutManager.orientation = RecyclerView.VERTICAL
        doAsync { uiThread {
                val adapter = FilterFeedAdapter(listing, true)
                adapter.view = it
                rv_community.adapter = adapter
                rv_community.layoutManager = layoutManager
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment RedditCommunity.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            RedditCommunityFragment()
    }
}
