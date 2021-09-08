package com.ph.bittelasia.meshtv.iptv

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ph.bittelasia.meshtv.R
import com.ph.bittelasia.meshtv.iptv.info.InfoFragment

class IPTV:AppCompatActivity()
{
    //======================================== Variable ============================================
    //---------------------------------------- Fragment --------------------------------------------
    var infoFragment:InfoFragment ? = null
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //======================================== LifeCycle ===========================================
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iptv)
        load()
        attachFragments()
    }
    //==============================================================================================
    //========================================= Method =============================================
    fun load()
    {
        this.infoFragment = InfoFragment()
    }
    fun attachFragments()
    {
        supportFragmentManager.beginTransaction().add(R.id.fc_top,this.infoFragment!!,"INFO").commit()
    }
    //==============================================================================================
}