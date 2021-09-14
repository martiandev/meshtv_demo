package com.ph.bittelasia.meshtv.iptv.channel

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ph.bittelasia.meshtv.R
import com.ph.bittelasia.meshtvlibrary.database.entity.iptv.MeshChannel
import com.ph.bittelasia.meshtvlibrary.database.entity.iptv.MeshChannelCategory
import com.ph.bittelasia.meshtvlibrary.viewmodel.iptv.MeshChannelCategoryViewModel
import com.ph.bittelasia.meshtvlibrary.viewmodel.iptv.MeshChannelViewModel


class TVFragment:Fragment(){
    //=========================================== Variable =========================================
    //------------------------------------------- Constant -----------------------------------------
    companion object
    {
        val APP_ID:Int = 5
    }
    //----------------------------------------------------------------------------------------------
    //------------------------------------------- Instance -----------------------------------------
    var categoryViewModel:MeshChannelCategoryViewModel? = null
    var objectViewModel:MeshChannelViewModel? = null
    var categoryObserver:Observer<List<MeshChannelCategory>> ? = null
    var observer:Observer<List<MeshChannel>> ? = null
    //----------------------------------------------------------------------------------------------
    //------------------------------------------- Fragment -----------------------------------------
    var categoryFragment:ChannelCategoryFragment ? = null
    var channelFragment:ChannelListFragment ? = null
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //=========================================== LifeCycle ========================================
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(container!!.context).inflate(R.layout.fragment_iptv_channel,container,false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(requireActivity()!=null)
        {
            if(isAdded)
            {
                this.categoryViewModel = MeshChannelCategoryViewModel.getViewModel(requireActivity())
                this.objectViewModel = MeshChannelViewModel.getViewModel(requireActivity())
                this.categoryObserver = Observer {
                    categoryFragment!!.updateCategories(it)
                }
                this.observer = Observer {

                        this.channelFragment!!.update(it)

                }
                categoryViewModel!!.results.observe(requireActivity(), this.categoryObserver!!)
                objectViewModel!!.catResult.observe(requireActivity(), this.observer!!)
                attachFragments()
                loadCategory()
            }

        }
    }

    override fun onDetach() {
        super.onDetach()
        categoryViewModel!!.results.removeObserver(categoryObserver!!)
        objectViewModel!!.results.removeObserver(observer!!)
    }
    //==============================================================================================
    //============================================ Method ==========================================
    fun loadCategory()
    {
        this.categoryViewModel!!.get()
    }
    fun load()
    {
        this.objectViewModel!!.get()
    }
    fun attachFragments()
    {
        this.categoryFragment = ChannelCategoryFragment(this.objectViewModel!!)
        this.channelFragment = ChannelListFragment()
        childFragmentManager.beginTransaction().add(R.id.fc_category,this.categoryFragment!!,"Category").commit()
        childFragmentManager.beginTransaction().add(R.id.fc_object,this.channelFragment!!,"Channels").commit()
    }
    //==============================================================================================

}