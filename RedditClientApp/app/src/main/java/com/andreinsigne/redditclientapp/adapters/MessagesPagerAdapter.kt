package com.andreinsigne.redditclientapp.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.andreinsigne.redditclientapp.model.FeedChildrenListing
import com.andreinsigne.redditclientapp.ui.redditfeedview.RedditFeedViewFragment
import com.andreinsigne.redditclientapp.ui.redditinbox.RedditInboxFragment
import com.andreinsigne.redditclientapp.ui.redditmessages.RedditMessagesFragment
import com.andreinsigne.redditclientapp.utils.Constants

class MessagesPagerAdapter(fm: FragmentManager):
    FragmentPagerAdapter(fm) {




    var msgs : Array<RedditMessagesFragment>? = null

    fun updateMessages(msgList : List<FeedChildrenListing>, actList : List<FeedChildrenListing>)
    {
        val msgPageOne = RedditMessagesFragment.newInstance(0, msgList)
        val msgPageTwo = RedditMessagesFragment.newInstance(1, actList)
        msgs = arrayOf(msgPageOne,msgPageTwo)
    }

    /**
     * getItem returns the RedditFeedViewFragment overridden for fragmentpageradapter
     * @return Fragment the RedditFeedViewFragment instance with position as basis for the filtering of feeds
     */
    override fun getItem(position: Int): Fragment? {
        if(msgs != null)
            return msgs!![position]
        return null
    }

    /**
     * getCount returns the size of the fragments in the view pager
     */

    override fun getCount(): Int {
        if(msgs != null)
            return msgs!!.count()
        return 0
    }

    /**
     * getPageTitle returns the title of the tabs
     * @param position as Int which is to identify what tab is being titled
     * @return the title as CharSequence
     */
    override fun getPageTitle(position: Int): CharSequence? = Constants.msgChoices[position].toUpperCase()

}