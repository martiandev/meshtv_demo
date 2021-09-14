package com.ph.bittelasia.meshtv.iptv.channel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ph.bittelasia.meshtv.R
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
    var selected:Int = -1
    var vm:MeshChannelViewModel? = null
    //----------------------------------------------------------------------------------------------
    //--------------------------------------- Categories -------------------------------------------
    var list:List<MeshChannelCategory> ? = null
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //======================================= Constructor ==========================================
    constructor(vm: MeshChannelViewModel, list:List<MeshChannelCategory>):this()
    {
        this.vm = vm
        this.list = list
    }
    //==============================================================================================
    //======================================== Adapter =============================================
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if(viewType== SELECTED)
        {
            return ViewHolder(LayoutInflater.from(parent.context!!).inflate(R.layout.item_category_selected,parent,false))
        }
        else
        {
            return ViewHolder(LayoutInflater.from(parent.context!!).inflate(R.layout.item_category,parent,false))
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var cat:MeshChannelCategory = this.list!!.get(position)
        holder.itemView!!.setOnClickListener {
            selected = position
            notifyDataSetChanged()
            vm!!.getCatResult(cat.id)
        }
        holder.tv_name!!.text = cat.category_name
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
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tv_name: TextView ? = itemView.findViewById(R.id.tv_name)

    }


    //==============================================================================================
}