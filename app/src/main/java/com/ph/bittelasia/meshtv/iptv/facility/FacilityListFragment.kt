package com.ph.bittelasia.meshtv.iptv.facility

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ph.bittelasia.meshtv.databinding.FragmentIptvListBinding
import com.ph.bittelasia.meshtvlibrary.database.entity.iptv.MeshFacility

class FacilityListFragment:Fragment()
{
    //========================================== Variable ==========================================
    //-------------------------------------- Binding -----------------------------------------------
    private var _binding: FragmentIptvListBinding? = null
    private val binding get() = _binding!!
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
        binding.rvItems!!.layoutManager = LinearLayoutManager(requireActivity())
    }
    //==============================================================================================
    //========================================= Method =============================================
    fun update(items:List<MeshFacility>)
    {
        if(isAdded)
        {
            var isHasItems:Boolean = items.size>0
            this.binding.rvItems!!.visibility = when(isHasItems)
            {
                true ->{
                    this.binding.rvItems!!.adapter = FacilityAdapter(requireActivity(),items)
                    View.VISIBLE
                }
                else -> View.GONE
            }
            this.binding!!.tvLabel!!.visibility = when(isHasItems)
            {
                true -> View.GONE
                else -> View.VISIBLE
            }
        }
    }
    //==============================================================================================
}