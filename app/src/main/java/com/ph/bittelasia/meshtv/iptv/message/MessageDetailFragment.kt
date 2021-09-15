package com.ph.bittelasia.meshtv.iptv.message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ph.bittelasia.meshtv.R
import com.ph.bittelasia.meshtvlibrary.database.entity.iptv.MeshFacility
import com.ph.bittelasia.meshtvlibrary.database.entity.iptv.MeshMessage

class MessageDetailFragment:Fragment()
{
    //========================================== Variable ==========================================
    //-------------------------------------------- List --------------------------------------------
    var message:MeshMessage ? = null
    //----------------------------------------------------------------------------------------------
    //--------------------------------------------- View -------------------------------------------
    var cl_details:View ? = null
    var tv_label: TextView? = null
    var tv_subject: TextView? = null
    var tv_sender: TextView? = null
    var tv_content: TextView? = null
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //========================================= Lifecycle ==========================================
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_iptv_inbox,container,false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(isAdded)
        {
            this.tv_label   = view.findViewById(R.id.tv_label)
            this.cl_details = view.findViewById(R.id.cl_details)
            this.tv_subject = view.findViewById(R.id.tv_subject)
            this.tv_sender  = view.findViewById(R.id.tv_sender)
            this.tv_content = view.findViewById(R.id.tv_content)
        }
    }
    //==============================================================================================
    //========================================= Method =============================================
    fun update(message:MeshMessage)
    {
        this.message = message

            this.tv_label!!.visibility = View.GONE
            this.cl_details!!.visibility = View.VISIBLE
            this.tv_subject!!.text = message!!.messg_subject
            this.tv_sender!!.text = message!!.messg_from
            this.tv_content!!.setText(HtmlCompat.fromHtml(message!!.messg_text!!, 0))

    }
    //==============================================================================================

}