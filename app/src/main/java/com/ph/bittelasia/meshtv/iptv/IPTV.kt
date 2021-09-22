package com.ph.bittelasia.meshtv.iptv

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.ph.bittelasia.meshtv.R
import com.ph.bittelasia.meshtv.iptv.app.AppFragment
import com.ph.bittelasia.meshtv.iptv.channel.TVFragment
import com.ph.bittelasia.meshtv.iptv.customizable.MainBGFragment
import com.ph.bittelasia.meshtv.iptv.facility.FacilityFragment
import com.ph.bittelasia.meshtv.iptv.home.HomeFragment
import com.ph.bittelasia.meshtv.iptv.info.InfoFragment
import com.ph.bittelasia.meshtv.iptv.message.MessageFragment
import com.ph.bittelasia.meshtv.iptv.weather.WeatherFragment
import com.ph.bittelasia.meshtv.setup.xmpp.XMPPFragment
import com.ph.bittelasia.meshtvlibrary.api.APIManager
import com.ph.bittelasia.meshtvlibrary.fragment.data.XMPPUpdateFragment
import com.ph.bittelasia.meshtvlibrary.fragment.expiry.DefaultExpiryMonitorFragment
import com.ph.bittelasia.meshtvlibrary.viewmodel.iptv.MeshWeatherDailyViewModel
import com.ph.bittelasia.meshtvlibrary.xmpp.instant_display.Message

class IPTV:FragmentActivity(),
        XMPPUpdateFragment.XMPPUpdateListener,
        DefaultExpiryMonitorFragment.CallBack
{
    //======================================== Variable ============================================
    //---------------------------------------- Fragment --------------------------------------------
    var infoFragment:InfoFragment ? = null
    var xmppFragment:XMPPUpdateFragment ? = null
    var appFragment : AppFragment? = null
    //----------------------------------------------------------------------------------------------
    //------------------------------------- IPTV-Fragment ------------------------------------------
    var homeFragment: HomeFragment ? = null
    var tvFragment: TVFragment ? = null
    var facilityFragment: FacilityFragment ? = null
    var messageFragment: MessageFragment ? = null
    var weatherFragment: WeatherFragment? = null
    var bgFragment: MainBGFragment? = null
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //======================================== LifeCycle ===========================================
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iptv)
        load()
        attachFragments()
        var daily:MeshWeatherDailyViewModel = MeshWeatherDailyViewModel.getViewModel(this)
        daily.getWeatherNow()
    }

    //==============================================================================================
    //========================================= Event ==============================================
    override fun onBackPressed() {
        if(findViewById<View>(R.id.fc_app)!!.visibility==View.GONE)
         {
            selectApplication(HomeFragment.APP_ID)
         }
        else
         {

         }
    }
    //==============================================================================================
    //========================================= Method =============================================
    fun load()
    {
        this.appFragment = AppFragment()
        this.infoFragment = InfoFragment()
        this.xmppFragment = XMPPFragment()
        this.homeFragment = HomeFragment()
        this.tvFragment = TVFragment()
        this.facilityFragment = FacilityFragment()
        this.messageFragment = MessageFragment()
        this.weatherFragment = WeatherFragment()
        this.bgFragment = MainBGFragment(this.appFragment!!)
    }
    fun attachFragments()
    {
        supportFragmentManager.beginTransaction().add(R.id.fc_main,this.homeFragment!!,"HOME").commit()
        supportFragmentManager.beginTransaction().add(R.id.fc_top,this.infoFragment!!,"INFO").commit()
        supportFragmentManager.beginTransaction().add(R.id.fc_xmpp,this.xmppFragment!!,"XMPP").commit()
        supportFragmentManager.beginTransaction().add(R.id.fc_app, this.bgFragment!!,"APP").commit()

    }
    //==============================================================================================
    //============================================ UI ==============================================
    fun displayApps(shouldDisplay:Boolean)
    {
        findViewById<View>(R.id.fc_app)!!.visibility = when(shouldDisplay) { true -> View.VISIBLE else ->View.GONE}
    }
    fun selectApplication(app:Int)
    {
        var content = supportFragmentManager.findFragmentById(R.id.fc_main)
        if(content!=null) {
            supportFragmentManager.beginTransaction().remove(content).commit()
        }

        when(app)
        {
            HomeFragment.APP_ID -> {supportFragmentManager.beginTransaction().add(R.id.fc_main,this.homeFragment!!,"HOME").commit()
                displayApps(true)
            }
            TVFragment.APP_ID -> {supportFragmentManager.beginTransaction().add(R.id.fc_main, this.tvFragment!!,"TV").commit()
                displayApps(false)
            }
            FacilityFragment.APP_ID -> {supportFragmentManager.beginTransaction().add(R.id.fc_main, this.facilityFragment!!,"FACILITY").commit()
                displayApps(false)
            }
            MessageFragment.APP_ID -> {supportFragmentManager.beginTransaction().add(R.id.fc_main, this.messageFragment!!,"MESSAGE").commit()
                displayApps(false)
            }
            WeatherFragment.APP_ID -> {supportFragmentManager.beginTransaction().add(R.id.fc_main, this.weatherFragment!!,"WEATHER").commit()
                displayApps(false)
            }
        }
    }
    //==============================================================================================
    //=================================== XMPPUpdateFragment =======================================
    override fun isSoleReceiver(): Boolean { return false }
    override fun onEmergecy(m: Message) {}
    override fun onPop(m: Message) {}
    override fun onReset() {}
    override fun onResetData() {}
    override fun onResetLicense() {}
    override fun onResetSTB() {}
    //==============================================================================================
    //===================================== DefaultExpiryCB ========================================
    override fun expired() {
    }
    override fun onWarning(days: Int) {
        infoFragment!!.displayExpiry(true)
    }
    //==============================================================================================
}