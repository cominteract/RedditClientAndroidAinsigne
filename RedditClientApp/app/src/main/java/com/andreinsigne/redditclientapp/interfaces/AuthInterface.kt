package com.andreinsigne.redditclientapp.interfaces

import com.andreinsigne.redditclientapp.manager.TokenRetrieve


/// the template used in authentication
interface AuthInterface {
    /// startAuth starts the authentication process using a callback whether mock or from reddit
    ///
    /// - Parameter authRetrieve: the closure callback used in determining whether authentication is successful as TokenRetrieve
    fun startAuth(authRetrieve : TokenRetrieve)


    /// refreshAuth refreshes the token using a callback whether mock or from reddit
    ///
    /// - Parameter authRetrieve: the closure callback used in determining whether refreshing the authentication is successful as TokenRetrieve

    fun refreshAuth(authRetrieve : TokenRetrieve)

    /// isLogged checks whether the user is logged or not
    ///
    /// - Returns: as Bool to identify if user is logged
    fun isLogged() : Boolean
}