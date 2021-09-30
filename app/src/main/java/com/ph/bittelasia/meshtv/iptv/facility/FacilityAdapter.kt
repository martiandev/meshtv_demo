package com.ph.bittelasia.meshtv.iptv.facility

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ph.bittelasia.meshtv.R
import com.ph.bittelasia.meshtv.databinding.ItemFacilityBinding
import com.ph.bittelasia.meshtvlibrary.database.entity.iptv.MeshChannel
import com.ph.bittelasia.meshtvlibrary.database.entity.iptv.MeshFacility
import com.ph.bittelasia.meshtvlibrary.preference.`object`.iptv.MeshSTB
import com.ph.bittelasia.meshtvlibrary.preference.manager.MeshSTBPreference
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
        MeshSTBPreference.get()!!.load(stb!!)
    }
    //==============================================================================================
    //========================================== Adapter ===========================================
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemFacilityBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var ch:MeshFacility = this.list!!.get(position)
        holder.bind(ch,this,stb!!)
    }

    override fun getItemCount(): Int { return list!!.size }
    //==============================================================================================
    //======================================== ViewHolder ==========================================
    class ViewHolder(private val binder: ItemFacilityBinding) : RecyclerView.ViewHolder(binder.root)
    {
        companion object
        {
            @JvmStatic
            @BindingAdapter("imageUrl","listener")
            fun loadImage(@NonNull ivIcon:ImageView, url:String, listener: ImageLoaderListener)
            {
                var il = ImageLoader(
                    ivIcon.context,
                    listener,
                    url,
                    ivIcon)
                CoroutineScope(Dispatchers.IO).async {
                    il.donwload()
                }
            }
        }
        fun bind(facility : MeshFacility, listener:ImageLoaderListener, stb:MeshSTB)
        {
            binder.facility=facility
            binder.stb=stb
            binder.listener=listener
        }
    }
    //==============================================================================================
    //===================================== ImageLoaderListener ====================================
    override fun onFail(message: String) {
    }
    override fun onSuccess(file: File, iv: ImageView) {
        activity!!.runOnUiThread(java.lang.Runnable {
            Glide.with(iv.rootView)
                .load(file.absolutePath)
                .into(iv)
        })
    }
    //==============================================================================================
}