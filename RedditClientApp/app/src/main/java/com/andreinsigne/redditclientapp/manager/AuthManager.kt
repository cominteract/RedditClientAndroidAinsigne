package com.andreinsigne.redditclientapp.manager

import com.andreinsigne.redditclientapp.interfaces.AuthInterface
import com.andreinsigne.redditclientapp.model.TokenResponse


/** TokenRetrieve class implementing the ErrorAuth which is the parent class
 * to return the error from the auth through closure callback and the closure to return the token retrieved
 **/
interface TokenRetrieve : ErrorAuth{
    fun didRetrieveToken(tokenResponse : TokenResponse)
}

/** ErrorAuth which is the parent class to return the error from the auth through closure callback
 **/
interface ErrorAuth{
    fun didEncounterError(error : Error, revoked : Boolean)
}

/**
 * serves as an abstract class for the auth protocol and its implementation
 **/
open class AuthManager : AuthInterface {

    override fun startAuth(authRetrieve: TokenRetrieve) {

    }

    override fun isLogged(): Boolean {
        return false
    }

    override fun refreshAuth(authRetrieve: TokenRetrieve) {

    }

}
