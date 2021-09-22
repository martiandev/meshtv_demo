package com.ph.bittelasia.meshtv.iptv.channel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ph.bittelasia.meshtv.R
import com.ph.bittelasia.meshtv.databinding.FragmentIptvChannelBinding

class TVFragment:Fragment(){
    //=========================================== Variable =========================================
    //-------------------------------------- Binding -----------------------------------------------
    private var _binding: FragmentIptvChannelBinding? = null
    private val binding get() = _binding!!
    //----------------------------------------------------------------------------------------------
    //------------------------------------------- Constant -----------------------------------------
    companion object { val APP_ID:Int = 5 }
    //----------------------------------------------------------------------------------------------
    //------------------------------------------- Fragment -----------------------------------------
    var categoryFragment:ChannelCategoryFragment ? = null
    var channelFragment:ChannelListFragment ? = null
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //=========================================== LifeCycle ========================================
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentIptvChannelBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(requireActivity()!=null)
        {
            if(isAdded)
            {
                attachFragments()
            }
        }
    }
    //==============================================================================================
    //============================================ Method ==========================================
    fun attachFragments()
    {
        this.categoryFragment = ChannelCategoryFragment()
        this.channelFragment = ChannelListFragment()
        childFragmentManager.beginTransaction().add(R.id.fc_category,this.categoryFragment!!,"Category").commit()
        childFragmentManager.beginTransaction().add(R.id.fc_object,this.channelFragment!!,"Channels").commit()
    }
    //==============================================================================================

}