package com.ph.bittelasia.meshtv.iptv.message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.ph.bittelasia.meshtv.R
import com.ph.bittelasia.meshtvlibrary.api.APIListener
import com.ph.bittelasia.meshtvlibrary.api.APIManager
import com.ph.bittelasia.meshtvlibrary.database.entity.iptv.MeshMessage
import com.ph.bittelasia.meshtvlibrary.viewmodel.iptv.MeshMessageViewModel

class InboxAdapter(): RecyclerView.Adapter<InboxAdapter.ViewHolder>(){
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
        this.activity = activity
        this.messages = messages
        this.vm = MeshMessageViewModel.getViewModel(this.activity!!)
    }
    //==============================================================================================
    //======================================== AppAdapter ==========================================
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if(viewType==1)
        {
            return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_inbox_read,parent,false))
        }
        else
        {
            return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_inbox,parent,false))
        }

    }
    override fun getItemViewType(position: Int): Int {
        var message:MeshMessage = this.messages!!.get(position)
        if(message!!.messg_status==MeshMessage.STATUS_READ)
        {
            return  0
        }
        return 1
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var message : MeshMessage = this.messages!!.get(position)
        holder.tv_name!!.text = message.messg_subject
        holder.tv_date!!.text = message.messg_datetime
        holder.itemView.setOnClickListener {
//            APIManager.get().readMessage()
            APIManager.get()!!.readMessage(null,message!!.id)
        }
    }
    override fun getItemCount(): Int { return this.messages!!.size }
    //==============================================================================================
    //========================================= ViewHolder =========================================
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var tv_name: TextView = itemView.findViewById(R.id.tv_name)
        var tv_date: TextView = itemView.findViewById(R.id.tv_date)
    }
    //==============================================================================================
}




