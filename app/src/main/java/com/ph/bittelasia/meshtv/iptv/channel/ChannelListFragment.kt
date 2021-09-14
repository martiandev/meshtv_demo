package com.ph.bittelasia.meshtv.iptv.channel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ph.bittelasia.meshtv.R
import com.ph.bittelasia.meshtvlibrary.database.entity.iptv.MeshChannel

class ChannelListFragment:Fragment()
{
    //========================================== Variable ==========================================
    //-------------------------------------------- List --------------------------------------------
    var items:List<MeshChannel> ? = null
    //----------------------------------------------------------------------------------------------
    //--------------------------------------------- View -------------------------------------------
    var rv_items:RecyclerView ? = null
    var tv_label: TextView? = null
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //========================================= Lifecycle ==========================================
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_iptv_list,container,false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.rv_items = view.findViewById(R.id.rv_items)
        this.rv_items!!.layoutManager = LinearLayoutManager(requireActivity())
        this.tv_label = view.findViewById(R.id.tv_label)
    }
    //==============================================================================================
    //========================================= Method =============================================
    fun update(items:List<MeshChannel>)
    {
        if(isAdded)
        {
            var isHasItems:Boolean = items.size>0
            this.items = items
            rv_items!!.visibility = when(isHasItems)
            {
                true ->{
                    rv_items!!.adapter = ChannelAdapter(requireActivity(),this.items!!)
                    View.VISIBLE
                }
                else -> View.GONE
            }
            tv_label!!.visibility = when(isHasItems)
            {
                true -> View.GONE
                else -> View.VISIBLE
            }
        }


    }
    //==============================================================================================

}