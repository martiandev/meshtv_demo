package com.ph.bittelasia.meshtv.setup.launcher

import android.util.Log
import com.ph.bittelasia.meshtv.setup.configure.ConfigurationActivity
import com.ph.bittelasia.meshtv.setup.expire.ExpirationActivity
import com.ph.bittelasia.meshtv.setup.splash.SplashActivity
import com.ph.bittelasia.meshtvlibrary.fragment.launcher.DefaultLauncherFragment

class LauncherFragment:DefaultLauncherFragment() {

    //================================= DefaultLauncherFragment ====================================
    override fun getConfigurationActivity(): Class<*> { return ConfigurationActivity::class.java }
    override fun getExpiredActivity(): Class<*> { return ExpirationActivity::class.java }
    override fun getSplashActivity(): Class<*> { return SplashActivity::class.java }
    override fun onNewMessage(from: String, body: String) {}
    override fun startHome() {}
    //==============================================================================================

}