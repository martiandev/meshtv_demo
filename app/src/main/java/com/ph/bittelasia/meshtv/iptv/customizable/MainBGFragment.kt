package com.ph.bittelasia.meshtv.iptv.customizable

import androidx.fragment.app.Fragment
import com.ph.bittelasia.meshtv.iptv.app.AppFragment
import com.ph.bittelasia.meshtvlibrary.fragment.customizable.CustomizableFragment

class MainBGFragment():CustomizableFragment()
{
    var app:AppFragment ? = null

    constructor(app:AppFragment):this()
    {
        this.app = app
    }


    override fun setFrameName(): String {
        return "AppListZone"
    }

    override fun setInnerFragment(): Fragment {
        return this.app!!
    }

}