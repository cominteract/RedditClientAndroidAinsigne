package com.andreinsigne.redditclientapp.manager

import android.util.Log
import com.andreinsigne.redditclientapp.model.Responses
import com.andreinsigne.redditclientapp.utils.APIEndpoint
import com.andreinsigne.redditclientapp.utils.ClosureResults
import com.andreinsigne.redditclientapp.utils.EndPoints

/**
 * Implements the api manager abstract class (which derives from the apiprotocol) and  mock
 * implementation for fetching mock data
 **/
class MockAPIManager : APIManager() {

    val responses = Responses()

    override fun startAPI(endPoint: APIEndpoint, input: String?, isJson: Boolean, retrieved: ErrorRetrieved) {
        Log.d(" I am here "," I am here $input")
        ClosureResults.apiResults(endPoint,responses.getResponsesFrom(endPoint),retrieved)
    }
    
}
