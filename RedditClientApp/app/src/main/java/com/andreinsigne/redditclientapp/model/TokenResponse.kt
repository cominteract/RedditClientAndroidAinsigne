package com.andreinsigne.redditclientapp.model

import kotlinx.serialization.Serializable

@Serializable
data class TokenResponse (var token_type : String? = null,
                          var access_token : String? = null,
                          var expires_in : Int = 0,
                          var refresh_token : String? = null)