package com.ph.bittelasia.meshtv.setup.expire

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ph.bittelasia.meshtv.databinding.FragmentExpiryMonitorBinding
import com.ph.bittelasia.meshtvlibrary.fragment.expiry.DefaultExpiryMonitorFragment
import com.ph.bittelasia.meshtvlibrary.preference.manager.LanguagePreference

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //==============================================================================================
    //========================================= MonitorFragment ====================================

    override fun onWarning(days: Int) {
        binding!!.tvExpire!!.text =       LanguagePreference.get()!!.getLang("FirstPartLicense")+" "+days+" day"+when{
            (days>1) -> "s"
            else -> ""
        }+". "+      LanguagePreference.get()!!.getLang("SecondPartLicense")
    }
    override fun minDays(): Int { return 30 }
    override fun onDanger(hours: Int) {}
    override fun onExpired() { requireActivity().finish() }
    //==============================================================================================

}