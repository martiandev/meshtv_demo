package com.ph.bittelasia.meshtv.setup.configure

import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.ph.bittelasia.meshtv.R
import com.ph.bittelasia.meshtvlibrary.api.APIManager
import com.ph.bittelasia.meshtvlibrary.fragment.config.DefaultConfigurationFragment
import com.ph.bittelasia.meshtvlibrary.preference.`object`.license.MeshLicense
import com.ph.bittelasia.meshtvlibrary.preference.manager.LicensePreference
import com.ph.bittelasia.meshtvlibrary.preference.manager.MeshSTBPreference

class ConfigurationFragment:DefaultConfigurationFragment()
{
    //=========================================== Variable =========================================
    //--------------------------------------------- View -------------------------------------------
    var et_host:    EditText?       = null
    var et_port:    EditText?       = null
    var et_room:    EditText?       = null
    var sw_live:    Switch?         = null
    var bt_submit:  Button?         = null
    var rb_iptv:    RadioButton?    = null
    var rb_ds:      RadioButton?    = null
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //========================================== LifeCycle =========================================
    override fun onAttach(context: Context) {
        super.onAttach(context)
        license = MeshLicense()
        LicensePreference!!.get()!!.load(license!!)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_configuration,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadView(view)

    }

    //==============================================================================================
    //========================================== Method ============================================
    fun loadView(view:View)
    {

        et_host     = view.findViewById(R.id.et_host)
        et_host!!.isEnabled = license!!.is_live==true
        et_host!!.setText(stb!!.host!!)

        et_port     = view.findViewById(R.id.et_port)
        et_port!!.isEnabled = license!!.is_live==true
        et_port!!.setText(stb!!.port!!)

        et_room     = view.findViewById(R.id.et_room)
        et_room!!.setText(stb!!.room!!)

        sw_live     = view.findViewById(R.id.sw_live)
        sw_live!!.setOnCheckedChangeListener{ compoundButton: CompoundButton, b: Boolean ->
            license!!.is_live   = b
            et_host!!.isEnabled = b
            et_port!!.isEnabled = b
            LicensePreference.get()!!.save(license!!)
        }
        sw_live!!.isChecked = license!!.is_live==true

        bt_submit   = view.findViewById(R.id.bt_submit)
        bt_submit!!.setOnClickListener {
            if(license!!.is_live==true)
            {
//                APIManager.get()!!.updateTime(requireActivity())
                startLive(et_host!!.text.toString(),et_port!!.text.toString(),et_room!!.text.toString())
            }
            else
            {
                startDemo()
            }
        }

        rb_iptv     = view.findViewById(R.id.rb_iptv)
        rb_iptv!!.isChecked = license!!.is_ds==false
        rb_iptv!!.setOnCheckedChangeListener { compoundButton:CompoundButton, b:Boolean ->
            license!!.is_ds = !b
            LicensePreference.get()!!.save(license!!)
        }

        rb_ds       = view.findViewById(R.id.rb_ds)
        rb_ds!!.isChecked = license!!.is_ds==true
        rb_ds!!.setOnCheckedChangeListener { compoundButton:CompoundButton, b:Boolean ->
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