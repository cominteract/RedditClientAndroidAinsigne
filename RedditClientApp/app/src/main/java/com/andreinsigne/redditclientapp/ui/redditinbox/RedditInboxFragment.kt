package com.andreinsigne.redditclientapp.ui.redditinbox

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.andreinsigne.redditclientapp.R
import com.andreinsigne.redditclientapp.adapters.MessagesPagerAdapter
import com.andreinsigne.redditclientapp.base.BaseFragment
import com.andreinsigne.redditclientapp.model.FeedChildrenListing
import kotlinx.android.synthetic.main.fragment_reddit_inbox.*
import kotlinx.android.synthetic.main.fragment_reddit_messages.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


/**
 * A simple [Fragment] subclass.

 * Use the [RedditInbox.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class RedditInboxFragment : BaseFragment(), RedditInboxView {

    var inboxList :  List<FeedChildrenListing>? = null

    var activitiesList :  List<FeedChildrenListing>? = null

    var pagerAdapter : MessagesPagerAdapter? = null

    override fun retrievedInboxUpdateView(listing: List<FeedChildrenListing>) {
        inboxList = listing

    }

    override fun retrievedActivitiesUpdateView(listing: List<FeedChildrenListing>) {
        activitiesList = listing
        doAsync {
            uiThread {
                if(inboxList != null) {
                    pagerAdapter?.updateMessages(inboxList!!, listing)
                    pagerAdapter?.notifyDataSetChanged()
                }
            }
        }


    }

    /**
     * injector as RedditInboxInjector injects the presenter with the services and data needed for the
     * implementation
     **/
    var injector = RedditInboxInjectorImplementation()
    /**
     * presenter as RedditInboxPresenter injected automatically to call implementations
     **/
    var presenter: RedditInboxPresenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reddit_inbox, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        pagerAdapter = MessagesPagerAdapter(childFragmentManager)

        pager_inbox.adapter = pagerAdapter
        tab_inbox.setupWithViewPager(pager_inbox)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter?.startAPI()
    }
//

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment RedditInbox.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = RedditInboxFragment()

    }
}
