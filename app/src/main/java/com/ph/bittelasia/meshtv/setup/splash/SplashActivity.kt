package com.ph.bittelasia.meshtv.setup.splash

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ph.bittelasia.meshtv.R
import com.ph.bittelasia.meshtvlibrary.preference.`object`.license.MeshLicense
import com.ph.bittelasia.meshtvlibrary.preference.manager.LicensePreference

class SplashActivity:AppCompatActivity() {

    //======================================= Variable =============================================
    //--------------------------------------- Fragment ---------------------------------------------
    var splashFragment:SplashFragment ? = null
    var splashDSFragment:SplashDSFragment ? = null
    //----------------------------------------------------------------------------------------------
    //--------------------------------------- Preference -------------------------------------------
    var license:MeshLicense ? = null
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //======================================= LifeCycle ============================================
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        loadLicense()
        attachFragments()
    }
    //==============================================================================================
    //======================================== Method ==============================================
    fun loadLicense()
    {
        this.license = MeshLicense()
        LicensePreference.get()!!.load(this.license!!)
    }
    fun attachFragments() {
        if(this.license!!.is_ds!=true)
        {
            this.splashFragment = SplashFragment()
            supportFragmentManager.beginTransaction().add(R.id.fc_main,splashFragment!!,"Splash").commit()
        }
        else
        {
            this.splashDSFragment = SplashDSFragment()
            supportFragmentManager.beginTransaction().add(R.id.fc_main,splashDSFragment!!,"Splash DS").commit()
        }
    }
    //==============================================================================================

}