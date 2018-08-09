package sajti.srollpickerdemo

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import sajti.scroll_picker.ScrollPicker
import sajti.srollpickerdemo.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val LAYOUT = R.layout.fragment_main

    lateinit var scrollPicker: ScrollPicker

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        val viewModel = MainFragmentViewModel

        val binding = DataBindingUtil.inflate<FragmentMainBinding>( inflater, LAYOUT, container, false).apply {
            setViewModel( viewModel )
            setLifecycleOwner(this@MainFragment)

            scrollPicker = picker.apply {
                setShownItemCount( 5 )
                val colors = getResources().getStringArray( R.array.colors )
                setList( colors.toCollection( ArrayList() ) )
                setEnabled( true )
            }
        }


//        fab.setOnClickListener { _ ->
//            run {
//                pickerValue.set( 2 )
//            }
//        }
//        fab2.setOnClickListener { _ ->
//            run {
//                val setEnabled = !isEnabled.get()
//                isEnabled.set( setEnabled )
//            }
//        }
        return binding.root
    }

}
