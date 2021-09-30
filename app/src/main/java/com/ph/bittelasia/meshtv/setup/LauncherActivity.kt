package com.ph.bittelasia.meshtv.setup

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ph.bittelasia.meshtv.R
import com.ph.bittelasia.meshtv.setup.launcher.LauncherFragment
import com.ph.bittelasia.meshtvlibrary.fragment.launcher.DefaultLauncherFragment
import com.ph.bittelasia.meshtvlibrary.viewmodel.signage.SignageViewModel
import androidx.lifecycle.Observer
import com.ph.bittelasia.meshtv.iptv.IPTV
import com.ph.bittelasia.meshtv.setup.xmpp.XMPPFragment
import com.ph.bittelasia.meshtvlibrary.fragment.data.XMPPUpdateFragment
import com.ph.bittelasia.meshtvlibrary.xmpp.instant_display.Message

/**
 *
 * Set-up a launcher activity
 *
 * 0. Add Launcher Activity to manifest
 *
 * 1. Extend AppCompatActivity
 *
 * 2. Override onCreate
 *      2.1. Create an Instance of SignageViewModel to load Signage for Digital Signage
 *      2.2. Create an Instance of the LauncherFragment
 *      2.3. Create an Instance of the XMPPFragment
 *      2.4. Attach launcherFragment
 *      2.5. Attach xmppFragment
 *
 * 3. Implement DefaultLauncherFragment.LauncherCallBack
 *      3.1. override launchDigitalSignageMethod and request the assigned signage using the SinageViewModel instance
 *      3.2. override launchIPTV and launch the IPTV activity in this demo its IPTV.kt
 *
 * 4. Implement XMPPUpdateFragment.XMPPUpdateListener
 *      4.1. override isSoleReceiver should be set to false
 *
 */
class LauncherActivity:AppCompatActivity(),
    DefaultLauncherFragment.LauncherCallBack,
    XMPPUpdateFragment.XMPPUpdateListener
{
    //======================================== Variable ============================================
    //---------------------------------------- Fragment --------------------------------------------
    var launcherFragment : LauncherFragment ? = null
    var xmppFragment : XMPPFragment? = null
    //----------------------------------------------------------------------------------------------
    //---------------------------------------- ViewModel -------------------------------------------
    var sVM: SignageViewModel? = null
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //======================================= LifeCycle ============================================
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        this.launcherFragment = LauncherFragment()
        this.xmppFragment = XMPPFragment()
        supportFragmentManager.beginTransaction().add(R.id.fc_main,this.launcherFragment!!,"LAUNCHER").commit()
        supportFragmentManager.beginTransaction().add(R.id.fc_xmpp,this.xmppFragment!!,"XMPP").commit()
    }
    //==============================================================================================
    //======================================= LauncherCallBack =====================================

    override fun launchDigitalSignage() {
        sVM = SignageViewModel.getViewModel( this)
        sVM!!.results.observe(
            this ,
            Observer {
                if(it.size>0)
                {
                    //var i: Intent = Intent(this, DS::class.java)
                    //i.putExtra(DSActivity.TAG_LAYOUT,it.get(0).layout_id)
                    //startActivity(i)
                }
            }
        )
        sVM!!.get()
    }
    override fun launchIPTV() {
        var i: Intent = Intent(this, IPTV::class.java)
        startActivity(i)
    }
    //==============================================================================================
    //========================================== XMPP Update =======================================
    override fun isSoleReceiver(): Boolean { return false }
    override fun onEmergecy(m: Message) {}
    override fun onPop(m: Message) {}
    override fun onReset() {}
    override fun onResetData() {}
    override fun onResetLicense() {}
    override fun onResetSTB() {}
    //==============================================================================================
}