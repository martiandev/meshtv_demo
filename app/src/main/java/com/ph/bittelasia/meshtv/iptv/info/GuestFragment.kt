package com.ph.bittelasia.meshtv.iptv.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ph.bittelasia.meshtv.R
import com.ph.bittelasia.meshtvlibrary.fragment.guest.DefaultGuestFragment
import com.ph.bittelasia.meshtvlibrary.preference.`object`.iptv.MeshGuest

class GuestFragment: DefaultGuestFragment()
{
    //=========================================== Variable =========================================
    //---------------------------------------------- View ------------------------------------------
    var tv_guest:TextView ? = null
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //=========================================== LifeCycle ========================================
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(container!!.context).inflate(R.layout.fragment_guest,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.tv_guest = view.findViewById(R.id.tv_guest)
    }

    //==============================================================================================
    //======================================== DefaultGuestFragment ================================
    override fun onCheckIn(guest: MeshGuest) {
        this.tv_guest!!.text = guest.firstname + " " +guest!!.lastname
    }

    override fun onCheckOut(guest: MeshGuest) {
        this.tv_guest!!.text = guest.firstname + " " +guest!!.lastname
    }

    override fun onLoad(guest: MeshGuest) {
        this.tv_guest!!.text = guest.firstname + " " +guest!!.lastname
    }
    //==============================================================================================

}