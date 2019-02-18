package com.github.tomeees.scrollpickerdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.github.tomeees.scrollpickerdemo.databinding.FragmentMainBinding

class MainFragment : androidx.fragment.app.Fragment() {

    private val layout = R.layout.fragment_main

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewModel = ViewModelProviders.of(this).get(MainFragmentViewModel::class.java)

        val binding = DataBindingUtil.inflate<FragmentMainBinding>(inflater, layout, container, false).apply {
            setViewModel(viewModel)
            lifecycleOwner = this@MainFragment

            picker.setItems(viewModel.shownItems)
//            picker.setItemsIntRange( 0, 2 )
            pager.adapter = ScrollPickerDemoAdapter(this@MainFragment.context!!, picker, viewModel )
        }

        return binding.root
    }

}
