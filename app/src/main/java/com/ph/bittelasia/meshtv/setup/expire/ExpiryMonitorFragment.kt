package com.ph.bittelasia.meshtv.setup.expire

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ph.bittelasia.meshtv.databinding.FragmentExpiryMonitorBinding
import com.ph.bittelasia.meshtvlibrary.fragment.expiry.DefaultExpiryMonitorFragment

class ExpiryMonitorFragment:DefaultExpiryMonitorFragment() {

    //============================================ Variable ========================================
    //------------------------------------------ Binding -------------------------------------------
    private var _binding: FragmentExpiryMonitorBinding? = null
    private val binding get() = _binding!!
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //============================================ Lifecycle =======================================
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentExpiryMonitorBinding.inflate(inflater, container, false)
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
    //========================================= MonitorFragment ====================================
    override fun expired() {
        binding!!.tvExpire!!.text = "Subscription expired"
    }
    override fun onWarning(days: Int) {

        binding!!.tvExpire!!.text = "Subscription will expire in "+days+" day"+when{
            (days>1) -> "s"
            else -> ""
        }+" please contact your service provider"
    }
    //==============================================================================================

}