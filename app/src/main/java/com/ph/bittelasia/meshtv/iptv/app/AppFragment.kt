package com.ph.bittelasia.meshtv.iptv.app

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ph.bittelasia.meshtv.R
import com.ph.bittelasia.meshtvlibrary.viewmodel.app.AppViewModel

class AppFragment:Fragment(){
    //===================================== Variable ===============================================
    //------------------------------------- ViewModel ----------------------------------------------
    var appViewModel:AppViewModel ? = null
    //----------------------------------------------------------------------------------------------
    //---------------------------------------- View ------------------------------------------------
    var rv_apps:RecyclerView ? = null
    //----------------------------------------------------------------------------------------------
    //--------------------------------------- Adapter ----------------------------------------------
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //===================================== LifeCycle ==============================================
    override fun onAttach(context: Context) {
        super.onAttach(context)
        appViewModel = AppViewModel.Companion.getViewModel(requireActivity())
        appViewModel!!.results.observe(requireActivity(), Observer {
            if(rv_apps!=null)
            {
                rv_apps!!.adapter = AppAdapter(requireActivity(),it)
            }
        })
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(container!!.context).inflate(R.layout.fragment_app,container!!,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.rv_apps = view.findViewById(R.id.rv_apps)
        this.rv_apps!!.layoutManager = LinearLayoutManager(view.context,LinearLayoutManager.HORIZONTAL,false)
        appViewModel!!.get()
    }

    //==============================================================================================

}
