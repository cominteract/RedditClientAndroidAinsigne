package com.andreinsigne.redditclientapp.ui.chooseinterest

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.andreinsigne.redditclientapp.R
import com.andreinsigne.redditclientapp.adapters.ChooseInterestAdapter
import com.andreinsigne.redditclientapp.base.BaseActivity
import com.andreinsigne.redditclientapp.base.BaseFragment
import com.andreinsigne.redditclientapp.utils.Config
import kotlinx.android.synthetic.main.fragment_choose_interest.*


/**
 * A simple [Fragment] subclass.

 * Use the [ChooseInterest.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ChooseInterestFragment : BaseFragment(), ChooseInterestView {

    var interests = ArrayList<String>()

    override fun interestChosenUpdateView(interest: String) {
        if(!interests.contains(interest))
            interests.add(interest)
        else
            interests.remove(interest)
    }

    /**
     * injector as ChooseInterestInjector injects the presenter with the services and data needed for the
     * implementation
     **/
    var injector = ChooseInterestInjectorImplementation()
    /**
     * presenter as ChooseInterestPresenter injected automatically to call implementations
     **/
    var presenter: ChooseInterestPresenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_interest, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(this.context)
        layoutManager.orientation = RecyclerView.VERTICAL
        val adapter = ChooseInterestAdapter()
        adapter.chooseInterestView = this
        rv_interest.adapter = adapter
        rv_interest.layoutManager = layoutManager
        btn_interest_submit.setOnClickListener {
            Config.updateInterest(interests)
            (context as BaseActivity).showNavigation(true)
            dismiss()
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment ChooseInterest.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            ChooseInterestFragment()
    }
}
