package com.ph.bittelasia.meshtv.iptv.message
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

import com.ph.bittelasia.meshtv.databinding.ItemInboxBinding
import com.ph.bittelasia.meshtv.databinding.ItemInboxReadBinding
import com.ph.bittelasia.meshtvlibrary.api.APIListener
import com.ph.bittelasia.meshtvlibrary.database.entity.iptv.MeshMessage
import com.ph.bittelasia.meshtvlibrary.viewmodel.iptv.MeshMessageViewModel

class InboxAdapter(): RecyclerView.Adapter<InboxAdapter.ViewHolder>(),APIListener{
    //========================================== Variable ==========================================
    //----------------------------------------- ViewModel ------------------------------------------
    var vm:MeshMessageViewModel ? = null
    //----------------------------------------------------------------------------------------------
    //------------------------------------------- View ---------------------------------------------
    var activity:FragmentActivity ? = null
    //----------------------------------------------------------------------------------------------
    //-------------------------------------------- Apps --------------------------------------------
    var messages:List<MeshMessage>? = null
    //----------------------------------------------------------------------------------------------
     //==============================================================================================
    //======================================== Constructor =========================================
    constructor(activity:FragmentActivity,messages:List<MeshMessage>):this()
    {
        this.activity   = activity
        this.messages   = messages
        this.vm         = MeshMessageViewModel.getViewModel(this.activity!!)
    }
    //==============================================================================================
    //======================================== AppAdapter ==========================================
    override fun onCreateViewHolder(parent:ViewGroup, viewType:Int): ViewHolder {
        if(viewType==1)
        {
            val itemBinding: ViewBinding = ItemInboxBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return ViewHolder(itemBinding!!,vm!!)
        }
        else
        {
            val itemBinding: ViewBinding = ItemInboxReadBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return ViewHolder(itemBinding!!,vm!!)
        }
    }
    override fun getItemViewType(position:Int): Int {
        var message:MeshMessage = this.messages!!.get(position)
        if(message!!.messg_status==2)
        {
            return  0
        }
        return 1
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) { holder.bind(this.messages!!.get(position)) }
    override fun getItemCount(): Int { return this.messages!!.size }
    //==============================================================================================
    //========================================= ViewHolder =========================================
    class ViewHolder(private val binding:ViewBinding, private val vm:MeshMessageViewModel) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(message:MeshMessage)
        {
            if(binding is ItemInboxBinding)
            {
                binding.message     = message!!
                binding.messageVM   = vm!!
            }
            else if(binding is ItemInboxReadBinding)
            {
                binding.message     = message!!
                binding.messageVM   = vm!!
            }
        }
    }
    //==============================================================================================
    //========================================== APIListener =======================================
    override fun onFail(result:Any, type:Int) {}
    override fun onSuccess(result:Any, type:Int) {}
    //==============================================================================================
}




