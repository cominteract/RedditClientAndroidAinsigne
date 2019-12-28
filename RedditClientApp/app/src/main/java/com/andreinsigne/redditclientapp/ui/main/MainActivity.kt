package com.andreinsigne.redditclientapp.ui.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView

import android.widget.TextView
import com.andreinsigne.redditclientapp.R
import com.andreinsigne.redditclientapp.base.BaseActivity
import kotlinx.android.synthetic.main.content_main.*
import android.content.Intent


import android.net.Uri

import android.util.Log
import android.view.View
import com.andreinsigne.redditclientapp.manager.TokenRetrieve
import com.andreinsigne.redditclientapp.model.TokenResponse
import com.andreinsigne.redditclientapp.utils.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*


class MainActivity : MainView, BaseActivity() {




    var tokenRetrieve : TokenRetrieve? = null

    var ongoingRefresh = false

    /**
     * injector as RedditAuthSignInjector injects the presenter with the services and data needed for the
     * implementation
     **/
    var injector = MainImplementation()
    /**
     * presenter as MainPresenter injected automatically to call implementations
     **/
    var presenter: MainPresenter? = null

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        if(presenter != null) {

            loadFragment(UINavigation.tabState(item.itemId, presenter!!.isLogged()))
        }
        else
            loadFragment(UINavigation.tabState(item.itemId, false))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injector.inject(this)
        Config.context = this
        navigation.visibility = View.VISIBLE
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)



        if(Config.getInterests() == null) {
            addFragment(UINavigation.ChooseInterestKey)
            navigation.visibility = View.GONE
        }
        else if(presenter != null && !ongoingRefresh && Config.getRefreshDate() != null
            && Config.getRefreshDate()!!.oneHourPassed())
        {
            ongoingRefresh = true
            presenter!!.refreshToken()
        }
        else if(presenter != null && presenter!!.isLogged())
        {
            loadFragment(UINavigation.tabState(R.id.navigation_feed, true))
            navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        }
        else{
            loadFragment(UINavigation.tabState(R.id.navigation_feed, true))
            navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        }
    }

    override fun tokenRefreshedUpdateView() {
        ongoingRefresh = false
        doAsync {
            uiThread {
                if(presenter != null && presenter!!.isLogged())
                {
                    navigation.visibility = View.VISIBLE
                    loadFragment(UINavigation.tabState(R.id.navigation_feed, true))
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if(presenter != null && !ongoingRefresh && Config.getRefreshDate() != null && Config.getRefreshDate()!!.oneHourPassed()) {
            ongoingRefresh = true
            presenter!!.refreshToken()
        }
    }

    override fun onNewIntent(intent: Intent) {
        val uri = intent.data

        if (uri != null && uri.toString()
                .startsWith("rditai://response"))
        {
            val code = uri.getQueryParameter("code")?.replace("code=","")
            if(tokenRetrieve != null && code != null)
                Config.getToken(code , tokenRetrieve!!)
        }

    }
}
