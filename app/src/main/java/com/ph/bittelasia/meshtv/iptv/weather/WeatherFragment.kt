package com.ph.bittelasia.meshtv.iptv.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ph.bittelasia.meshtv.R

class WeatherFragment: Fragment(){
    //=========================================== Variable =========================================
    //------------------------------------------- Constant -----------------------------------------
    companion object
    {
        val APP_ID:Int = 14
    }
    //----------------------------------------------------------------------------------------------
    //------------------------------------------- Instance -----------------------------------------
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //=========================================== LifeCycle ========================================
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(container!!.context).inflate(R.layout.fragment_iptv_weather,container,false)
    }
    //==============================================================================================


}