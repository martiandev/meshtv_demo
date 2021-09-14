package com.ph.bittelasia.meshtv.iptv.message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ph.bittelasia.meshtv.R
import com.ph.bittelasia.meshtv.iptv.facility.FacilityCategoryFragment
import com.ph.bittelasia.meshtv.iptv.facility.FacilityListFragment
import com.ph.bittelasia.meshtvlibrary.viewmodel.iptv.MeshMessageViewModel

class MessageFragment : Fragment(){
    //=========================================== Variable =========================================
    //------------------------------------------- Constant -----------------------------------------
    companion object
    {
        val APP_ID:Int = 16
    }
    //----------------------------------------------------------------------------------------------
    //------------------------------------------ ViewModel -----------------------------------------
    var mvm:MeshMessageViewModel ? = null
    //----------------------------------------------------------------------------------------------
    //------------------------------------------- Fragment -----------------------------------------
    var inboxFragment:InboxFragment ? = null
    //----------------------------------------------------------------------------------------------
    //------------------------------------------- Instance -----------------------------------------
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //=========================================== LifeCycle ========================================
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(container!!.context).inflate(R.layout.fragment_iptv_message,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(isAdded)
        {
            this.mvm = MeshMessageViewModel.getViewModel(requireActivity())
            this.mvm!!.results.observe(requireActivity(), Observer {
                if(inboxFragment!=null)
                {
                    inboxFragment!!.updateMessages(it)
                }
            })
            attachFragments()
            load()
        }
    }

    //==============================================================================================
    //============================================ Method ==========================================
    fun attachFragments()
    {
        this.inboxFragment = InboxFragment(this.mvm!!)
        childFragmentManager.beginTransaction().add(R.id.fc_category,this.inboxFragment!!,"Inbox").commit()
    }
    fun load()
    {
        this.mvm!!.get()
    }
    //==============================================================================================


}