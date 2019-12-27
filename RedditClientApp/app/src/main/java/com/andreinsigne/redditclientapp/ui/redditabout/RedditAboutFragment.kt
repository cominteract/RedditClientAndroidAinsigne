package com.andreinsigne.redditclientapp.ui.redditabout

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.andreinsigne.redditclientapp.R
import com.andreinsigne.redditclientapp.base.BaseFragment
import com.andreinsigne.redditclientapp.model.Me
import com.andreinsigne.redditclientapp.utils.roundTo
import com.andreinsigne.redditclientapp.utils.toDateFromUTC
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_reddit_about.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL


/**
 * A simple [Fragment] subclass.

 * Use the [RedditAbout.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class RedditAboutFragment : BaseFragment(), RedditAboutView {

    override fun retrievedMeUpdateView(me: Me) {
        Log.d(" View Created "," View Created ${me}")
        Log.d(" View Created "," View Created ${me.data?.name}")
        Log.d(" View Created "," View Created ${me.data?.display_name_prefixed}")
        doAsync {
            uiThread {

                tv_reddit_user_name.text = me.data?.name
                if(me.data?.comment_karma != null)
                {
                    tv_reddit_user_commentkarma.text = me.data?.comment_karma!!.toString()
                    if(me.data?.comment_karma!! > 1000)
                    {
                        val karma = (me.data?.comment_karma!!.toDouble()/1000).roundTo(0)
                        tv_reddit_user_commentkarma.text = "$karma k"
                    }
                }
                if(me.data?.link_karma != null)
                {
                    tv_reddit_user_postkarma.text = me.data?.link_karma!!.toString()
                    if(me.data?.link_karma!! > 1000)
                    {
                        val karma = (me.data?.link_karma!!.toDouble()/1000).roundTo(0)
                        tv_reddit_user_postkarma.text = "$karma k"
                    }
                }
                tv_reddit_user_online.text = me.data?.created_utc?.toDateFromUTC()
            }
        }
        if(context != null)
            Glide.with(context!!).load(me.icon_img).into(iv_reddit_user_pic)

    }

    /**
     * injector as RedditAboutInjector injects the presenter with the services and data needed for the
     * implementation
     **/
    var injector = RedditAboutInjectorImplementation()
    /**
     * presenter as RedditAboutPresenter injected automatically to call implementations
     **/
    var presenter: RedditAboutPresenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reddit_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(arguments != null) {
            Log.d(" View Created "," View Created ${arguments!!.getString("username")}")
            presenter?.startInfo(arguments!!.getString("username"))
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment RedditAbout.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            RedditAboutFragment()
    }
}
