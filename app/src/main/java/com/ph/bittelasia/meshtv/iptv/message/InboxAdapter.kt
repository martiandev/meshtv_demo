package com.ph.bittelasia.meshtv.iptv.message
import android.util.Log
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
    //------------------------------------------- Listener -----------------------------------------
    var listener: InboxFragment.ClickListener? = null
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //======================================== Constructor =========================================
    constructor(activity:FragmentActivity,messages:List<MeshMessage>,listener: InboxFragment.ClickListener):this()
    {
        this.activity = activity
        this.messages = messages
        this.vm = MeshMessageViewModel.getViewModel(this.activity!!)
        this.listener = listener
    }
    //==============================================================================================
    //======================================== AppAdapter ==========================================
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if(viewType==1)
        {
            return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_inbox,parent,false))
        }
        else
        {
            return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_inbox_read,parent,false))
        }

    }
    override fun getItemViewType(position: Int): Int {
        var message:MeshMessage = this.messages!!.get(position)
        Log.i("message-status","("+message.id+") status:"+message!!.messg_status)
        if(message!!.messg_status==2)
        {
            return  0
        }
        return 1
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var message : MeshMessage = this.messages!!.get(position)
        holder.tv_subject!!.text = message.messg_subject
        holder.tv_name!!.text = message.messg_from
        holder.tv_date!!.text = message.messg_datetime
        holder.itemView.setOnClickListener {
            if(message!!.messg_status!=2)
            {
                vm!!.read(this,message)

            }
            if(listener!=null)
            {
                listener!!.onClick(message)
            }

        }
    }
    override fun getItemCount(): Int { return this.messages!!.size }
    //==============================================================================================
    //========================================= ViewHolder =========================================
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var tv_name: TextView = itemView.findViewById(R.id.tv_name)
        var tv_subject: TextView = itemView.findViewById(R.id.tv_subject)
        var tv_date: TextView = itemView.findViewById(R.id.tv_date)
    }

    //==============================================================================================
    //========================================== APIListener =======================================
    override fun onFail(result: Any, type: Int) {
    }

    override fun onSuccess(result: Any, type: Int) {

    }
    //==============================================================================================
}




