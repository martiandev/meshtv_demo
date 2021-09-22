package com.ph.bittelasia.meshtv.setup.configure

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ph.bittelasia.meshtv.R

class ConfigurationActivity:AppCompatActivity() {
    //========================================== Variable ==========================================
    //------------------------------------------ Fragment ------------------------------------------
    var configurationFragment:ConfigurationFragment ? = null
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //========================================= LifeCycle ==========================================
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setup)
        attachFragments()
    }
    //==============================================================================================
    //========================================== Event =============================================
    override fun onBackPressed() {}
    //==============================================================================================
    //========================================== Method ============================================
    fun attachFragments() {
        this.configurationFragment = ConfigurationFragment()
        supportFragmentManager.beginTransaction().add(R.id.fc_main,configurationFragment!!,"Config").commit()
    }
    //==============================================================================================
}