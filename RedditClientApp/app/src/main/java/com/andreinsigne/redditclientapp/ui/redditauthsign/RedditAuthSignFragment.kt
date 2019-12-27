package com.andreinsigne.redditclientapp.ui.redditauthsign

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.andreinsigne.redditclientapp.R
import com.andreinsigne.redditclientapp.base.BaseFragment
import com.andreinsigne.redditclientapp.utils.Config
import kotlinx.android.synthetic.main.fragment_reddit_auth_sign.*


/**
 * A simple [Fragment] subclass.

 * Use the [RedditAuthSign.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class RedditAuthSignFragment : BaseFragment(), RedditAuthSignView {

    /**
     * injector as RedditAuthSignInjector injects the presenter with the services and data needed for the
     * implementation
     **/
    var injector = RedditAuthSignInjectorImplementation()
    /**
     * presenter as RedditAuthSignPresenter injected automatically to call implementations
     **/
    var presenter: RedditAuthSignPresenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reddit_auth_sign, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_login.setOnClickListener {
            presenter?.startAuth()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment RedditAuthSign.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            RedditAuthSignFragment()
    }
}
