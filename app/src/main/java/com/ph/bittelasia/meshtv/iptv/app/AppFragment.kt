package com.ph.bittelasia.meshtv.iptv.app

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ph.bittelasia.meshtv.databinding.FragmentAppBinding
import com.ph.bittelasia.meshtvlibrary.viewmodel.app.AppViewModel

class AppFragment:Fragment(){
    //===================================== Variable ===============================================
    //-------------------------------------- Binding -----------------------------------------------
    private var _binding: FragmentAppBinding? = null
    private val binding get() = _binding!!
    //----------------------------------------------------------------------------------------------
    //------------------------------------- ViewModel ----------------------------------------------
    var appViewModel:AppViewModel ? = null
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //===================================== LifeCycle ==============================================
    override fun onAttach(context: Context) {
        super.onAttach(context)
        appViewModel = AppViewModel.Companion.getViewModel(requireActivity())
        appViewModel!!.results.observe(requireActivity(), Observer {
            binding.rvApps.adapter = AppAdapter(requireActivity(),it)
        })
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAppBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvApps.layoutManager = LinearLayoutManager(view.context,LinearLayoutManager.HORIZONTAL,false)
        appViewModel!!.get()
    }

    //==============================================================================================

}
