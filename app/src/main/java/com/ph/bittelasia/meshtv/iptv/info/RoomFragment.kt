package com.ph.bittelasia.meshtv.iptv.info

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ph.bittelasia.meshtv.databinding.FragmentRoomBinding
import com.ph.bittelasia.meshtvlibrary.preference.`object`.iptv.MeshSTB
import com.ph.bittelasia.meshtvlibrary.preference.manager.MeshSTBPreference

class RoomFragment: Fragment()
{
    //=========================================== Variable =========================================
    //------------------------------------------ Binding -------------------------------------------
    private var _binding: FragmentRoomBinding? = null
    private val binding get() = _binding!!
    //----------------------------------------------------------------------------------------------
    //-------------------------------------------- Instance ----------------------------------------
    var stb:MeshSTB ? = null
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //=========================================== Lifecycle ========================================
    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.stb = MeshSTB(context)
        MeshSTBPreference.get()!!.load(this.stb!!)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentRoomBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)
        binding.tvRoom!!.text = this.stb!!.room
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    //==============================================================================================
}