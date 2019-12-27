package com.andreinsigne.redditclientapp

import com.andreinsigne.redditclientapp.manager.*
import com.andreinsigne.redditclientapp.model.*
import com.andreinsigne.redditclientapp.utils.APIEndpoint
import com.andreinsigne.redditclientapp.utils.ClosureResults
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RedditManagerTest {

    val apiManager = MockAPIManager()


    @Test
    fun feedTest()
    {

        //val trending = Responses().trending() + Responses().trending2() + Responses().trending3()
        val news = Responses().news() + Responses().news2()
        val retrieved = object : ListingRetrieved{
            override fun didRetrievedError(error: Error, revoked: Boolean) {
                assert(false)
            }

            override fun didRetrievedListing(feedListing: FeedListing, apiEndpoint: APIEndpoint) {
                assert(true)
            }
        }
        apiManager.startAPI(APIEndpoint.News, news, false, retrieved)
    }

    @Test
    fun searchTest()
    {
        val data = Responses().searchsub()
        val retrieved = object : SubredditSearched{
            override fun didRetrievedError(error: Error, revoked: Boolean) {
                assert(false)
            }

            override fun didRetrieveSearchedSub(
                searchSub: SearchSubreddit,
                apiEndpoint: APIEndpoint
            ) {
                assert(true)
            }
        }
        apiManager.startAPI(APIEndpoint.SearchSub, data, false, retrieved)


    }

    @Test
    fun rulesTest()
    {
        val data = Responses().subredditrules()
        val retrieved = object : RulesRetrieved{
            override fun didRetrievedError(error: Error, revoked: Boolean) {
                assert(false)
            }

            override fun didRetrieveRules(rules: Rules, apiEndpoint: APIEndpoint) {
                assert(true)
            }
        }
        apiManager.startAPI(APIEndpoint.AbtSubRules, data,false, retrieved)

    }

    @Test
    fun subredditInfoTest()
    {
        val data = Responses().subreddit() + Responses().subreddit2()
        val retrieved = object : SubredditRetrieved{
            override fun didRetrievedError(error: Error, revoked: Boolean) {
                assert(false)
            }

            override fun didRetrieveSubreddit(subreddit: Subreddit, apiEndpoint: APIEndpoint) {
                assert(true)
            }
        }
        apiManager.startAPI(APIEndpoint.AbtSub, data, false, retrieved)

    }

    @Test
    fun meTest()
    {
        val data = Responses().me()
        val retrieved = object : MeRetrieved{
            override fun didRetrievedError(error: Error, revoked: Boolean) {
                assert(false)
            }

            override fun didRetrieveMe(me: Me) {
                assert(true)
            }
        }
        apiManager.startAPI(APIEndpoint.Me, data, false, retrieved)

    }

    @Test
    fun commentsTest()
    {
        val data = Responses().comments() +
                Responses().comments2() +
                Responses().comments3() +
                Responses().comments4() +
                Responses().comments5() +
                Responses().comments6() +
                Responses().comments7() +
                Responses().comments8() +
                Responses().comments9()


        val retrieved = object : CommentsRetrieved{
            override fun didRetrievedError(error: Error, revoked: Boolean) {
                assert(false)
            }

            override fun didRetrievedComments(comments: List<Comments>) {
                assert(true)
            }
        }
        apiManager.startAPI(APIEndpoint.ArticleComments, data, false, retrieved)

    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}
