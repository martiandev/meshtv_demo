package com.ph.bittelasia.meshtv.iptv.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ph.bittelasia.meshtv.databinding.FragmentGuestBinding
import com.ph.bittelasia.meshtvlibrary.fragment.guest.DefaultGuestFragment
import com.ph.bittelasia.meshtvlibrary.preference.`object`.iptv.MeshGuest

class GuestFragment: DefaultGuestFragment()
{
    //=========================================== Variable =========================================
    //------------------------------------------ Binding -------------------------------------------
    private var _binding: FragmentGuestBinding? = null
    private val binding get() = _binding!!
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //=========================================== LifeCycle ========================================
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentGuestBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    //==============================================================================================
    //======================================== DefaultGuestFragment ================================
    override fun onCheckIn(guest: MeshGuest) {
        binding.tvGuest!!.text = guest.firstname+" "+guest!!.lastname
    }

    override fun onCheckOut(guest: MeshGuest) {
        binding.tvGuest!!.text = guest.firstname +" "+guest!!.lastname
    }

    override fun onLoad(guest: MeshGuest) {
        binding.tvGuest!!.text = guest.firstname +" "+guest!!.lastname
    }
    //==============================================================================================

}