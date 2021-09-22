package com.ph.bittelasia.meshtv.iptv.channel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.ph.bittelasia.meshtv.databinding.ItemCategoryBinding
import com.ph.bittelasia.meshtv.databinding.ItemCategorySelectedBinding
import com.ph.bittelasia.meshtvlibrary.database.entity.iptv.MeshChannelCategory
import com.ph.bittelasia.meshtvlibrary.viewmodel.iptv.MeshChannelViewModel

class ChannelCategoryAdapter(): RecyclerView.Adapter<ChannelCategoryAdapter.ViewHolder>() {

    //======================================== Variable ============================================
    //---------------------------------------- Companion -------------------------------------------
    companion object{
        val SELECTED = 0
        val NOT_SELECTED = 1
    }
    //----------------------------------------------------------------------------------------------
    //---------------------------------------- Instance --------------------------------------------
    lateinit var activity:FragmentActivity
    lateinit var vm:MeshChannelViewModel
    var selected:Int = -1
    //----------------------------------------------------------------------------------------------
    //--------------------------------------- Categories -------------------------------------------
    var list:List<MeshChannelCategory> ? = null
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //======================================= Constructor ==========================================
    constructor(activity:FragmentActivity, list:List<MeshChannelCategory>):this() {
        this.activity = activity
        this.vm = MeshChannelViewModel.getViewModel(this.activity)
        this.list = list
    }
    //==============================================================================================
    //======================================== Adapter =============================================
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if(viewType== SELECTED)
        {
            val itemBinding: ViewBinding = ItemCategorySelectedBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return ViewHolder(this.vm,itemBinding)
        }
        else
        {
            val itemBinding:ViewBinding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return ViewHolder(this.vm,itemBinding)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(this.list!!.get(position))
    }
    override fun getItemViewType(position: Int): Int
    {
        return when(position==selected)
        {
            true -> SELECTED
            else -> NOT_SELECTED
        }
    }
    override fun getItemCount(): Int { return list!!.size }
    //==============================================================================================
    //======================================= ViewHolder ===========================================
    class ViewHolder(private val vm:MeshChannelViewModel,private val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(category:MeshChannelCategory)
        {
            if(binding is ItemCategorySelectedBinding)
            {
                binding.channelCategory = category
                binding.channelVM = vm
            }
            else if(binding is ItemCategoryBinding)
            {
                binding.channelCategory = category
                binding.channelVM = vm
            }

        }
    }
    //==============================================================================================
}