package com.ph.bittelasia.meshtv.setup.configure

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.ph.bittelasia.meshtv.databinding.FragmentConfigurationBinding
import com.ph.bittelasia.meshtvlibrary.fragment.config.DefaultConfigurationFragment
import com.ph.bittelasia.meshtvlibrary.preference.manager.LicensePreference

class ConfigurationFragment:DefaultConfigurationFragment()
{
    //=========================================== Variable =========================================
    //-------------------------------------- Binding -----------------------------------------------
    private var _binding: FragmentConfigurationBinding? = null
    private val binding get() = _binding!!
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //========================================== LifeCycle =========================================
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentConfigurationBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadView()
    }
    //==============================================================================================
    //========================================== Method ============================================
    fun loadView()
    {
        binding.etHost!!.isEnabled = license!!.is_live==true
        binding.etHost!!.setText(stb!!.host!!)
        binding.etPort!!.isEnabled = license!!.is_live==true
        binding.etPort!!.setText(stb!!.port!!)
        binding.etRoom!!.setText(stb!!.room!!)

        binding.swLive!!.setOnCheckedChangeListener{ compoundButton: CompoundButton, b: Boolean ->
            license!!.is_live   = b
            binding.etHost!!.isEnabled = b
            binding.etPort!!.isEnabled = b
            LicensePreference.get()!!.save(license!!)
        }

        binding.swLive!!.isChecked = license!!.is_live==true
        binding.btSubmit!!.setOnClickListener {
            if(license!!.is_live==true)
            {
                startLive(
                    binding.etHost!!.text.toString(),
                    binding.etPort!!.text.toString(),
                    binding.etRoom!!.text.toString()
                )
            }
            else
            {
                startDemo()
            }
        }

        binding.rbIptv!!.isChecked = license!!.is_ds==false
        binding.rbIptv!!.setOnCheckedChangeListener { compoundButton:CompoundButton, b:Boolean ->
            license!!.is_ds = !b
            LicensePreference.get()!!.save(license!!)
        }

        binding.rbDs!!.isChecked = license!!.is_ds==true
        binding.rbDs!!.setOnCheckedChangeListener { compoundButton:CompoundButton, b:Boolean ->
            license!!.is_ds = b
            LicensePreference.get()!!.save(license!!)
        }

    }
    //==============================================================================================
    //================================= DefaultConfigurationFragment ===============================
    override fun onDone() { requireActivity().finish() }
    override fun onFail(listenerID: Int, message: String) {}
    override fun onResult(listenerID: Int, message: String, result: Any) {}
    override fun onSuccess(listenerID: Int, message: String) {}
    override fun pingResult(result: Boolean) {}
    //==============================================================================================
}