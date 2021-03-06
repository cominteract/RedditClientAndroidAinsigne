package com.andreinsigne.redditclientapp.ui.redditfilterfeedsearch

import com.andreinsigne.redditclientapp.data.DataInjector
import com.andreinsigne.redditclientapp.manager.ServicesInjector
import com.andreinsigne.redditclientapp.manager.APIManager
import com.andreinsigne.redditclientapp.manager.AuthManager

/**
 * injector interface template for injection of the services and data
 **/
interface RedditFilterFeedSearchInjector {
    fun inject(fragment: RedditFilterFeedSearchFragment)
}


/**
 * RedditFilterFeedSearchInjector injector implementation of the protocol template for injection
 **/
class RedditFilterFeedSearchInjectorImplementation() {

    /**
     * injects the ___VARIABLE_moduleName___ViewController generated with the services and the
     * presenter used data
     * @param fragment : RedditFilterFeedSearchFragment generated by the MVPTemplate
     **/
    fun inject(fragment: RedditFilterFeedSearchFragment) {
        val data = DataInjector().data
        val services = ServicesInjector(data)
        val presenter = RedditFilterFeedSearchPresenterImplementation(fragment, services.getAPI(), services.getAuth())
        fragment.presenter = presenter
    }


}
