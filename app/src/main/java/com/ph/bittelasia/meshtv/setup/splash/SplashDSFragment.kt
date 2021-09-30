package com.ph.bittelasia.meshtv.setup.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ph.bittelasia.meshtv.R
import com.ph.bittelasia.meshtvlibrary.fragment.splash.DefaultDSSplashFragment
import java.io.File

class SplashDSFragment: DefaultDSSplashFragment()
{
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_splash,container,false)
    }

    override fun onDone(type: Int) {}
    override fun onLoading(fileName: String) {}
    override fun onMediaDownloaded(i: Int, total: Int, f: File) {}
    override fun onMediaLoaded() { requireActivity().finish() }
    override fun setRaw() {}

}