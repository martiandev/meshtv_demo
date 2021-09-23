package com.ph.bittelasia.meshtv.iptv.message

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ph.bittelasia.meshtv.databinding.FragmentIptvInboxBinding
import com.ph.bittelasia.meshtvlibrary.api.APIListener
import com.ph.bittelasia.meshtvlibrary.database.entity.iptv.MeshMessage
import com.ph.bittelasia.meshtvlibrary.viewmodel.iptv.MeshMessageViewModel

class MessageDetailFragment:Fragment(),APIListener
{
    //========================================== Variable ==========================================
    //------------------------------------------ Binding -------------------------------------------
    private var _binding: FragmentIptvInboxBinding? = null
    private val binding get() = _binding!!
    //----------------------------------------------------------------------------------------------
    //---------------------------------------- ViewModel -------------------------------------------
    var mvm: MeshMessageViewModel? = null
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //========================================= Lifecycle ==========================================
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentIptvInboxBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(isAdded)
        {
            mvm = MeshMessageViewModel.getViewModel(requireActivity())
            mvm!!.result.observe(requireActivity(), Observer {
                update(it)
            })
        }
    }
    //==============================================================================================
    //========================================= Method =============================================
    fun update(message:MeshMessage)
    {
        binding!!.message = message
        if(message!=null)
        {

            binding!!.tvLabel.visibility    = View.GONE
            binding!!.clDetails.visibility  = View.VISIBLE
            binding!!.tvContent.text        = Html.fromHtml(message.messg_text)

            if(message.messg_status!=2)
            {
                mvm!!.read(this,message)
            }
        }
        else
        {
            binding!!.tvLabel.visibility = View.VISIBLE
            binding!!.clDetails.visibility  = View.GONE

        }

    }
    //==============================================================================================
    //========================================== APIListener =======================================
    override fun onFail(result: Any, type: Int) {

    }
    override fun onSuccess(result: Any, type: Int) {
        mvm!!.get()
    }
    //==============================================================================================

}