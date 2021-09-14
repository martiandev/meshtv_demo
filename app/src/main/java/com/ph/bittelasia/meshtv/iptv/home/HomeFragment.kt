package com.ph.bittelasia.meshtv.iptv.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.ph.bittelasia.meshtv.R
import com.ph.bittelasia.meshtvlibrary.preference.`object`.iptv.MeshConfig
import com.ph.bittelasia.meshtvlibrary.preference.manager.ConfigPreference
import com.ph.bittelasia.meshtvlibrary.util.imageloader.ImageLoader
import com.ph.bittelasia.meshtvlibrary.util.imageloader.ImageLoaderListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import java.io.File

class HomeFragment: Fragment(),
ImageLoaderListener{
    //=========================================== Variable =========================================
    //------------------------------------------- Constant -----------------------------------------
    companion object
    {
        val APP_ID:Int = 0
    }
    //----------------------------------------------------------------------------------------------
    //--------------------------------------------- View ------------------------------------------
    var iv_logo:ImageView ? = null

    //----------------------------------------------------------------------------------------------
    //------------------------------------------- Instance -----------------------------------------
    var config:MeshConfig ? = null
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //=========================================== LifeCycle ========================================
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.config = MeshConfig(requireContext())
        ConfigPreference.get()!!.load(this.config!!)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(container!!.context).inflate(R.layout.fragment_iptv_home,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadViews(view)
        load()

    }
    override fun onDetach() {
        super.onDetach()


    }
    //==============================================================================================
    //========================================== Method ============================================
    fun loadViews(view:View)
    {
        this.iv_logo = view.findViewById(R.id.iv_logo)
    }
    fun load()
    {
        var logoLoader:ImageLoader = ImageLoader(requireActivity(),this,this.config!!.logo!!,iv_logo!!)
        CoroutineScope(Dispatchers.IO).async {
            logoLoader!!.donwload()
        }
    }
    //==============================================================================================
    //==================================== ImageLoaderListener =====================================
    override fun onFail(message: String) {

    }
    override fun onSuccess(file: File, iv: ImageView) {
        requireActivity()!!.runOnUiThread(java.lang.Runnable {
            Glide.with(this)
                .load(file!!.absolutePath)
                .into(iv!!)
        })

    }
    //==============================================================================================

}