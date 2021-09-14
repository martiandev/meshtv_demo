package com.ph.bittelasia.meshtv.iptv.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ph.bittelasia.meshtv.R
import com.ph.bittelasia.meshtv.iptv.IPTV
import com.ph.bittelasia.meshtv.iptv.message.MessageFragment
import com.ph.bittelasia.meshtvlibrary.database.entity.app.AppEntity
import com.ph.bittelasia.meshtvlibrary.util.imageloader.ImageLoader
import com.ph.bittelasia.meshtvlibrary.util.imageloader.ImageLoaderListener
import com.ph.bittelasia.meshtvlibrary.viewmodel.iptv.MeshMessageViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import java.io.File

class AppAdapter(): RecyclerView.Adapter<AppAdapter.ViewHolder>(), ImageLoaderListener {
    //========================================== Variable ==========================================

    //----------------------------------------- ViewModel ------------------------------------------
    var vm:MeshMessageViewModel ? = null
    //----------------------------------------------------------------------------------------------
    //------------------------------------------- View ---------------------------------------------
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
        this.vm = MeshMessageViewModel.getViewModel(this.activity!!)
    }
    //==============================================================================================
    //======================================== AppAdapter ==========================================
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_app,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var app : AppEntity = this.apps!!.get(position)

        if(app!!.app==MessageFragment.APP_ID)
        {
            vm!!.unread.observe(activity!!, Observer {
                if(it.size>0)
                {
                    holder.tv_balloon!!.visibility = View.VISIBLE
                    holder.tv_balloon!!.text = ""+it.size+""
                }
                else
                {
                    holder.tv_balloon!!.visibility = View.GONE
                }
            })
            vm!!.results.observe(activity!!, Observer {
                vm!!.getUnread()
            })
            vm!!.getUnread()
        }

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

            true
        }
        CoroutineScope(Dispatchers.IO).async {
            il!!.donwload()
        }
    }
    override fun getItemCount(): Int { return this.apps!!.size }
    //==============================================================================================
    //======================================= ImageLoaderListener ==================================
    override fun onFail(message: String) {}
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
        var tv_app: TextView = itemView.findViewById(R.id.tv_app)
        var tv_balloon: TextView = itemView.findViewById(R.id.tv_balloon)
        var iv_icon: ImageView = itemView.findViewById(R.id.iv_icon)
    }
    //==============================================================================================
}




