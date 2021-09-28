package com.ph.bittelasia.meshtv.iptv.message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ph.bittelasia.meshtv.R
import com.ph.bittelasia.meshtv.databinding.FragmentIptvMessageBinding

class MessageFragment : Fragment(){
    //=========================================== Variable =========================================
    //------------------------------------------- Constant -----------------------------------------
    companion object
    {
        val APP_ID:Int = 16
    }
    //----------------------------------------------------------------------------------------------
    //------------------------------------------ Binding -------------------------------------------
    private var _binding: FragmentIptvMessageBinding? = null
    private val binding get() = _binding!!
    //----------------------------------------------------------------------------------------------

    //------------------------------------------- Fragment -----------------------------------------
    var inboxFragment:InboxFragment ? = null
    var messageDetailFragment:MessageDetailFragment ? = null
    //---------------------------------------------------------------------------------------------- //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //=========================================== LifeCycle ========================================
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentIptvMessageBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(isAdded)
        {
            attachFragments()
        }
    }
    //==============================================================================================
    //============================================ Method ==========================================
    fun attachFragments()
    {
        this.inboxFragment = InboxFragment()
        this.messageDetailFragment = MessageDetailFragment()
        childFragmentManager.beginTransaction().add(R.id.fc_category,this.inboxFragment!!,"Inbox").commit()
        childFragmentManager.beginTransaction().add(R.id.fc_object,this.messageDetailFragment!!,"Inbox").commit()
    }
    //==============================================================================================

}