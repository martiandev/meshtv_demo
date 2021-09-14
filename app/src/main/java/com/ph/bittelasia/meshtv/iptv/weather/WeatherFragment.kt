package com.ph.bittelasia.meshtv.iptv.weather

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ph.bittelasia.meshtv.R
import com.ph.bittelasia.meshtvlibrary.database.entity.iptv.MeshWeatherWeekly
import com.ph.bittelasia.meshtvlibrary.viewmodel.iptv.MeshWeatherWeeklyViewModel

class WeatherFragment: Fragment(){
    //=========================================== Variable =========================================
    //------------------------------------------- Constant -----------------------------------------
    companion object
    {
        val APP_ID:Int = 14
    }
    //----------------------------------------------------------------------------------------------
    //------------------------------------------- Instance -----------------------------------------
    var weatherVM:MeshWeatherWeeklyViewModel? = null
    var weatherObserver:Observer<List<MeshWeatherWeekly>>? =null
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //=========================================== LifeCycle ========================================
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(container!!.context).inflate(R.layout.fragment_iptv_weather,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.weatherVM = MeshWeatherWeeklyViewModel.getViewModel(requireActivity())
        this.weatherObserver = Observer {
            Log.d("MESHTV-DOCU","Results:"+it.size)
        }
        this.weatherVM!!.results.observe(requireActivity(), weatherObserver!!)
        load()
    }
    override fun onDetach() {
        super.onDetach()
        Log.d("MESHTV-DOCU","Detach")
        this.weatherVM!!.results.removeObserver(weatherObserver!!)

    }
    //==============================================================================================
    //=========================================== Method ===========================================
    fun load()
    {
        this.weatherVM!!.get()
    }
    //==============================================================================================

}