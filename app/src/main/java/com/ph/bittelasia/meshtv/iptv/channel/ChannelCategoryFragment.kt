package com.ph.bittelasia.meshtv.iptv.channel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ph.bittelasia.meshtv.databinding.FragmentCategoryBinding
import com.ph.bittelasia.meshtvlibrary.database.entity.iptv.MeshChannel
import com.ph.bittelasia.meshtvlibrary.database.entity.iptv.MeshChannelCategory
import com.ph.bittelasia.meshtvlibrary.viewmodel.iptv.MeshChannelCategoryViewModel
import com.ph.bittelasia.meshtvlibrary.viewmodel.iptv.MeshChannelViewModel

class ChannelCategoryFragment:Fragment()
{
    //======================================== Variable ============================================
    //-------------------------------------- Binding -----------------------------------------------
    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!
    //----------------------------------------------------------------------------------------------
    //---------------------------------------- Instance --------------------------------------------
    var selected:Int = 0
    var vm:MeshChannelViewModel? = null
    var categoryVm:MeshChannelCategoryViewModel? = null
    var categoryObserver: Observer<List<MeshChannelCategory>>? = null
    var observer: Observer<List<MeshChannel>>? = null
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //======================================= LifeCycle ============================================
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(isAdded)
        {
            binding.rvCategory!!.layoutManager = LinearLayoutManager(requireActivity())
            this.vm = MeshChannelViewModel.getViewModel(requireActivity())
            this.categoryVm = MeshChannelCategoryViewModel.getViewModel(requireActivity())
            this.categoryObserver = Observer {
                if(isAdded) {
                    updateCategories(it)
                    vm!!.getCatResult(selected)
                }
            }
            this.observer = Observer {
                if(it.size>0) { selected = it!!.get(0)!!.channel_category_id!! }
                else{ selected=0 }
            }
            this.vm!!.catResult.observe(requireActivity(), observer!!)
            this.categoryVm!!.results.observe(requireActivity(), categoryObserver!!)
            this.categoryVm!!.get()
        }
    }
    //==============================================================================================
    //======================================== Categories ==========================================
    fun updateCategories(categories:List<MeshChannelCategory>)
    {
        binding.rvCategory!!.adapter = ChannelCategoryAdapter(requireActivity(),categories!!)
    }
    //==============================================================================================
}