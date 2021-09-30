package com.ph.bittelasia.meshtv.iptv.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ph.bittelasia.meshtv.databinding.ItemAppBinding
import com.ph.bittelasia.meshtv.iptv.IPTV
import com.ph.bittelasia.meshtv.iptv.message.MessageFragment
import com.ph.bittelasia.meshtvlibrary.database.entity.app.AppEntity
import com.ph.bittelasia.meshtvlibrary.preference.`object`.iptv.MeshSTB
import com.ph.bittelasia.meshtvlibrary.preference.manager.MeshSTBPreference
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
        val itemBinding:ItemAppBinding = ItemAppBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var app : AppEntity = this.apps!!.get(position)
        holder.bind(activity!!,app,this)
        if(app.app==MessageFragment.APP_ID)
        {
            vm!!.unread.observe(activity!!, Observer {
               holder.setBalloon(it.size)
            })
            vm!!.results.observe(activity!!, Observer {
                vm!!.getUnread()
            })
            vm!!.getUnread()
        }
    }
    override fun getItemCount(): Int { return this.apps!!.size }
    //==============================================================================================
    //======================================= ImageLoaderListener ==================================
    override fun onFail(message: String) {}
    override fun onSuccess(file: File, iv: ImageView) {
        activity!!.runOnUiThread(java.lang.Runnable {
            Glide.with(iv.rootView)
                .load(file.absolutePath)
                .into(iv)
        })
    }
    //==============================================================================================
    //========================================= ViewHolder =========================================
    class ViewHolder(private val itemBinding: ItemAppBinding) : RecyclerView.ViewHolder(itemBinding.root)
    {
        companion object
        {
            @JvmStatic
            @BindingAdapter("imageUrl","listener")
            fun loadImage(@NonNull ivIcon:ImageView,url:String,listener: ImageLoaderListener)
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
        fun bind(activity:FragmentActivity,app:AppEntity, listener: ImageLoaderListener) {
            var stb =MeshSTB(activity)
            MeshSTBPreference.get()!!.load(stb)
            itemBinding.stb = stb
            itemBinding.app = app
            itemBinding.listener = listener
            itemBinding.ivIcon.setOnClickListener {
                (activity as IPTV).selectApplication(app.app!!)
            }

        }
        fun setBalloon(count:Int)
        {
            itemBinding.tvBalloon.visibility = when(count>0){
                true -> View.VISIBLE
                else-> View.GONE
            }
            itemBinding.tvBalloon.text = ""+count
        }
    }
    //==============================================================================================
}




