package com.ph.bittelasia.meshtv.iptv.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ph.bittelasia.meshtv.R
import com.ph.bittelasia.meshtv.databinding.FragmentInformationBinding

class InfoFragment: Fragment()
{
    //========================================== Variable ==========================================
    //------------------------------------------ Binding -------------------------------------------
    private var _binding: FragmentInformationBinding? = null
    private val binding get() = _binding!!
    //----------------------------------------------------------------------------------------------
    //------------------------------------------ Fragment ------------------------------------------
    var guestFragment:GuestFragment ? = null
    var roomFragment:RoomFragment ? = null
    var weatherFragment:WeatherFragment ? = null
    var expirationMonitorFragment: ExpiryMonitorFragment? = null
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //========================================== LifeCycle =========================================
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentInformationBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    //==============================================================================================

}