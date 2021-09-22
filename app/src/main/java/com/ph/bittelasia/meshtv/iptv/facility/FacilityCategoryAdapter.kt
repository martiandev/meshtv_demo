package com.ph.bittelasia.meshtv.iptv.facility

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.ph.bittelasia.meshtv.R
import com.ph.bittelasia.meshtv.databinding.ItemCategoryBinding
import com.ph.bittelasia.meshtv.databinding.ItemCategorySelectedBinding
import com.ph.bittelasia.meshtv.databinding.ItemFacilityCategorySelectedBinding
import com.ph.bittelasia.meshtv.databinding.ItemFaciltyCategoryBinding
import com.ph.bittelasia.meshtvlibrary.database.entity.iptv.MeshChannelCategory
import com.ph.bittelasia.meshtvlibrary.database.entity.iptv.MeshFacility
import com.ph.bittelasia.meshtvlibrary.database.entity.iptv.MeshFacilityCategory
import com.ph.bittelasia.meshtvlibrary.viewmodel.iptv.MeshChannelViewModel
import com.ph.bittelasia.meshtvlibrary.viewmodel.iptv.MeshFacilityViewModel

class FacilityCategoryAdapter(): RecyclerView.Adapter<FacilityCategoryAdapter.ViewHolder>() {

    //======================================== Variable ============================================
    //---------------------------------------- Companion -------------------------------------------
    companion object{
        val SELECTED = 0
        val NOT_SELECTED = 1
    }
    //----------------------------------------------------------------------------------------------
    //---------------------------------------- Instance --------------------------------------------
    var selected:Int = -1
    var vm:MeshFacilityViewModel? = null
    //----------------------------------------------------------------------------------------------
    //--------------------------------------- Categories -------------------------------------------
    var list:List<MeshFacilityCategory> ? = null
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //======================================= Constructor ==========================================
    constructor(vm: MeshFacilityViewModel, list:List<MeshFacilityCategory>):this()
    {
        this.vm = vm
        this.list = list
    }
    //==============================================================================================
    //======================================== Adapter =============================================
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if(viewType== SELECTED)
        {
            val itemBinding: ViewBinding = ItemFacilityCategorySelectedBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return ViewHolder(vm!!,itemBinding)
        }
        else
        {
            val itemBinding:ViewBinding = ItemFaciltyCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return ViewHolder(vm!!,itemBinding)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var cat:MeshFacilityCategory = this.list!!.get(position)
        holder.bind(cat)

    }

    override fun getItemViewType(position: Int): Int
    {
        if(position==selected)
        {
            return SELECTED
        }
        else
        {
            return NOT_SELECTED
        }

    }

    override fun getItemCount(): Int { return list!!.size }
    //==============================================================================================
    //======================================= ViewHolder ===========================================

    class ViewHolder(private val vm:MeshFacilityViewModel,private val binding:ViewBinding)
        :RecyclerView.ViewHolder(binding.root)
    {
        fun bind(category: MeshFacilityCategory)
        {
            if(binding is ItemFacilityCategorySelectedBinding)
            {
                binding.category = category!!
                binding.facilityVM = vm
            }
            else if(binding is ItemFaciltyCategoryBinding)
            {
                binding.category = category!!
                binding.facilityVM = vm
            }

        }
    }

    //==============================================================================================
}