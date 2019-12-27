package com.andreinsigne.redditclientapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Rules (var rules : ArrayList<SubRules>? = null)
@Serializable
data class SubRules(var description : String? = null)