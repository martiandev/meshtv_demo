package com.ph.bittelasia.meshtv.setup.expire

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ph.bittelasia.meshtv.databinding.FragmentExpirationBinding
import com.ph.bittelasia.meshtvlibrary.fragment.expiry.DefaultExpiryFragment

class ExpirationFragment:DefaultExpiryFragment()
{
    //=========================================== Variable =========================================
    //-------------------------------------------- Binding -----------------------------------------
    private var _binding: FragmentExpirationBinding? = null
    private val binding get() = _binding!!
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //===================================== LifeCycle ==============================================
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        _binding = FragmentExpirationBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    //==============================================================================================
    //======================================== DefaultExpiryFragment ===============================
    override fun onLicensed() { requireActivity().finish() }
    override fun onUnLicensed() {}
    //==============================================================================================
}