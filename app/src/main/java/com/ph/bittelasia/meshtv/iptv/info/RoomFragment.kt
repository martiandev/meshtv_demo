package com.ph.bittelasia.meshtv.iptv.info

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.ph.bittelasia.meshtv.R
import com.ph.bittelasia.meshtvlibrary.preference.`object`.iptv.MeshSTB
import com.ph.bittelasia.meshtvlibrary.preference.manager.MeshSTBPreference

class RoomFragment: Fragment()
{
    //=========================================== Variable =========================================
    //--------------------------------------------- View -------------------------------------------
    var tv_room:TextView ?  = null
    //----------------------------------------------------------------------------------------------
    //-------------------------------------------- Instance ----------------------------------------
    var stb:MeshSTB ? = null

    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //=========================================== Lifecycle ========================================
    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.stb = MeshSTB(context)
        MeshSTBPreference.get()!!.load(this.stb!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(container!!.context).inflate(R.layout.fragment_room,container,false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)
        this.tv_room = view.findViewById(R.id.tv_room)
        this.tv_room!!.text = this.stb!!.room

    }
    //==============================================================================================
}