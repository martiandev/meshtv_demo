package com.ph.bittelasia.meshtv.iptv.facility

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ph.bittelasia.meshtv.R
import com.ph.bittelasia.meshtvlibrary.database.entity.iptv.MeshFacility
import com.ph.bittelasia.meshtvlibrary.preference.`object`.iptv.MeshSTB
import com.ph.bittelasia.meshtvlibrary.util.imageloader.ImageLoader
import com.ph.bittelasia.meshtvlibrary.util.imageloader.ImageLoaderListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import java.io.File

class FacilityAdapter():RecyclerView.Adapter<FacilityAdapter.ViewHolder>(),ImageLoaderListener{

    //========================================= Variable ===========================================
    //----------------------------------------- Instance -------------------------------------------
    var stb:MeshSTB ? = null
    //----------------------------------------------------------------------------------------------
    //----------------------------------------- Channels -------------------------------------------
    var list:List<MeshFacility> ? = null
    //----------------------------------------------------------------------------------------------
    //------------------------------------------ View ----------------------------------------------
    var activity: FragmentActivity? = null
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //======================================== Constructor =========================================
    constructor(activity:FragmentActivity,list:List<MeshFacility> ):this()
    {
        this.activity = activity
        this.list = list
        this.stb = MeshSTB(activity)

    }
    //==============================================================================================
    //========================================== Adapter ===========================================
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder { return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_channel,parent,false)) }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var ch:MeshFacility = this.list!!.get(position)
        holder.tv_name!!.text = ch!!.item_name
        holder.tv_description!!.text = ch!!.item_description
        var il = ImageLoader(
            holder.iv_icon.context,
            this,
            stb!!.host+":"+stb!!.port+ch!!.img_uri!!,
            holder.iv_icon!!)
        CoroutineScope(Dispatchers.IO).async {
            il!!.donwload()
        }
    }

    override fun getItemCount(): Int { return list!!.size }
    //==============================================================================================
    //======================================== ViewHolder ==========================================
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var iv_icon:ImageView = itemView.findViewById(R.id.iv_icon)
        var tv_name:TextView = itemView.findViewById(R.id.tv_name)
        var tv_description:TextView = itemView.findViewById(R.id.tv_description)
    }
    //==============================================================================================
    //===================================== ImageLoaderListener ====================================
    override fun onFail(message: String) {
    }
    override fun onSuccess(file: File, iv: ImageView) {
        activity!!.runOnUiThread(java.lang.Runnable {
            Glide.with(iv!!.rootView)
                .load(file!!.absolutePath)
                .into(iv!!)
        })
    }
    //==============================================================================================
}