package com.andreinsigne.redditclientapp.ui.redditdetails

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.andreinsigne.redditclientapp.R
import com.andreinsigne.redditclientapp.adapters.FeedPagerAdapter
import com.andreinsigne.redditclientapp.adapters.RedditDetailsAdapter
import com.andreinsigne.redditclientapp.adapters.RedditDetailsPagerAdapter
import com.andreinsigne.redditclientapp.base.BaseFragment
import com.andreinsigne.redditclientapp.model.FeedListing
import com.andreinsigne.redditclientapp.model.Rules
import com.andreinsigne.redditclientapp.model.Subreddit
import com.andreinsigne.redditclientapp.utils.APIEndpoint
import kotlinx.android.synthetic.main.adapter_reddit_header.*
import kotlinx.android.synthetic.main.fragment_reddit_details.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL


/**
 * A simple [Fragment] subclass.

 * Use the [RedditDetails.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class RedditDetailsFragment : BaseFragment(), RedditDetailsView {

    var adapter : RedditDetailsAdapter? = null

    var listing : FeedListing? = null

    var rules : Rules? = null

    override fun retrievedPostsUpdateView(listing: FeedListing, endPoint: APIEndpoint) {
        this.listing = listing
        adapter?.feedListing = listing
        adapter?.notifyDataSetChanged()
    }

    override fun retrievedAboutSubredditUpdateView(subreddit: Subreddit, endPoint: APIEndpoint) {
        doAsync {
            uiThread {
                val layoutManager = LinearLayoutManager(it.context)
                layoutManager.orientation = RecyclerView.VERTICAL
                adapter = RedditDetailsAdapter(subreddit)
                if(rules != null)
                    adapter?.rules = rules
                if(listing != null)
                    adapter?.feedListing = listing
                rv_details.adapter = adapter
                rv_details.layoutManager = layoutManager
                adapter?.notifyDataSetChanged()
            }
        }

    }

    override fun retrievedAboutSubRulesUpdateView(rules: Rules) {
        this.rules = rules
        adapter?.rules = rules
        adapter?.notifyDataSetChanged()
    }

    /**
     * injector as RedditDetailsInjector injects the presenter with the services and data needed for the
     * implementation
     **/
    var injector = RedditDetailsInjectorImplementation()
    /**
     * presenter as RedditDetailsPresenter injected automatically to call implementations
     **/
    var presenter: RedditDetailsPresenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reddit_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(arguments != null) {
            presenter?.startAPI(arguments!!.getString("subreddit"))
            //src_details.setQuery(arguments!!.getString("subreddit"),false)
            edt_details_search.setText(arguments!!.getString("subreddit"))
        }
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment RedditDetails.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() : RedditDetailsFragment{

            val frag = RedditDetailsFragment()

            return frag
        }

    }
}
