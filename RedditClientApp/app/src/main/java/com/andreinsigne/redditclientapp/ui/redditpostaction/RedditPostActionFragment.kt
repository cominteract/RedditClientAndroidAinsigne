package com.andreinsigne.redditclientapp.ui.redditpostaction

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.andreinsigne.redditclientapp.R
import com.andreinsigne.redditclientapp.base.BaseFragment
import com.andreinsigne.redditclientapp.model.FeedChildrenListing
import kotlinx.android.synthetic.main.fragment_reddit_post_action.*
import android.widget.ArrayAdapter
import android.widget.Toast
import com.andreinsigne.redditclientapp.utils.PostType
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


/**
 * A simple [Fragment] subclass.

 * Use the [RedditPostAction.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class RedditPostActionFragment : BaseFragment(), RedditPostActionView {

    var currentPostType : PostType = PostType.Text

    override fun postedSuccessfullyUpdateView(message: String) {
        doAsync {
            uiThread {
                Toast.makeText(it.context, message, Toast.LENGTH_LONG).show()
                dismiss()
            }
        }

    }

    override fun retrievedSubredditsUpdateView(listing: List<FeedChildrenListing>) {
        doAsync {
            uiThread {
                var map = ArrayList<String>()
                var associated = ArrayList<String>()
                for(value in listing)
                {
                    if(value.data?.display_name != null)
                        map.add(value.data?.display_name!!)
                }
                for(value in listing)
                {
                    if(value.data?.id != null)
                        associated.add(value.data?.id!!)
                }

                val dataAdapter = ArrayAdapter<String>(
                    it.context,
                    android.R.layout.simple_spinner_item, map.toTypedArray()
                )
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spn_community.adapter = dataAdapter
                btn_post_action.setOnClickListener {


//                    presenter?.postText("Look at this!",
//                        "http://github.com/reddit/reddi", "programming", PostType.Link)
                    presenter?.postText(edt_post_action_title.text.toString(),
                            edt_post_action_body.text.toString(), spn_community.selectedItem.toString() , currentPostType)
                }
            }
        }
    }

    /**
     * injector as RedditPostActionInjector injects the presenter with the services and data needed for the
     * implementation
     **/
    var injector = RedditPostActionInjectorImplementation()
    /**
     * presenter as RedditPostActionPresenter injected automatically to call implementations
     **/
    var presenter: RedditPostActionPresenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reddit_post_action, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(arguments?.getString("post") != null && arguments?.getString("post") == "text") {
            currentPostType = PostType.Text
            edt_post_action_title.hint = "Title here"
            edt_post_action_body.hint = "Message here"
        }
        else if(arguments?.getString("post") != null && arguments?.getString("post") == "link") {
            currentPostType = PostType.Link
            edt_post_action_title.hint = "Title here"
            edt_post_action_body.hint = "Link here"
        }
        presenter?.startAPI()
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment RedditPostAction.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            RedditPostActionFragment()
    }
}
