package com.andreinsigne.redditclientapp.ui.redditpostfeed

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager

import com.andreinsigne.redditclientapp.R
import com.andreinsigne.redditclientapp.base.BaseDialogFragment
import com.andreinsigne.redditclientapp.base.BaseFragment
import com.andreinsigne.redditclientapp.utils.UINavigation
import kotlinx.android.synthetic.main.fragment_reddit_post_feed.*
import org.jetbrains.anko.displayMetrics


/**
 * A simple [Fragment] subclass.

 * Use the [RedditPostFeed.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class RedditPostFeedFragment : BaseDialogFragment(), RedditPostFeedView {

    /**
     * injector as RedditPostFeedInjector injects the presenter with the services and data needed for the
     * implementation
     **/
    var injector = RedditPostFeedInjectorImplementation()
    /**
     * presenter as RedditPostFeedPresenter injected automatically to call implementations
     **/
    var presenter: RedditPostFeedPresenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reddit_post_feed, container, false)


    }

    override fun onResume() {
        super.onResume()
        val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
        if(activity?.displayMetrics?.density != null) {
            params?.width = (320.0 * activity?.displayMetrics?.density!!).toInt()
            params?.height = (250.0 * activity?.displayMetrics?.density!!).toInt()
            dialog?.window?.attributes = params as WindowManager.LayoutParams
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iv_post_link.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("post","link")
            addOnTopWithBundle(UINavigation.PostActionKey, bundle)
            dismiss()
        }
        iv_post_text.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("post","text")
            addOnTopWithBundle(UINavigation.PostActionKey, bundle)
            dismiss()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment RedditPostFeed.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            RedditPostFeedFragment()
    }
}
