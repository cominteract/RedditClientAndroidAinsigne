package com.andreinsigne.redditclientapp.ui.chooseinterest

import com.andreinsigne.redditclientapp.R
import com.andreinsigne.redditclientapp.manager.APIManager
import com.andreinsigne.redditclientapp.manager.AuthManager

/**
 * ChooseInterestServices for implementing the services needed for the presenter.
 **/
class ChooseInterestServices(
    apiManager: APIManager,
    authManager: AuthManager
) {
    /**
     * apiManager used in consuming the api related to data whether mock or from aws to be initialized
     **/
    var apiManager: APIManager = apiManager
    /**
     * authManager used in consuming the authentication api whether mock or from aws to be initialized
     **/
    var authManager: AuthManager = authManager
    /**
     * ChooseInterestView reference of the ChooseInterestFragment
     * as ChooseInterestView type. Must be weak
     **/
    var contract: ChooseInterestContract? = null


}
