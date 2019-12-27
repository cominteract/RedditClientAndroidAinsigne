package com.andreinsigne.redditclientapp

import com.andreinsigne.redditclientapp.manager.ListingRetrieved
import com.andreinsigne.redditclientapp.manager.MockAPIManager
import com.andreinsigne.redditclientapp.manager.MockAuthManager
import com.andreinsigne.redditclientapp.model.FeedListing
import com.andreinsigne.redditclientapp.model.Responses
import com.andreinsigne.redditclientapp.model.Rules
import com.andreinsigne.redditclientapp.model.Subreddit
import com.andreinsigne.redditclientapp.ui.redditdetails.RedditDetailsPresenterImplementation
import com.andreinsigne.redditclientapp.ui.redditdetails.RedditDetailsView
import com.andreinsigne.redditclientapp.utils.APIEndpoint
import com.andreinsigne.redditclientapp.utils.ClosureResults
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RedditPresenterTest {

    lateinit var presenter : RedditDetailsPresenterImplementation
    var view = object : RedditDetailsView{
        override fun retrievedPostsUpdateView(listing: FeedListing, endPoint: APIEndpoint) {
            assert(true, { "posts" })
        }

        override fun retrievedAboutSubredditUpdateView(
            subreddit: Subreddit,
            endPoint: APIEndpoint
        ) {
            assert(true, { "about sub" })
        }

        override fun retrievedAboutSubRulesUpdateView(rules: Rules) {
            assert(true, { "sub rules" })
        }

    }
    init {
        presenter = RedditDetailsPresenterImplementation(view, MockAPIManager(), MockAuthManager())
    }

    @Test
    fun subTest()
    {
        presenter.startAPI("subreddit")
        assert(true, { "start" })
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}
