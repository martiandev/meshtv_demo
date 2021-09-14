package com.ph.bittelasia.meshtv.iptv.facility

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ph.bittelasia.meshtv.R
import com.ph.bittelasia.meshtv.iptv.channel.ChannelCategoryFragment
import com.ph.bittelasia.meshtv.iptv.channel.ChannelListFragment
import com.ph.bittelasia.meshtvlibrary.database.entity.iptv.MeshFacility
import com.ph.bittelasia.meshtvlibrary.database.entity.iptv.MeshFacilityCategory
import com.ph.bittelasia.meshtvlibrary.viewmodel.iptv.MeshFacilityCategoryViewModel
import com.ph.bittelasia.meshtvlibrary.viewmodel.iptv.MeshFacilityViewModel

class FacilityFragment: Fragment(){
    //=========================================== Variable =========================================
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
        return LayoutInflater.from(container!!.context).inflate(R.layout.fragment_iptv_facility,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(requireActivity()!=null)
        {
            if(isAdded)
            {
                this.facilityCategoryVM = MeshFacilityCategoryViewModel.getViewModel(requireActivity())
                this.facilityVM = MeshFacilityViewModel.getViewModel(requireActivity())
                this.categoryObserver = Observer {
                    categoryFragment!!.updateCategories(it)

                }
                this.observer = Observer {
                    this.facilityFragment!!.update(it)
                }
                facilityCategoryVM!!.results.observe(requireActivity(), this.categoryObserver!!)
                facilityVM!!.catResult.observe(requireActivity(), this.observer!!)
                attachFragments()
                loadCategory()
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
    fun loadCategory()
    {
        this.facilityCategoryVM!!.get()
    }
    fun load()
    {
        this.facilityVM!!.get()
    }
    fun attachFragments()
    {
        this.categoryFragment = FacilityCategoryFragment(this.facilityVM!!)
        this.facilityFragment = FacilityListFragment()
        childFragmentManager.beginTransaction().add(R.id.fc_category,this.categoryFragment!!,"Category").commit()
        childFragmentManager.beginTransaction().add(R.id.fc_object,this.facilityFragment!!,"Facility").commit()
    }
    //==============================================================================================

}