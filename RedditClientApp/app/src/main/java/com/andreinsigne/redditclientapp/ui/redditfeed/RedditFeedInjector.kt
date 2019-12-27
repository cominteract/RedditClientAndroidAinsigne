package com.andreinsigne.redditclientapp.ui.redditfeed

import com.andreinsigne.redditclientapp.data.DataInjector
import com.andreinsigne.redditclientapp.manager.ServicesInjector
import com.andreinsigne.redditclientapp.manager.APIManager
import com.andreinsigne.redditclientapp.manager.AuthManager

/**
 * injector interface template for injection of the services and data
 **/
interface RedditFeedInjector {
    fun inject(fragment: RedditFeedFragment)
}


/**
 * RedditFeedInjector injector implementation of the protocol template for injection
 **/
class RedditFeedInjectorImplementation() {

    /**
     * injects the ___VARIABLE_moduleName___ViewController generated with the services and the
     * presenter used data
     * @param fragment : RedditFeedFragment generated by the MVPTemplate
     **/
    fun inject(fragment: RedditFeedFragment) {
        val data = DataInjector().data
        val services = ServicesInjector(data)
        val presenter = RedditFeedPresenterImplementation(fragment, services.getAPI(), services.getAuth())
        fragment.presenter = presenter
    }


}