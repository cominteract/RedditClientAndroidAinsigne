package com.andreinsigne.redditclientapp.adapters

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.andreinsigne.redditclientapp.R
import com.andreinsigne.redditclientapp.ui.chooseinterest.ChooseInterestView

import com.andreinsigne.redditclientapp.utils.*
import kotlinx.android.synthetic.main.adapter_choose_interest.view.*



class ChooseInterestAdapter() : RecyclerView.Adapter<ChooseInterestAdapter.InterestDataHolder>()  {


    var chooseInterestView : ChooseInterestView? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): InterestDataHolder {
        val inflatedView = LayoutInflater.from(p0.context)
            .inflate(R.layout.adapter_choose_interest, p0, false)
        return InterestDataHolder(inflatedView)
    }

    override fun getItemCount(): Int {

        return 8
    }


    /**
     * FeedDataHolder as custom view holder which will define and bind the
     * data of the app to be displayed on the view
     * @param interestView_ as View that contains interest
     */
    open inner class InterestDataHolder(interestView_: View) : RecyclerView.ViewHolder(interestView_) {

        val interestView = interestView_
        val choices = arrayOf(4,3,4,4,3,3,4,4)
        val buttons = arrayOf(interestView.btn_choose_interest_1,interestView.btn_choose_interest_2,interestView.btn_choose_interest_3,interestView.btn_choose_interest_4)

        fun bind(position : Int) {
            val max = choices[position]
            val choicesPos = choices.take(position).sum()
            var selected = HashMap<String,Boolean>()
            for(i in 0 until max)
            {

                if(position == 0)
                    buttons[i].text = Constants.categories[position + i]
                else
                    buttons[i].text = Constants.categories[choicesPos + i]
                buttons[i].setOnClickListener {
                    if(selected.get((it as Button).text.toString()) == null) {
                        selected.put((it as Button).text.toString(), true)
                        (it as Button).setBackgroundColor(Color.BLUE)
                        (it as Button).setTextColor(Color.WHITE)
                    }
                    else {
                        selected.put((it as Button).text.toString(), !selected.get((it as Button).text.toString())!!)

                        if(selected.get((it as Button).text.toString())!!) {
                            (it as Button).setBackgroundColor(Color.BLUE)
                            (it as Button).setTextColor(Color.WHITE)
                        }
                        else {
                            (it as Button).setBackgroundColor(Color.LTGRAY)
                            (it as Button).setTextColor(Color.BLACK)
                        }
                    }
                    chooseInterestView?.interestChosenUpdateView((it as Button).text.toString())
                }
            }
            if(max == 3)
                buttons[3].visibility = View.GONE
        }
    }

    override fun onBindViewHolder(holder: InterestDataHolder, position: Int) {
            holder.bind(position)
    }
}