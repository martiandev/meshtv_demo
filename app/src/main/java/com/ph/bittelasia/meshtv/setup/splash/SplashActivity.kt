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
        Log.d("FINAL-CHECK","("+this.javaClass::class.qualifiedName+") - onCreate")

        loadLicense()
        attachFragments()
    }

    override fun onPause() {
        super.onPause()
        Log.d("FINAL-CHECK","("+this.javaClass::class.qualifiedName+") - PAUSING ACTIVITY")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("FINAL-CHECK","("+this.javaClass::class.qualifiedName+") - FINISHING ACTIVITY")

    }

    //==============================================================================================
    //======================================== Method ==============================================
    fun loadLicense()
    {
        Log.d("FINAL-CHECK","("+this.javaClass::class.qualifiedName+") - Load License")

        this.license = MeshLicense()
        LicensePreference.get()!!.load(this.license!!)
    }
    fun attachFragments() {
        Log.d("FINAL-CHECK","("+this::class.qualifiedName+") - Attach Fragments")

        if(this.license!!.is_ds!=true)
        {
            Log.d("FINAL-CHECK","("+this::class.qualifiedName+") - Attach IPTV SPLASH")
            this.splashFragment = SplashFragment()
            supportFragmentManager.beginTransaction().add(R.id.fc_main,splashFragment!!,"Splash").commit()
        }
        else
        {
            Log.d("FINAL-CHECK","("+this::class.qualifiedName+") - Attach DS SPLASH")
            this.splashDSFragment = SplashDSFragment()
            supportFragmentManager.beginTransaction().add(R.id.fc_main,splashDSFragment!!,"Splash DS").commit()
        }
    }
    //==============================================================================================

}