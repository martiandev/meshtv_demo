package com.ph.bittelasia.meshtv.iptv.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.ph.bittelasia.meshtv.R
import com.ph.bittelasia.meshtvlibrary.database.entity.iptv.MeshWeatherWeekly
import com.ph.bittelasia.meshtvlibrary.util.imageloader.ImageLoaderListener
import com.ph.bittelasia.meshtvlibrary.viewmodel.iptv.MeshWeatherWeeklyViewModel
import java.io.File

class WeatherFragment: Fragment(),ImageLoaderListener
{
    //=========================================== Variable =========================================
    //------------------------------------------- Constant -----------------------------------------
    companion object {
        val APP_ID:Int = 14
    }
    //----------------------------------------------------------------------------------------------
    //--------------------------------------------- View -------------------------------------------
    var tv_temp: TextView? = null
    var tv_desc: TextView? = null
    var iv_icon: ImageView? = null
    var rv_forecast: RecyclerView? = null
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
            var current = it.get(0)
            this.tv_temp!!.text = current.temp_max+"°C"
            this.tv_desc!!.text = current.description
            this.iv_icon = view.findViewById(R.id.iv_icon)
        }
        this.weatherVM!!.results.observe(requireActivity(), weatherObserver!!)
        bindViews(view)
        load()
    }
    override fun onDetach() {
        super.onDetach()
        this.weatherVM!!.results.removeObserver(weatherObserver!!)
    }
    //==============================================================================================
    //=========================================== Method ===========================================
    fun bindViews(view:View) {
        this.tv_temp        = view.findViewById(R.id.tv_temp)
        this.tv_desc        = view.findViewById(R.id.tv_desc)
        this.iv_icon        = view.findViewById(R.id.iv_icon)
        this.rv_forecast    = view.findViewById(R.id.rv_forecast)
    }
    fun load() {
        this.weatherVM!!.get()
    }
    //==============================================================================================
    //======================================== ImageLoaderListener =================================
    override fun onFail(message: String) {}
    override fun onSuccess(file: File, iv: ImageView) {}
    //==============================================================================================
}