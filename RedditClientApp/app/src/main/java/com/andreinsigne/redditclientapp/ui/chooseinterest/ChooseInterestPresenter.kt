package com.andreinsigne.redditclientapp.ui.chooseinterest


import com.andreinsigne.redditclientapp.manager.APIManager
import com.andreinsigne.redditclientapp.manager.AuthManager


/**
 * ChooseInterestView interface for updating the view in the fragments
 **/
interface ChooseInterestView {
    fun interestChosenUpdateView(interest : String)
}

/**
 * ChooseInterestContract interface for delegating implementations from the ChooseInterestServices
 **/
interface ChooseInterestContract {

}

/**
 * ChooseInterestPresenter interface for implementing the ChooseInterestPresenter
 **/
interface ChooseInterestPresenter {

}


/**
 * ChooseInterestPresenter implementation based on the presenter protocol.
 **/
class ChooseInterestPresenterImplementation(
    view: ChooseInterestView, apiManager: APIManager,
    authManager: AuthManager
) : ChooseInterestPresenter, ChooseInterestContract {

    var view: ChooseInterestView

    var service: ChooseInterestServices

    /**
     * initializes with the ChooseInterestFragment as the ChooseInterestView for updating
     * and authManager for consuming the authentication api and apiManager for consuming the api
     * related to data
     **/
    init {
        service = ChooseInterestServices(apiManager, authManager)
        service.contract = this
        this.view = view
    }


}
