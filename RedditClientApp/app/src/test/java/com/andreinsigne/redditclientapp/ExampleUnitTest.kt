package com.andreinsigne.redditclientapp

import com.andreinsigne.redditclientapp.manager.ListingRetrieved
import com.andreinsigne.redditclientapp.model.FeedListing
import com.andreinsigne.redditclientapp.model.Responses
import com.andreinsigne.redditclientapp.utils.APIEndpoint
import com.andreinsigne.redditclientapp.utils.ClosureResults
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {



    @Test
    fun closureTest()
    {

        val trending = Responses().trending() + Responses().trending2() + Responses().trending3()
        val news = Responses().news() + Responses().news2()
        val retrieved = object : ListingRetrieved{
            override fun didRetrievedError(error: Error, revoked: Boolean) {
                assert(false)
            }

            override fun didRetrievedListing(feedListing: FeedListing, apiEndpoint: APIEndpoint) {
                assert(true)
            }
        }
        ClosureResults.apiResults(APIEndpoint.News, news, retrieved)
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}
