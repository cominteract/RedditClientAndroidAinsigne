package com.andreinsigne.redditclientapp.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.andreinsigne.redditclientapp.base.BaseFragment
import com.andreinsigne.redditclientapp.model.FeedListing
import com.andreinsigne.redditclientapp.model.Rules
import com.andreinsigne.redditclientapp.model.Subreddit
import com.andreinsigne.redditclientapp.ui.redditaboutsub.RedditAboutSubFragment
import com.andreinsigne.redditclientapp.ui.redditfeedview.RedditFeedViewFragment
import com.andreinsigne.redditclientapp.utils.Constants

class RedditDetailsPagerAdapter(fm: FragmentManager):
    FragmentPagerAdapter(fm) {

    var feedListings = ArrayList<FeedListing?>()




    private var feeds : ArrayList<BaseFragment>? = null

    fun updateFeedListings(feedListing: FeedListing?)
    {
        feedListings.add(feedListing)
        val feedPageOne = RedditFeedViewFragment.newInstance(0, feedListings)
        feeds?.add(feedPageOne)
    }
    fun updateAboutRules(rules : Rules)
    {
        var feedPageOne = RedditAboutSubFragment.newInstance()
        feeds?.add(feedPageOne)
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
    override fun getPageTitle(position: Int): CharSequence? = Constants.subredditTabChoices[position].toUpperCase()

}