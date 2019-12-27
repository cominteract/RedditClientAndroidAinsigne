package com.andreinsigne.redditclientapp.data

import com.andreinsigne.redditclientapp.interfaces.AuthInterface
import com.andreinsigne.redditclientapp.data.APIData


/**
 * This is where the magic happens :D. this enables us to switch between schemes and targets.
 * If it is *unit tests target or the mock scheme it uses the mock data. else it will use the real data
 * which is retrieved or consumed from the real backend
 **/
class DataInjector {

    /**
     * initializes with the correct data protocol depending on the target/scheme
     * now uses a single repo of data
     **/
    val data = APIData()


}
