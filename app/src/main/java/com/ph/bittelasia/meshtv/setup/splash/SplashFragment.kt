package com.ph.bittelasia.meshtv.setup.splash

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.ph.bittelasia.meshtv.MeshTV
import com.ph.bittelasia.meshtv.R
import com.ph.bittelasia.meshtvlibrary.fragment.splash.DefaultSplashFragment
import com.ph.bittelasia.meshtvlibrary.fragment.splash.Entities
import com.ph.bittelasia.meshtvlibrary.util.FileUtility
import kotlinx.coroutines.*
import java.io.File

@Entities(entities = [
    DefaultSplashFragment.CHANNEL,
    DefaultSplashFragment.FACILITY,
    DefaultSplashFragment.FOOD,
    DefaultSplashFragment.VC,
    DefaultSplashFragment.WEATHER,
    DefaultSplashFragment.SHOP,
    DefaultSplashFragment.APP
])
class SplashFragment: DefaultSplashFragment()
{
    //=========================================== Variable =========================================
    //--------------------------------------------- View -------------------------------------------
    var tv_status:TextView ? = null
    var pb_splash:ProgressBar ? = null
    var ctr:Int = 0;
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //=========================================== Lifecycle ========================================
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_splash,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        this.tv_status = view.findViewById(R.id.tv_status)
        this.pb_splash = view.findViewById(R.id.pb_splash)
        this.pb_splash!!.max=entityCount()
        this.pb_splash!!.progress = ctr
        super.onViewCreated(view, savedInstanceState)

    }

    //==============================================================================================
    //==================================== DefaultSplashFragment ===================================
    override fun onEnd() {
        super.onEnd()
        ctr++
        this.pb_splash!!.progress = ctr
        this.tv_status!!.text = "Data updated"
        finish()

    }

    fun finish()
    {
        CoroutineScope(Dispatchers.IO).async {
            delay((1 * 1000).toLong())
            withContext(Dispatchers.Main)
            {
               requireActivity().finish()
            }
        }
    }

    override fun onDone(type: Int) {
        ctr++
        this.pb_splash!!.progress = ctr
        when(type)
        {
            DefaultSplashFragment.CHANNEL->{
                this.tv_status!!.setText("Channels Loaded")
            }
            DefaultSplashFragment.FACILITY-> {
                this.tv_status!!.setText("Facility Loaded")
            }
            DefaultSplashFragment.FOOD-> {
                this.tv_status!!.setText("Food Loaded")
            }
            DefaultSplashFragment.VOD-> {
                this.tv_status!!.setText("VOD Loaded")
            }
            DefaultSplashFragment.VC-> {
                this.tv_status!!.setText("Concierge Loaded")
            }
            DefaultSplashFragment.WEATHER-> {
                this.tv_status!!.setText("Weather Loaded")
            }
            DefaultSplashFragment.SHOP-> {
                this.tv_status!!.setText("Shops Loaded")
            }
            DefaultSplashFragment.APP-> {
                this.tv_status!!.setText("Applications Loaded")
            }
        }

    }

    override fun onLoading(code: Int) {
        when(code)
        {
            DefaultSplashFragment.CHANNEL-> {
                this.tv_status!!.setText("Loading Channels")
            }
            DefaultSplashFragment.FACILITY-> {

                this.tv_status!!.setText("Loading Facilities")
            }
            DefaultSplashFragment.FOOD-> {

                this.tv_status!!.setText("Loading Food")
            }
            DefaultSplashFragment.VOD-> {
                this.tv_status!!.setText("Loading VODs")
            }
            DefaultSplashFragment.VC-> {
                this.tv_status!!.setText("Loading Concierge")
            }
            DefaultSplashFragment.WEATHER-> {
                this.tv_status!!.setText("Loading Weather")
            }
            DefaultSplashFragment.SHOP-> {
                this.tv_status!!.setText("Loading Shops")
            }
            DefaultSplashFragment.APP-> {
                this.tv_status!!.setText("Loading Applications")
            }
        }
    }

    override fun setRaw() {

    }
    //==============================================================================================

}