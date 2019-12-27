package com.andreinsigne.redditclientapp.ui.redditbrowse

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.andreinsigne.redditclientapp.R
import com.andreinsigne.redditclientapp.base.BaseFragment


/**
 * A simple [Fragment] subclass.

 * Use the [RedditBrowse.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class RedditBrowseFragment : BaseFragment(), RedditBrowseView {

    /**
     * injector as RedditBrowseInjector injects the presenter with the services and data needed for the
     * implementation
     **/
    var injector = RedditBrowseInjectorImplementation()
    /**
     * presenter as RedditBrowsePresenter injected automatically to call implementations
     **/
    var presenter: RedditBrowsePresenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reddit_browse, container, false)


    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment RedditBrowse.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            RedditBrowseFragment()
    }
}
