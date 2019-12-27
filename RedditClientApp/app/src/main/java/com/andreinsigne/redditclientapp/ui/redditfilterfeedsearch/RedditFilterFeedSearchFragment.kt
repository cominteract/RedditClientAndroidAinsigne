package com.andreinsigne.redditclientapp.ui.redditfilterfeedsearch

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.andreinsigne.redditclientapp.R
import com.andreinsigne.redditclientapp.base.BaseFragment


/**
 * A simple [Fragment] subclass.

 * Use the [RedditFilterFeedSearch.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class RedditFilterFeedSearchFragment : BaseFragment(), RedditFilterFeedSearchView {

    /**
     * injector as RedditFilterFeedSearchInjector injects the presenter with the services and data needed for the
     * implementation
     **/
    var injector = RedditFilterFeedSearchInjectorImplementation()
    /**
     * presenter as RedditFilterFeedSearchPresenter injected automatically to call implementations
     **/
    var presenter: RedditFilterFeedSearchPresenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reddit_filter_feed_search, container, false)


    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment RedditFilterFeedSearch.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            RedditFilterFeedSearchFragment()
    }
}
