package com.andreinsigne.redditclientapp.manager

import com.andreinsigne.redditclientapp.utils.APIEndpoint
import com.andreinsigne.redditclientapp.utils.ClosureResults
import com.andreinsigne.redditclientapp.utils.EndPoints

/**
 * Implements the api manager abstract class (which derives from the apiprotocol) and  mock
 * implementation for fetching mock data
 **/
class MockAPIManager : APIManager() {

    override fun startAPI(endPoint: APIEndpoint, input: String?, isJson: Boolean, retrieved: ErrorRetrieved) {
        if(input != null)
            ClosureResults.apiResults(endPoint,EndPoints.endpoint(endPoint,input),retrieved)
    }
    
}
