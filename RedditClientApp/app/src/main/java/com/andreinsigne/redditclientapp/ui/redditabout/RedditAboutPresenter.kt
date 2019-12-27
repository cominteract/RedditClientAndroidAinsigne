package com.andreinsigne.redditclientapp.ui.redditabout


import com.andreinsigne.redditclientapp.manager.APIManager
import com.andreinsigne.redditclientapp.manager.AuthManager
import com.andreinsigne.redditclientapp.model.Me


/**
 * RedditAboutView interface for updating the view in the fragments
 **/
interface RedditAboutView {
    fun retrievedMeUpdateView(me : Me)
}

/**
 * RedditAboutContract interface for delegating implementations from the RedditAboutServices
 **/
interface RedditAboutContract {
    fun retrievedMe(me : Me)
}

/**
 * RedditAboutPresenter interface for implementing the RedditAboutPresenter
 **/
interface RedditAboutPresenter {
    fun startInfo(username : String)
}


/**
 * RedditAboutPresenter implementation based on the presenter protocol.
 **/
class RedditAboutPresenterImplementation(
    view: RedditAboutView, apiManager: APIManager,
    authManager: AuthManager
) : RedditAboutPresenter, RedditAboutContract {

    override fun retrievedMe(me: Me) {
        view.retrievedMeUpdateView(me)
    }

    override fun startInfo(username: String) {
        service.startInfo(username)
    }

    var view: RedditAboutView

    var service: RedditAboutServices

    /**
     * initializes with the RedditAboutFragment as the RedditAboutView for updating
     * and authManager for consuming the authentication api and apiManager for consuming the api
     * related to data
     **/
    init {
        service = RedditAboutServices(apiManager, authManager)
        service.contract = this
        this.view = view
    }


}
