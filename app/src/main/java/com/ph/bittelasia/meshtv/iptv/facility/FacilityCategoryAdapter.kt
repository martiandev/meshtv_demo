package com.ph.bittelasia.meshtv.iptv.facility

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.ph.bittelasia.meshtv.databinding.ItemFacilityCategorySelectedBinding
import com.ph.bittelasia.meshtv.databinding.ItemFaciltyCategoryBinding
import com.ph.bittelasia.meshtv.iptv.channel.ChannelCategoryAdapter
import com.ph.bittelasia.meshtvlibrary.database.entity.iptv.MeshFacility
import com.ph.bittelasia.meshtvlibrary.database.entity.iptv.MeshFacilityCategory
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
    var categories:List<MeshFacilityCategory> ?= null
    lateinit var activity:FragmentActivity
    var vm:MeshFacilityViewModel? = null
    var observer:Observer<List<MeshFacility>> = Observer {
        if(it.size>0)
        {
            var ctr = 0
            for(c in categories!!)
            {
                if(c.id == it.get(0).category_id)
                {
                    selected = ctr
                    notifyDataSetChanged()
                    break
                }
                ctr++
            }

        }
    }
    //----------------------------------------------------------------------------------------------
    //--------------------------------------- Categories -------------------------------------------
    var list:List<MeshFacilityCategory> ? = null
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //======================================= Constructor ==========================================
    constructor(activity: FragmentActivity, list:List<MeshFacilityCategory>):this()
    {
        this.categories = list!!
        this.activity = activity
        this.vm = MeshFacilityViewModel.getViewModel(this.activity)
        this.list = list
        this.vm!!.catResult.observe(this.activity,observer)
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
        holder.bind(this.list!!.get(position))

    }

    override fun getItemViewType(position: Int): Int
    {
        return when(position==selected)
        {
            true -> ChannelCategoryAdapter.SELECTED
            else -> ChannelCategoryAdapter.NOT_SELECTED
        }

    }

    override fun getItemCount(): Int { return list!!.size }
    //==============================================================================================
    //======================================= ViewHolder ===========================================

    class ViewHolder(
            private val vm:MeshFacilityViewModel,
            private val binding:ViewBinding
        ) :RecyclerView.ViewHolder(binding.root)
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