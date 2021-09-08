package com.ph.bittelasia.meshtv.iptv.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ph.bittelasia.meshtv.R
import com.ph.bittelasia.meshtv.setup.expire.ExpiryMonitorFragment
import com.ph.bittelasia.meshtvlibrary.fragment.expiry.DefaultExpiryMonitorFragment

class InfoFragment: Fragment(),
        DefaultExpiryMonitorFragment.CallBack
{
    //========================================== Variable ==========================================
    //------------------------------------------ Fragment ------------------------------------------
    var guestFragment:GuestFragment ? = null
    var roomFragment:RoomFragment ? = null
    var weatherFragment:WeatherFragment ? = null
    var expirationMonitorFragment: ExpiryMonitorFragment ? = null
    var fc_expiry:View ? = null
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //========================================== LifeCycle =========================================
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? { return LayoutInflater.from(container!!.context).inflate(R.layout.fragment_information,container,false) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.expirationMonitorFragment = ExpiryMonitorFragment()
        this.guestFragment = GuestFragment()
        this.roomFragment = RoomFragment()
        this.weatherFragment = WeatherFragment()
        childFragmentManager.beginTransaction().add(R.id.fc_expiry,expirationMonitorFragment!!,"Expiry Monitor").commit()
        childFragmentManager.beginTransaction().add(R.id.fc_guest_info,guestFragment!!,"Guest").commit()
        childFragmentManager.beginTransaction().add(R.id.fc_room_info,roomFragment!!,"Room").commit()
        childFragmentManager.beginTransaction().add(R.id.fc_time,weatherFragment!!,"weather").commit()
        fc_expiry = view.findViewById(R.id.fc_expiry)
    }
    //==============================================================================================
    //========================================== ExpiryMonitor =====================================
    override fun expired() { fc_expiry!!.visibility = View.VISIBLE }
    override fun onWarning(days: Int) { fc_expiry!!.visibility = View.VISIBLE }
    //==============================================================================================


}