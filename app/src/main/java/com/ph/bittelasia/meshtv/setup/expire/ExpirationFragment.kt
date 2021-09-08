package com.ph.bittelasia.meshtv.setup.expire

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ph.bittelasia.meshtv.R
import com.ph.bittelasia.meshtvlibrary.fragment.expiry.DefaultExpiryFragment

class ExpirationFragment:DefaultExpiryFragment()
{
    //===================================== LifeCycle ==============================================
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        var v: View = inflater.inflate(R.layout.fragment_expiration,container,false)
        return v
    }

    //==============================================================================================
    //================================ DefaultExpiryFragment =======================================
    override fun onLicensed() {
        requireActivity().finish()
    }

    override fun onUnLicensed() {

    }
    //==============================================================================================

}