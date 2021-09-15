package com.ph.bittelasia.meshtv

import android.app.Application
import com.ph.bittelasia.meshtvlibrary.util.MeshInitiator

/**
 * Extend default application to initiate Managers
 * 1. Override application onCreate Lifecycle method
 * 2. Initiate managers required for MeshTV to work
 */
class MeshTV :Application(){

    //1
    override fun onCreate() {
        super.onCreate()
        //2
        MeshInitiator.initiate(this)
    }
}