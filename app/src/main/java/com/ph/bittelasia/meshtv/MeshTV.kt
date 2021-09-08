package com.ph.bittelasia.meshtv

import android.app.Application
import com.ph.bittelasia.meshtvlibrary.util.MeshInitiator

/**
 * Override default application to initiate Managers
 */
class MeshTV :Application(){

    //Override Application onCreate Lifecycle method
    override fun onCreate() {
        super.onCreate()
        //Initiates managers required for MeshTV to work
        MeshInitiator.initiate(this)
    }
}