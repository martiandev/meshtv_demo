package com.ph.bittelasia.meshtv.iptv.message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ph.bittelasia.meshtv.databinding.FragmentCategoryBinding
import com.ph.bittelasia.meshtvlibrary.database.entity.iptv.MeshMessage
import com.ph.bittelasia.meshtvlibrary.viewmodel.iptv.MeshMessageViewModel

class InboxFragment(): Fragment() {
    //========================================= Variable ===========================================
    //------------------------------------------ Binding -------------------------------------------
    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!
    //----------------------------------------------------------------------------------------------
    //---------------------------------------- ViewModel -------------------------------------------
    var mvm:MeshMessageViewModel? = null
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //========================================= LifeCycle ==========================================
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.mvm = MeshMessageViewModel.getViewModel(requireActivity())
        this.binding.rvCategory!!.layoutManager = LinearLayoutManager(requireActivity())
        this.mvm!!.results.observe(
            requireActivity(),
            Observer {
                updateMessages(it)
            }
        )
        this.mvm!!.get()
    }
    //==============================================================================================
    //======================================== Messages ==========================================
    fun updateMessages(messages:List<MeshMessage>)
    {
        if(isAdded)
        {
            this.binding.rvCategory!!.adapter = InboxAdapter(requireActivity(),messages!!.reversed())
        }
    }
    //==============================================================================================
 }