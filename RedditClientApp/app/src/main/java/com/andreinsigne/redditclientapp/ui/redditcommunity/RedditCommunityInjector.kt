package com.andreinsigne.redditclientapp.ui.redditcommunity

import com.andreinsigne.redditclientapp.data.DataInjector
import com.andreinsigne.redditclientapp.manager.ServicesInjector
import com.andreinsigne.redditclientapp.manager.APIManager
import com.andreinsigne.redditclientapp.manager.AuthManager

/**
 * injector interface template for injection of the services and data
 **/
interface RedditCommunityInjector {
    fun inject(fragment: RedditCommunityFragment)
}


/**
 * RedditCommunityInjector injector implementation of the protocol template for injection
 **/
class RedditCommunityInjectorImplementation() {

    /**
     * injects the ___VARIABLE_moduleName___ViewController generated with the services and the
     * presenter used data
     * @param fragment : RedditCommunityFragment generated by the MVPTemplate
     **/
    fun inject(fragment: RedditCommunityFragment) {
        val data = DataInjector().data
        val services = ServicesInjector(data)
        val presenter = RedditCommunityPresenterImplementation(fragment, services.getAPI(), services.getAuth())
        fragment.presenter = presenter
    }


}
