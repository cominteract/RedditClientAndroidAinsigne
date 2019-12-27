package com.andreinsigne.redditclientapp.utils

import android.content.Context

class InitContext {
    companion object {

        private lateinit var context: Context

        fun setContext(context_: Context) {
            context = context_
        }
    }
}