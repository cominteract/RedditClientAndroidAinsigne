package com.andreinsigne.redditclientapp.manager

import com.andreinsigne.redditclientapp.BuildConfig
import com.andreinsigne.redditclientapp.data.APIData
import com.andreinsigne.redditclientapp.utils.Constants


/**
 * This is where the magic happens :D. this enables us to switch between schemes and targets.
 * If it is *unit tests target or the mock scheme it uses the mock api. else it will use the real api
 * which connects to the real backend
 **/
class ServicesInjector(var data: APIData) {
    /**
     * initializes with the reddit api manager depending on the target
     **/
    var authManager: AuthManager = if (BuildConfig.FLAVOR == Constants.fake_build) {
        MockAuthManager()
    } else {
        RedditAuthManager()
    }
    /**
     * initializes with the reddit auth manager depending on the target
     **/
    var apiManager: APIManager = if (BuildConfig.FLAVOR == Constants.fake_build) {
        MockAPIManager()
    } else {
        RedditAPIManager()
    }

    /**
     * api manager that is returned depending on the target
     * @return the api manager whether from backend or mock as APIManager
     **/
    fun getAPI(): APIManager {
        return apiManager
    }

    /**
     * auth manager that is returned depending on the target
     * @return the auth manager whether from backend or mock as AuthManager
     **/
    fun getAuth(): AuthManager {
        return authManager
    }
}


