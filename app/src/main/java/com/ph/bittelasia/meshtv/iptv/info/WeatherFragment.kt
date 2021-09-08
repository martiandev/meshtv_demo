package com.ph.bittelasia.meshtv.iptv.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ph.bittelasia.meshtv.R
import com.ph.bittelasia.meshtvlibrary.database.entity.iptv.MeshWeatherDaily
import com.ph.bittelasia.meshtvlibrary.fragment.weather.DefaultDailyWeatherForecastFragment
import java.text.SimpleDateFormat
import java.util.*

class WeatherFragment : DefaultDailyWeatherForecastFragment() {

    //======================================== Variable ============================================
    //------------------------------------------- View ---------------------------------------------
    var tv_weather:TextView ? = null
    var tv_date:TextView ? = null
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //======================================== LifeCycle ===========================================
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(container!!.context).inflate(R.layout.fragment_weather,container!!,false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.tv_weather = view.findViewById(R.id.tv_weather)
        this.tv_date = view.findViewById(R.id.tv_date)
    }
    override fun onResume() {
        super.onResume()
        var sf = SimpleDateFormat("MMM dd, yyyy")
        tv_date!!.text  = sf.format(Date())
    }
    //==============================================================================================
    //=============================== DailyWeatherForecastFragment =================================
    override fun onForecastLoaded(daily: MeshWeatherDaily) { this.tv_weather!!.text = daily.description }
    //==============================================================================================

}