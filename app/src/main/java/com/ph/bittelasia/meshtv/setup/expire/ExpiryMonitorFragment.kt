package com.ph.bittelasia.meshtv.setup.expire

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ph.bittelasia.meshtv.R
import com.ph.bittelasia.meshtvlibrary.fragment.expiry.DefaultExpiryMonitorFragment

class ExpiryMonitorFragment:DefaultExpiryMonitorFragment() {

    //============================================ Variable ========================================
    var tv_expire:TextView ? = null
    //==============================================================================================
    //============================================ Lifecycle =======================================
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(container!!.context).inflate(R.layout.fragment_expiry_monitor,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_expire = view.findViewById(R.id.tv_expire)

    }

    //==============================================================================================
    //========================================= MonitorFragment ====================================
    override fun expired() {
        tv_expire!!.text = "Subscription expired"
    }
    override fun onWarning(days: Int) {

        tv_expire!!.text = "Subscription will expire in "+days+" day"+when{
            (days>1) -> "s"
            else -> ""
        }+" please contact your service provider"
    }
    //==============================================================================================

}