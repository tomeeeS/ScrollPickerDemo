package sajti.scrollpickerdemo

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import sajti.scrollpicker.ScrollPicker
import sajti.scrollpickerdemo.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val LAYOUT = R.layout.fragment_main

    lateinit var scrollPicker: ScrollPicker

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewModel = ViewModelProviders.of(this).get(MainFragmentViewModel::class.java)

        val binding = DataBindingUtil.inflate<FragmentMainBinding>(inflater, LAYOUT, container, false).apply {
            setViewModel(viewModel)
            setLifecycleOwner(this@MainFragment)

            scrollPicker = picker.apply {
                setShownItemCount(viewModel.SHOWN_ITEM_COUNT)
                setList(viewModel.shownList)
            }
            pager.adapter = ScrollPickerDemoAdapter( scrollPicker, viewModel )
        }

        return binding.root
    }

}
