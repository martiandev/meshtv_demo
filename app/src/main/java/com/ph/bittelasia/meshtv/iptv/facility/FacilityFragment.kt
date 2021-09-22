package com.ph.bittelasia.meshtv.iptv.facility

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ph.bittelasia.meshtv.R
import com.ph.bittelasia.meshtv.databinding.FragmentIptvChannelBinding
import com.ph.bittelasia.meshtv.databinding.FragmentIptvFacilityBinding
import com.ph.bittelasia.meshtv.iptv.channel.ChannelCategoryFragment
import com.ph.bittelasia.meshtv.iptv.channel.ChannelListFragment
import com.ph.bittelasia.meshtvlibrary.database.entity.iptv.MeshFacility
import com.ph.bittelasia.meshtvlibrary.database.entity.iptv.MeshFacilityCategory
import com.ph.bittelasia.meshtvlibrary.viewmodel.iptv.MeshFacilityCategoryViewModel
import com.ph.bittelasia.meshtvlibrary.viewmodel.iptv.MeshFacilityViewModel

class FacilityFragment: Fragment(){
    //=========================================== Variable =========================================
    //-------------------------------------- Binding -----------------------------------------------
    private var _binding: FragmentIptvFacilityBinding? = null
    private val binding get() = _binding!!
    //----------------------------------------------------------------------------------------------
    //------------------------------------------- Constant -----------------------------------------
    companion object
    {
        val APP_ID:Int = 15
    }
    //----------------------------------------------------------------------------------------------
    //------------------------------------------- Fragment -----------------------------------------
    var categoryFragment:FacilityCategoryFragment? = null
    var facilityFragment:FacilityListFragment? = null
    //----------------------------------------------------------------------------------------------
    //------------------------------------------- Instance -----------------------------------------
    var facilityCategoryVM:MeshFacilityCategoryViewModel ? = null
    var facilityVM: MeshFacilityViewModel? = null
    var categoryObserver:Observer<List<MeshFacilityCategory>> ? = null
    var observer:Observer<List<MeshFacility>> ? = null
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //=========================================== LifeCycle ========================================
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentIptvFacilityBinding.inflate(inflater, container, false)
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
    override fun onDetach() {
        super.onDetach()
        facilityCategoryVM!!.results.removeObserver(categoryObserver!!)
        facilityVM!!.results.removeObserver(observer!!)
    }
    //==============================================================================================
    //============================================ Method ==========================================

    fun attachFragments()
    {
        this.categoryFragment = FacilityCategoryFragment()
        this.facilityFragment = FacilityListFragment()
        childFragmentManager.beginTransaction().add(R.id.fc_category,this.categoryFragment!!,"Category").commit()
        childFragmentManager.beginTransaction().add(R.id.fc_object,this.facilityFragment!!,"Facilities").commit()
    }
    //==============================================================================================

}