package com.ph.bittelasia.meshtv.iptv.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ph.bittelasia.meshtv.databinding.FragmentWeatherBinding
import com.ph.bittelasia.meshtvlibrary.database.entity.iptv.MeshWeatherDaily
import com.ph.bittelasia.meshtvlibrary.fragment.weather.DefaultDailyWeatherForecastFragment
import java.text.SimpleDateFormat
import java.util.*

    class WeatherFragment : DefaultDailyWeatherForecastFragment() {
    //======================================== Variable ============================================
    //------------------------------------------ Binding -------------------------------------------
    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //======================================== LifeCycle ===========================================
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onResume() {
        super.onResume()
        var sf = SimpleDateFormat("MMM dd, yyyy")
        binding.tvDate!!.text  = sf.format(Date())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    //==============================================================================================
    //=============================== DailyWeatherForecastFragment =================================
    override fun onForecastLoaded(daily: MeshWeatherDaily) { binding.tvWeather!!.text = daily.description }
    //==============================================================================================

}