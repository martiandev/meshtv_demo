package com.ph.bittelasia.meshtv.iptv.message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ph.bittelasia.meshtv.R
import com.ph.bittelasia.meshtvlibrary.database.entity.iptv.MeshMessage
import com.ph.bittelasia.meshtvlibrary.viewmodel.iptv.MeshMessageViewModel

class InboxFragment(): Fragment() {
    //========================================= Variable ===========================================
    //---------------------------------------- ViewModel -------------------------------------------
    var mvm:MeshMessageViewModel ? = null
    //----------------------------------------------------------------------------------------------
    //------------------------------------------ View ----------------------------------------------
    var rv_category: RecyclerView? = null
    //----------------------------------------------------------------------------------------------
    //--------------------------------------- Categories -------------------------------------------
    var messages: List<MeshMessage>? = null
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //========================================= Constructor ========================================
    constructor(mvm: MeshMessageViewModel):this()
    {
        this.mvm = mvm
    }
    //==============================================================================================
    //========================================= LifeCycle ==========================================
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_category,container,false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.rv_category = view.findViewById(R.id.rv_category)
        this.rv_category!!.layoutManager = LinearLayoutManager(requireActivity())
    }
    //==============================================================================================
    //======================================== Messages ==========================================
    fun updateMessages(messages:List<MeshMessage>)
    {
        if(isAdded)
        {
            this.messages = messages
            this.rv_category!!.adapter = InboxAdapter(requireActivity(),this.messages!!.reversed())
        }

    }
    //==============================================================================================

}