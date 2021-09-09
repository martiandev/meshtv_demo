package com.ph.bittelasia.meshtv.setup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ph.bittelasia.meshtv.R
import com.ph.bittelasia.meshtv.setup.launcher.LauncherFragment
import com.ph.bittelasia.meshtvlibrary.fragment.launcher.DefaultLauncherFragment
import com.ph.bittelasia.meshtvlibrary.viewmodel.signage.SignageViewModel
import androidx.lifecycle.Observer
import com.ph.bittelasia.meshtv.iptv.IPTV
import com.ph.bittelasia.meshtv.iptv.app.AppFragment
import com.ph.bittelasia.meshtv.setup.xmpp.XMPPFragment
import com.ph.bittelasia.meshtvlibrary.fragment.data.XMPPUpdateFragment
import com.ph.bittelasia.meshtvlibrary.xmpp.instant_display.Message


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
        supportFragmentManager.beginTransaction().add(R.id.fc_main, this.launcherFragment!!,"LAUNCHER").commit()
        supportFragmentManager.beginTransaction().add(R.id.fc_xmpp, this.xmppFragment!!,"XMPP").commit()
    }

    //==============================================================================================
    //======================================= LauncherCallBack =====================================
    override fun launchDigitalSignage() {
        sVM = SignageViewModel.getViewModel( this)
        sVM!!.results.observe(
            this, Observer
            {
                if(it.size>0)
                {
//                    var i: Intent = Intent(this, DS::class.java)
//                    i.putExtra(DSActivity.TAG_LAYOUT,it.get(0).layout_id)
//                    startActivity(i)
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
    override fun isSoleReceiver(): Boolean {
        return false
    }

    override fun onEmergecy(m: Message) {
    }

    override fun onPop(m: Message) {
    }

    override fun onReset() {
    }

    override fun onResetData() {
    }

    override fun onResetLicense() {
    }

    override fun onResetSTB() {
    }
    //==============================================================================================


}