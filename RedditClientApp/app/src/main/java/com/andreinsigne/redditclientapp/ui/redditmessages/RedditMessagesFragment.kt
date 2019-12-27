package com.andreinsigne.redditclientapp.ui.redditmessages

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.andreinsigne.redditclientapp.R
import com.andreinsigne.redditclientapp.adapters.ActivitiesFeedAdapter
import com.andreinsigne.redditclientapp.adapters.FeedAdapter
import com.andreinsigne.redditclientapp.adapters.MessagesFeedAdapter
import com.andreinsigne.redditclientapp.adapters.MessagesPagerAdapter
import com.andreinsigne.redditclientapp.base.BaseFragment
import com.andreinsigne.redditclientapp.model.FeedChildrenListing
import com.andreinsigne.redditclientapp.model.FeedListing
import com.andreinsigne.redditclientapp.model.Responses
import kotlinx.android.synthetic.main.fragment_reddit_inbox.*
import kotlinx.android.synthetic.main.fragment_reddit_messages.*
import kotlinx.serialization.json.Json


/**
 * A simple [Fragment] subclass.

 * Use the [RedditMessages.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class RedditMessagesFragment : BaseFragment(), RedditMessagesView {

    /**
     * injector as RedditMessagesInjector injects the presenter with the services and data needed for the
     * implementation
     **/
    var injector = RedditMessagesInjectorImplementation()
    /**
     * presenter as RedditMessagesPresenter injected automatically to call implementations
     **/
    var presenter: RedditMessagesPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reddit_messages, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(this.context)
        layoutManager.orientation = RecyclerView.VERTICAL
        if(arguments != null && msgList != null) {
            val position = arguments!!.getInt("msg_type")
            if(position == 0)
            {
                var adapter = MessagesFeedAdapter(msgList!!)
                rv_msgs.adapter = adapter
            }
            else
            {
                var adapter = ActivitiesFeedAdapter(msgList!!)
                rv_msgs.adapter = adapter
            }
            rv_msgs.layoutManager = layoutManager
        }
    }



    companion object {


        var msgList : List<FeedChildrenListing>? = null

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment RedditMessages.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(position : Int, list : List<FeedChildrenListing>) : RedditMessagesFragment{
            msgList = list
            var inbox = RedditMessagesFragment()
            var args = Bundle()
            args.putInt("msg_type",position)
            inbox.arguments = args
            return inbox
        }
    }
}
