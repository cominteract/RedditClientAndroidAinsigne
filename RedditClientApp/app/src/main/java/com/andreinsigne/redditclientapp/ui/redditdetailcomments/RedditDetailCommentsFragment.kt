package com.andreinsigne.redditclientapp.ui.redditdetailcomments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.andreinsigne.redditclientapp.R
import com.andreinsigne.redditclientapp.adapters.RedditDetailCommentsAdapter
import com.andreinsigne.redditclientapp.base.BaseFragment
import com.andreinsigne.redditclientapp.model.Comments
import com.andreinsigne.redditclientapp.model.FeedChildrenListing
import com.andreinsigne.redditclientapp.ui.redditpreview.RedditPreviewFragment
import com.andreinsigne.redditclientapp.utils.UINavigation
import kotlinx.android.synthetic.main.fragment_reddit_detail_comments.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


/**
 * A simple [Fragment] subclass.

 * Use the [RedditDetailComments.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class RedditDetailCommentsFragment : BaseFragment(), RedditDetailCommentsView {
    var adapter : RedditDetailCommentsAdapter? = null

    override fun moveToDetails(feedChildrenListing_: FeedChildrenListing) {
        val bundle = Bundle()
        bundle.putString("subreddit",feedChildrenListing_.data?.subreddit_name_prefixed)

        addOnTopWithBundle(UINavigation.FeedDetailsKey, bundle)

    }


    override fun moveToPreview(feedChildrenListing_: FeedChildrenListing) {
        val fullScreen = RedditPreviewFragment.newInstance()
        fullScreen.feedChildrenListing = feedChildrenListing
        activity!!.supportFragmentManager
            .beginTransaction().setCustomAnimations(
                R.anim.slide_in_from_right, R.anim.slide_out_to_left,
                R.anim.slide_in_from_left, R.anim.slide_out_to_right
            )
            .replace(R.id.fragment_container, fullScreen, UINavigation.PreviewKey)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .addToBackStack(null)
            .commit()
    }


    override fun moveToUser(feedChildrenListing_: FeedChildrenListing) {
        val bundle = Bundle()
        bundle.putString("username", feedChildrenListing_.data?.display_name_prefixed)
        addOnTopWithBundle(UINavigation.AboutUserKey, bundle)
    }

    override fun retrievedCommentsUpdateView(comments: List<Comments>) {
        doAsync {
            uiThread {
                if(comments.isNotEmpty()) {
                    val layoutManager = LinearLayoutManager(it.context)
                    layoutManager.orientation = RecyclerView.VERTICAL
                    adapter = RedditDetailCommentsAdapter(feedChildrenListing!!, comments)
                    adapter?.view = it
                    rv_comments.adapter = adapter
                    rv_comments.layoutManager = layoutManager
                }
            }
        }

    }

    var feedChildrenListing : FeedChildrenListing? = null

    /**
     * injector as RedditDetailCommentsInjector injects the presenter with the services and data needed for the
     * implementation
     **/
    var injector = RedditDetailCommentsInjectorImplementation()
    /**
     * presenter as RedditDetailCommentsPresenter injected automatically to call implementations
     **/
    var presenter: RedditDetailCommentsPresenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reddit_detail_comments, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(arguments != null) {
            presenter?.startAPI(arguments!!.getString("subreddit"), arguments!!.getString("id"))
            //src_comments.setQuery(arguments!!.getString("subreddit"),false)
            edt_comments_search.setText(arguments!!.getString("subreddit"))
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment RedditDetailComments.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            RedditDetailCommentsFragment()
    }
}
