package com.ph.bittelasia.meshtv.iptv.facility

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ph.bittelasia.meshtv.databinding.FragmentIptvListBinding
import com.ph.bittelasia.meshtvlibrary.database.entity.iptv.MeshFacility
import com.ph.bittelasia.meshtvlibrary.viewmodel.iptv.MeshFacilityViewModel

class FacilityListFragment:Fragment()
{
    //========================================== Variable ==========================================
    //-------------------------------------- Binding -----------------------------------------------
    private var _binding: FragmentIptvListBinding? = null
    private val binding get() = _binding!!
    //----------------------------------------------------------------------------------------------
    //----------------------------------------- ViewModel ------------------------------------------
    var objectViewModel: MeshFacilityViewModel? = null
    var observer: Observer<List<MeshFacility>>? = null
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //========================================= Lifecycle ==========================================
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentIptvListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvItems.layoutManager = LinearLayoutManager(requireActivity())
        this.objectViewModel = MeshFacilityViewModel.getViewModel(requireActivity())
        this.observer = Observer {
            updateItems(it)
        }
        this.objectViewModel!!.catResult.observe(requireActivity(),observer!!)

    }
    //==============================================================================================
    //========================================= Method =============================================
    fun updateItems(items:List<MeshFacility>)
    {
        if(isAdded)
        {
            var isHasItems:Boolean = items.size>0
            this.binding.rvItems.visibility = when(isHasItems)
            {
                true ->{
                    this.binding.rvItems.adapter = FacilityAdapter(requireActivity(),items)
                    View.VISIBLE
                }
                else -> View.GONE
            }
            this.binding.tvLabel.visibility = when(isHasItems)
            {
                true -> View.GONE
                else -> View.VISIBLE
            }
        }
    }
    //==============================================================================================
}