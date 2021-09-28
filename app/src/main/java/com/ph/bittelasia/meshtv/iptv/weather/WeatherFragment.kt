package com.ph.bittelasia.meshtv.iptv.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ph.bittelasia.meshtv.R
import com.ph.bittelasia.meshtv.databinding.FragmentIptvWeatherBinding
import com.ph.bittelasia.meshtvlibrary.database.entity.iptv.MeshWeatherWeekly
import com.ph.bittelasia.meshtvlibrary.util.imageloader.ImageLoaderListener
import com.ph.bittelasia.meshtvlibrary.viewmodel.iptv.MeshWeatherWeeklyViewModel
import java.io.File

class WeatherFragment: Fragment(),ImageLoaderListener
{
    //=========================================== Variable =========================================
    //-------------------------------------- Binding -----------------------------------------------
    private var _binding: FragmentIptvWeatherBinding? = null
    private val binding get() = _binding!!
    //----------------------------------------------------------------------------------------------

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
        _binding = FragmentIptvWeatherBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.binding.rvForecast.layoutManager = GridLayoutManager(requireActivity(),6)
        this.weatherVM = MeshWeatherWeeklyViewModel.getViewModel(requireActivity())
        this.weatherObserver = Observer {
            if(it.size>0)
            {
                var current = it.get(0)
                this.binding.tvTemp!!.text = current.temp_max+"°C"
                this.binding.tvDesc!!.text = current.description
                this.binding.rvForecast.adapter = WeatherAdapter(requireActivity(),it)
//                this.binding.iv_icon = view.findViewById(R.id.iv_icon)
            }
        }
        this.weatherVM!!.results.observe(requireActivity(), weatherObserver!!)
        load()
    }
    override fun onDetach() {
        super.onDetach()
        this.weatherVM!!.results.removeObserver(weatherObserver!!)
    }
    //==============================================================================================
    //=========================================== Method ===========================================
    fun load() {
        this.weatherVM!!.get()
    }
    //==============================================================================================
    //======================================== ImageLoaderListener =================================
    override fun onFail(message: String) {}
    override fun onSuccess(file: File, iv: ImageView) {}
    //==============================================================================================
}