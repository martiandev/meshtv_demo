package com.ph.bittelasia.meshtv.iptv.app

import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ph.bittelasia.meshtv.R
import com.ph.bittelasia.meshtv.iptv.IPTV
import com.ph.bittelasia.meshtvlibrary.database.entity.app.AppEntity
import com.ph.bittelasia.meshtvlibrary.util.imageloader.ImageLoader
import com.ph.bittelasia.meshtvlibrary.util.imageloader.ImageLoaderListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import java.io.File

class AppAdapter(): RecyclerView.Adapter<AppAdapter.ViewHolder>(), ImageLoaderListener {
    //========================================== Variable ==========================================
    //----------------------------------------- Selection ------------------------------------------
    var selection:Int ? = -1
    //----------------------------------------------------------------------------------------------
    //------------------------------------------- View ---------------------------------------------
    var itemView:View ? = null
    var activity:FragmentActivity ? = null
    //----------------------------------------------------------------------------------------------
    //-------------------------------------------- Apps --------------------------------------------
    var apps:List<AppEntity>? = null
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //======================================== Constructor =========================================
    constructor(activity:FragmentActivity,apps:List<AppEntity>):this()
    {
        this.activity = activity
        this.apps = apps
    }
    //==============================================================================================
    //======================================== AppAdapter ==========================================
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_app,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var app : AppEntity = this.apps!!.get(position)
        holder.tv_app.text = app!!.display_name

        var il = ImageLoader(
            holder.iv_icon.context,
            this,
            app!!.icon!!,
            holder.iv_icon!!)

        holder.iv_icon!!.setOnClickListener {
            (activity as IPTV).selectApplication(app!!.app!!)
        }

        holder.iv_icon.setOnHoverListener { view, motionEvent ->
            Log.d("MESTV-DOCU","MOTION DETECTED")
            true
        }

        CoroutineScope(Dispatchers.IO).async {
            il!!.donwload()
        }

    }
    override fun getItemCount(): Int { return this.apps!!.size }
    //==============================================================================================
    //=========================================== Selection ========================================

    //==============================================================================================
    //======================================= ImageLoaderListener ==================================

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
    //========================================= ViewHolder =========================================
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var tv_app:TextView = itemView.findViewById(R.id.tv_app)
        var iv_icon: ImageView = itemView.findViewById(R.id.iv_icon)
    }
    //==============================================================================================
}




