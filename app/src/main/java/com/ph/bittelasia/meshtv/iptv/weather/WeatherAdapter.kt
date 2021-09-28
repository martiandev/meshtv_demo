package com.ph.bittelasia.meshtv.iptv.weather
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.ph.bittelasia.meshtv.databinding.ItemWeatherBinding
import com.ph.bittelasia.meshtvlibrary.api.APIListener
import com.ph.bittelasia.meshtvlibrary.database.entity.iptv.MeshWeatherWeekly
import java.text.SimpleDateFormat

class WeatherAdapter(): RecyclerView.Adapter<WeatherAdapter.ViewHolder>(),APIListener{
    //========================================== Variable ==========================================
    //------------------------------------------- View ---------------------------------------------
    var activity:FragmentActivity ? = null
    //----------------------------------------------------------------------------------------------
    //-------------------------------------------- Apps --------------------------------------------
    var forecast:List<MeshWeatherWeekly>? = null
    //----------------------------------------------------------------------------------------------
     //==============================================================================================
    //======================================== Constructor =========================================
    constructor(activity:FragmentActivity,forecast:List<MeshWeatherWeekly>):this()
    {
        this.activity   = activity
        this.forecast   = forecast.drop(1)
    }
    //==============================================================================================
    //======================================== AppAdapter ==========================================
    override fun onCreateViewHolder(parent:ViewGroup, viewType:Int): ViewHolder {
        val itemBinding = ItemWeatherBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(itemBinding!!)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) { holder.bind(this.forecast!!.get(position)) }
    override fun getItemCount(): Int { return this.forecast!!.size }
    //==============================================================================================
    //========================================= ViewHolder =========================================
    class ViewHolder(private val binding:ItemWeatherBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(forecast:MeshWeatherWeekly)
        {
            var sf=SimpleDateFormat(MeshWeatherWeekly.DATE_FORMAT)
            var sfDay=SimpleDateFormat("EEE")

           binding.tvDay.text = sfDay.format(sf.parse(forecast.date))
           binding.tvWeather.text = forecast.description
        }
    }
    //==============================================================================================
    //========================================== APIListener =======================================
    override fun onFail(result:Any, type:Int) {}
    override fun onSuccess(result:Any, type:Int) {}
    //==============================================================================================
}




