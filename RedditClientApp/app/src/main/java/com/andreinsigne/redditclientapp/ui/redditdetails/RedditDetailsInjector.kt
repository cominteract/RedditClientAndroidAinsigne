package com.andreinsigne.redditclientapp.ui.redditdetails

import com.andreinsigne.redditclientapp.data.DataInjector
import com.andreinsigne.redditclientapp.manager.ServicesInjector
import com.andreinsigne.redditclientapp.manager.APIManager
import com.andreinsigne.redditclientapp.manager.AuthManager

/**
 * injector interface template for injection of the services and data
 **/
interface RedditDetailsInjector {
    fun inject(fragment: RedditDetailsFragment)
}


/**
 * RedditDetailsInjector injector implementation of the protocol template for injection
 **/
class RedditDetailsInjectorImplementation() {

    /**
     * injects the ___VARIABLE_moduleName___ViewController generated with the services and the
     * presenter used data
     * @param fragment : RedditDetailsFragment generated by the MVPTemplate
     **/
    fun inject(fragment: RedditDetailsFragment) {
        val data = DataInjector().data
        val services = ServicesInjector(data)
        val presenter = RedditDetailsPresenterImplementation(fragment, services.getAPI(), services.getAuth())
        fragment.presenter = presenter
    }


}