package com.ph.bittelasia.meshtv.iptv.facility

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ph.bittelasia.meshtv.R
import com.ph.bittelasia.meshtvlibrary.database.entity.iptv.MeshFacilityCategory
import com.ph.bittelasia.meshtvlibrary.viewmodel.iptv.MeshFacilityViewModel

class FacilityCategoryFragment(): Fragment() {
    //======================================== Variable ============================================
    //------------------------------------------ View ----------------------------------------------
    var rv_category: RecyclerView? = null

    //----------------------------------------------------------------------------------------------
    //--------------------------------------- Categories -------------------------------------------
    var categories: List<MeshFacilityCategory>? = null
    //----------------------------------------------------------------------------------------------
    //---------------------------------------- Instance --------------------------------------------
    var vm: MeshFacilityViewModel? = null

    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //====================================== Constructor ===========================================
    constructor(vm: MeshFacilityViewModel) : this() {
        this.vm = vm
    }
    //==============================================================================================
    //======================================= LifeCycle ============================================
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_category,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.rv_category = view.findViewById(R.id.rv_category)
        this.rv_category!!.layoutManager = LinearLayoutManager(requireActivity())
    }
    //==============================================================================================
    //======================================== Categories ==========================================
    fun updateCategories(categories:List<MeshFacilityCategory>)
    {
        this.categories = categories
        this.rv_category!!.adapter = FacilityCategoryAdapter(this.vm!!,this.categories!!)
    }
    //==============================================================================================

}