package com.andreinsigne.redditclientapp.ui.redditfeed

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.andreinsigne.redditclientapp.R
import com.andreinsigne.redditclientapp.adapters.FeedAdapter
import com.andreinsigne.redditclientapp.adapters.FeedPagerAdapter
import com.andreinsigne.redditclientapp.adapters.SearchFeedAdapter
import com.andreinsigne.redditclientapp.base.BaseFragment
import com.andreinsigne.redditclientapp.model.FeedChildrenListing
import com.andreinsigne.redditclientapp.model.FeedListing
import com.andreinsigne.redditclientapp.model.Responses
import com.andreinsigne.redditclientapp.model.SearchSubreddit
import com.andreinsigne.redditclientapp.ui.redditdetails.RedditDetailsFragment
import com.andreinsigne.redditclientapp.utils.Constants
import com.andreinsigne.redditclientapp.utils.UINavigation
import kotlinx.android.synthetic.main.fragment_reddit_feed.*
import kotlinx.serialization.json.Json
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*
import kotlin.collections.ArrayList
import android.view.MotionEvent
import com.andreinsigne.redditclientapp.adapters.TrendingFeedAdapter
import com.andreinsigne.redditclientapp.utils.Config


enum class FeedType
{
    SearchTrending,
    Results,
    Recent,
    List,
    Default,
    Trending
}

enum class FilterType
{
    News,
    Popular,
    Home
}
/**
 * A simple [Fragment] subclass.

 * Use the [RedditFeed.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class RedditFeedFragment : BaseFragment(), RedditFeedView {



    var filterType : FilterType? = FilterType.Home
    var homeList: FeedListing? = null
    var newsList : FeedListing? = null
    var popularList : FeedListing? = null
    var subscribedList : FeedListing? = null
    var trendingList : FeedListing? = null
    var currentIndex = 1
    var filteredResults : ArrayList<FeedChildrenListing>? = null
    var searchedSubreddit : SearchSubreddit? = null
    var timer : Timer? = null
    var allowed = true
    var recentsSearch = ArrayList<String>()
    var pagerAdapter : FeedPagerAdapter? = null


    override fun retrievedBestUpdateView(listing: FeedListing) {
        homeList = listing
    }


    override fun retrievedNewsUpdateView(listing: FeedListing) {
        newsList = listing

    }

    override fun retrievedPopularUpdateView(listing: FeedListing) {
        popularList = listing

    }

    override fun retrievedHomeUpdateView(listing: FeedListing) {
        pagerAdapter!!.updateFeedListings(newsList)
        pagerAdapter!!.updateFeedListings(listing)
        pagerAdapter!!.updateFeedListings(homeList)
        homeList = listing
        doAsync { uiThread {
            pagerAdapter?.notifyDataSetChanged()
            if(tab_feed != null && tab_feed.tabCount > 0)
                tab_feed.getTabAt(0)?.select()
        } }

    }

    override fun retrievedTrendingUpdateView(listing: FeedListing) {
        trendingList = listing
        doAsync {
            uiThread {
                if(trendingList?.data?.children != null) {
                    trendingAdapter = TrendingFeedAdapter(trendingList?.data?.children!!)
                    val layoutManager = LinearLayoutManager(it.context)
                    layoutManager.orientation = RecyclerView.VERTICAL
                    rv_searched.adapter = trendingAdapter
                    rv_searched.layoutManager = layoutManager
                }
            }
        }
    }

    override fun retrievedSubscribedUpdateView(listing: FeedListing) {
    }


    var searchAdapter : SearchFeedAdapter? = null

    var trendingAdapter : TrendingFeedAdapter? = null

    override fun retrieveSearchedUpdateView(searched: SearchSubreddit) {
        searchAdapter = SearchFeedAdapter(searched)
        searchAdapter?.view = this
        val searchedString = edt_feed.text.toString()
        searchAdapter?.searches = Config.getInterests()?.filter { it.contains(searchedString) }?.toTypedArray()
        doAsync {
            uiThread {

                val layoutManager = LinearLayoutManager(it.context)
                layoutManager.orientation = RecyclerView.VERTICAL
                rv_searched.adapter = searchAdapter
                rv_searched.layoutManager = layoutManager
            }
        }

    }

    /**
     * injector as RedditFeedInjector injects the presenter with the services and data needed for the
     * implementation
     **/
    var injector = RedditFeedInjectorImplementation()
    /**
     * presenter as RedditFeedPresenter injected automatically to call implementations
     **/
    var presenter: RedditFeedPresenter? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reddit_feed, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter?.startAPI()
        Log.d(" View Created "," View Created ")
        btn_feed_cancel.visibility = View.GONE
        edt_feed.setOnTouchListener(View.OnTouchListener { arg0, arg1 ->

            if(edt_feed.text.toString().isEmpty() && trendingList != null)
            {
                rv_searched.visibility = View.VISIBLE
                tab_feed.visibility = View.GONE
                pager_feed.visibility = View.GONE
                btn_feed_cancel.visibility = View.VISIBLE
                doAsync {
                    uiThread {
                        val layoutManager = LinearLayoutManager(it.context)
                        layoutManager.orientation = RecyclerView.VERTICAL
                        rv_searched.adapter = trendingAdapter
                        rv_searched.layoutManager = layoutManager
                    }
                }
            }
            false
        })

        edt_feed.addTextChangedListener( object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if(s.toString().isNotEmpty()) {
                    presenter?.startSearch(s.toString())
                    btn_feed_cancel.visibility = View.VISIBLE
                }
                else
                {
                    rv_searched.visibility = View.GONE
                    tab_feed.visibility = View.VISIBLE
                    pager_feed.visibility = View.VISIBLE
                    btn_feed_cancel.visibility = View.GONE
                    edt_feed.clearFocus()
                }

            }
        })

        btn_feed_cancel.setOnClickListener {
            rv_searched.visibility = View.GONE
            tab_feed.visibility = View.VISIBLE
            pager_feed.visibility = View.VISIBLE
            btn_feed_cancel.visibility = View.GONE
            edt_feed.clearFocus()

        }
        tab_feed.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if(tab != null) {
                    Log.d(" Chosen this "," Chosen this ${Constants.tabChoices[tab.position]}")

                }
            }
        })

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        pagerAdapter = FeedPagerAdapter(childFragmentManager)

        pager_feed.adapter = pagerAdapter
        tab_feed.setupWithViewPager(pager_feed)


    }


    override fun moveToDetails(sr: String) {
        val bundle = Bundle()
        bundle.putString("subreddit",sr)

        addOnTopWithBundle(UINavigation.FeedDetailsKey, bundle)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment RedditFeed.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            RedditFeedFragment()
    }
}
