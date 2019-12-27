package com.andreinsigne.redditclientapp.ui.redditmenu

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.andreinsigne.redditclientapp.R
import com.andreinsigne.redditclientapp.base.BaseFragment


/**
 * A simple [Fragment] subclass.

 * Use the [RedditMenu.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class RedditMenuFragment : BaseFragment(), RedditMenuView {

    /**
     * injector as RedditMenuInjector injects the presenter with the services and data needed for the
     * implementation
     **/
    var injector = RedditMenuInjectorImplementation()
    /**
     * presenter as RedditMenuPresenter injected automatically to call implementations
     **/
    var presenter: RedditMenuPresenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reddit_menu, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment RedditMenu.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            RedditMenuFragment()
    }
}
