package com.andreinsigne.redditclientapp.utils

import android.app.Dialog
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import com.andreinsigne.redditclientapp.R
import com.andreinsigne.redditclientapp.ui.chooseinterest.ChooseInterestFragment
import com.andreinsigne.redditclientapp.ui.redditabout.RedditAboutFragment
import com.andreinsigne.redditclientapp.ui.redditauthsign.RedditAuthSignFragment
import com.andreinsigne.redditclientapp.ui.redditchat.RedditChatContract
import com.andreinsigne.redditclientapp.ui.redditchat.RedditChatFragment
import com.andreinsigne.redditclientapp.ui.redditcommunity.RedditCommunityFragment
import com.andreinsigne.redditclientapp.ui.redditdetails.RedditDetailsFragment
import com.andreinsigne.redditclientapp.ui.redditfeed.RedditFeedFragment
import com.andreinsigne.redditclientapp.ui.redditfilterfeedsearch.RedditFilterFeedSearchFragment
import com.andreinsigne.redditclientapp.ui.redditinbox.RedditInboxFragment
import com.andreinsigne.redditclientapp.ui.redditpostaction.RedditPostActionFragment
import com.andreinsigne.redditclientapp.ui.redditpostfeed.RedditPostFeedFragment

/**
 * UINavigation fragment navigation helper that makes use of keys for accessing fragments
 */
class UINavigation
{
    companion object {

        /**
         * PostKey as String constant provided as key for navigation to sign in page
         */
        const val SignKey = "Sign"

        /**
         * HomeKey as String constant provided as key for navigation to home page
         */
        const val HomeKey = "Home"

        /**
         * FeedKey as String constant provided as key for navigation to feed page
         */
        const val FeedKey = "Feed"

        /**
         * SearchKey as String constant provided as key for navigation to search page
         */
        const val SearchKey = "Search"

        /**
         * PostKey as String constant provided as key for navigation to post feed page
         */
        const val PostFeedKey = "PostFeed"

        /**
         * ChatKey as String constant provided as key for navigation to chat page
         */
        const val ChatKey = "Chat"

        /**
         * ActivitiesKey as String constant provided as key for navigation to activities page
         */
        const val ActivitiesKey = "Activities"

        /**
         * PostActionKey as String constant provided as key for navigation to post action page
         */
        const val PostActionKey = "PostAction"

        /**
         * ChooseInterestKey as String constant provided as key for navigation to choose interest page
         */
        const val ChooseInterestKey = "ChooseInterest"
        /**
         * CommentsKey as String constant provided as key for navigation to comments page
         */
        const val CommentsKey = "Comments"
        /**
         * PreviewKey as String constant provided as key for navigation to preview page
         */
        const val PreviewKey = "Preview"

        /**
         * FeedDetailsKey as String constant provided as key for navigation to feed details page
         */
        const val FeedDetailsKey = "FeedDetails"

        /**
         * AboutUserKey as String constant provided as key for navigation to about user page
         */
        const val AboutUserKey = "AboutUser"


        /**
         * tabState from layoutId to determine that when tab is clicked
         * to return the key from the view pressed to be used in navigation
         * @param layoutId as Int id of the view pressed
         * @return String as key to be used in navigation
         */
        @JvmStatic
        fun tabState(layoutId : Int, logged : Boolean) : String =
            when(layoutId)
            {
                R.id.navigation_feed -> FeedKey
                R.id.navigation_search -> SearchKey
                R.id.navigation_activity -> ActivitiesKey

                R.id.navigation_post -> if(logged) PostFeedKey else SignKey
                R.id.navigation_chat -> ChatKey
                else -> HomeKey
            }

        /**
         * frag to return the fragment based on the key input
         * @param fragKey as String to be used in determining what fragment to navigate into
         * @return Fragment that is the instance of fragment to be navigated
         */
        @JvmStatic
        fun frag(fragKey: String) : Fragment =
            when (fragKey) {
                FeedKey -> RedditFeedFragment.newInstance()
                SearchKey -> RedditCommunityFragment.newInstance()
                PostFeedKey -> RedditPostFeedFragment.newInstance()
                PostActionKey -> RedditPostActionFragment.newInstance()
                ChatKey -> RedditChatFragment.newInstance()
                ActivitiesKey -> RedditInboxFragment.newInstance()
                SignKey -> RedditAuthSignFragment.newInstance()
                FeedDetailsKey -> RedditDetailsFragment.newInstance()
                AboutUserKey -> RedditAboutFragment.newInstance()
                ChooseInterestKey -> ChooseInterestFragment.newInstance()
                else -> RedditPostFeedFragment.newInstance()
            }


        /**
         * frag to return the fragment based on the key input
         * @param fragKey as String to be used in determining what fragment to navigate into
         * @return DialogFragment that is the instance of dialog fragment to be navigated
         */
        @JvmStatic
        fun dialogFrag(fragKey: String) : DialogFragment =
            when (fragKey) {
                FeedKey -> RedditPostFeedFragment.newInstance()

                else -> RedditPostFeedFragment.newInstance()
            }

    }
}
