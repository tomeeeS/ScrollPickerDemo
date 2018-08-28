package com.github.tomeees.scrollpickerdemo

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.tomeees.scrollpickerdemo.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val LAYOUT = R.layout.fragment_main

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewModel = ViewModelProviders.of(this).get(MainFragmentViewModel::class.java)

        val binding = DataBindingUtil.inflate<FragmentMainBinding>(inflater, LAYOUT, container, false).apply {
            setViewModel(viewModel)
            setLifecycleOwner(this@MainFragment)

            picker.setItems(viewModel.shownItems)
            pager.adapter = ScrollPickerDemoAdapter(this@MainFragment.context!!, picker, viewModel )
        }

        return binding.root
    }

}
