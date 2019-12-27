package com.andreinsigne.redditclientapp.ui.redditoauth

import com.andreinsigne.redditclientapp.data.DataInjector
import com.andreinsigne.redditclientapp.manager.ServicesInjector
import com.andreinsigne.redditclientapp.manager.APIManager
import com.andreinsigne.redditclientapp.manager.AuthManager

/**
 * injector interface template for injection of the services and data
 **/
interface RedditOAuthInjector {
    fun inject(fragment: RedditOAuthFragment)
}


/**
 * RedditOAuthInjector injector implementation of the protocol template for injection
 **/
class RedditOAuthInjectorImplementation() {

    /**
     * injects the ___VARIABLE_moduleName___ViewController generated with the services and the
     * presenter used data
     * @param fragment : RedditOAuthFragment generated by the MVPTemplate
     **/
    fun inject(fragment: RedditOAuthFragment) {
        val data = DataInjector().data
        val services = ServicesInjector(data)
        val presenter = RedditOAuthPresenterImplementation(fragment, services.getAPI(), services.getAuth())
        fragment.presenter = presenter
    }


}
