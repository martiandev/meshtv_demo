package com.ph.bittelasia.meshtv.iptv.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ph.bittelasia.meshtv.databinding.FragmentExpiryMonitorBinding
import com.ph.bittelasia.meshtvlibrary.fragment.expiry.DefaultExpiryMonitorFragment
import com.ph.bittelasia.meshtvlibrary.preference.`object`.license.MeshLicense
import com.ph.bittelasia.meshtvlibrary.preference.manager.LanguagePreference
import com.ph.bittelasia.meshtvlibrary.preference.manager.LicensePreference

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

        var license = MeshLicense()
        LicensePreference.get()!!.load(license)
        binding!!.tvExpire.visibility = View.VISIBLE
        binding!!.tvExpire!!.text = LanguagePreference.get()!!.getLang("FirstPartLicense")+" "+days+" day"+when{
            (days>1) -> "s"
            else -> ""
        }+". "+      LanguagePreference.get()!!.getLang("SecondPartLicense")
    }

    override fun licensed() {
        binding!!.tvExpire.visibility = View.GONE
    }

    override fun minDays(): Int { return 30 }
    override fun onDanger(hours: Int) {
        binding!!.tvExpire.visibility = View.VISIBLE
        binding!!.tvExpire!!.text = ""+hours+" hr(s) left"

    }

    override fun onExpired() {
        requireActivity().finish()
    }
    //==============================================================================================

}