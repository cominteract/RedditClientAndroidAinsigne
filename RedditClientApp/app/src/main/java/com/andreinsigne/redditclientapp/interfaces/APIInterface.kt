package com.andreinsigne.redditclientapp.interfaces

import com.andreinsigne.redditclientapp.manager.ErrorRetrieved
import com.andreinsigne.redditclientapp.utils.APIEndpoint


/// the template used in implementing the api
interface  APIInterface {
/** startAPI starts the api process for the GET and POST requests in reddit api or for mock api process
 *
 * @param endPoint as APIEndpoint to identify what type of endpoint will be requested
 * @param input as String the extra parameters to be ued in post or get requests
 * @param isJson  as Bool whether the .json will be appended for public
 * @param retrieved as ErrorRetrieved the closure callback to be used in identifying whether the api process is ready to return results or error
 **/
    fun startAPI(endPoint : APIEndpoint, input : String?, isJson : Boolean, retrieved : ErrorRetrieved)
}