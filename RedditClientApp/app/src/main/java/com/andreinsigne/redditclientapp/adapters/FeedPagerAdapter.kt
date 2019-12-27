package com.andreinsigne.redditclientapp.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.andreinsigne.redditclientapp.model.FeedListing
import com.andreinsigne.redditclientapp.ui.redditfeedview.RedditFeedViewFragment
import com.andreinsigne.redditclientapp.utils.Constants

class FeedPagerAdapter(fm: FragmentManager):
    FragmentPagerAdapter(fm) {

    var feedListings = ArrayList<FeedListing?>()




    private var feeds : Array<RedditFeedViewFragment>? = null

    fun updateFeedListings(feedListing: FeedListing?)
    {
        feedListings.add(feedListing)
        if(feedListings.size >= 3) {
            var feedPageOne = RedditFeedViewFragment.newInstance(0, feedListings)

            var feedPageTwo = RedditFeedViewFragment.newInstance(1, feedListings)

            var feedPageThree = RedditFeedViewFragment.newInstance(2, feedListings)
            feeds = arrayOf(feedPageOne, feedPageTwo, feedPageThree)
        }
    }


    /**
     * getItem returns the RedditFeedViewFragment overridden for fragmentpageradapter
     * @return Fragment the RedditFeedViewFragment instance with position as basis for the filtering of feeds
     */

    override fun getItem(position: Int): Fragment? {
        if(feeds != null)
            return feeds!![position]
        return null
    }

    /**
     * getCount returns the size of the fragments in the view pager
     */

    override fun getCount(): Int {
        if(feeds != null)
            return feeds!!.size
        return 0
    }

    /**
     * getPageTitle returns the title of the tabs
     * @param position as Int which is to identify what tab is being titled
     * @return the title as CharSequence
     */
    override fun getPageTitle(position: Int): CharSequence? = Constants.tabChoices[position].toUpperCase()

}