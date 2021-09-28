package com.ph.bittelasia.meshtv.setup.configure

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ph.bittelasia.meshtv.R
import com.ph.bittelasia.meshtv.databinding.ActivitySetupBinding

class ConfigurationActivity:AppCompatActivity() {
    //========================================== Variable ==========================================
    //------------------------------------------ Binding -------------------------------------------
    private lateinit var binding: ActivitySetupBinding
    //----------------------------------------------------------------------------------------------
    //------------------------------------------ Fragment ------------------------------------------
    var configurationFragment:ConfigurationFragment ? = null
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //========================================= LifeCycle ==========================================
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetupBinding.inflate(layoutInflater)
        setContentView(binding.root)
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