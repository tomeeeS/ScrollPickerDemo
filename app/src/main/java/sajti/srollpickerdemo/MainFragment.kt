package sajti.srollpickerdemo

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import sajti.scroll_picker.ScrollPicker
import sajti.srollpickerdemo.databinding.FragmentMainBinding
import java.util.*


class MainFragment : Fragment() {

    private val LAYOUT = R.layout.fragment_main

    lateinit var scrollPicker: ScrollPicker

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        val viewModel = ViewModelProviders.of( this ).get( MainFragmentViewModel::class.java )

        val binding = DataBindingUtil.inflate<FragmentMainBinding>( inflater, LAYOUT, container, false).apply {
            setViewModel( viewModel )
            setLifecycleOwner(this@MainFragment)

            scrollPicker = picker.apply {
                setShownItemCount( 5 )
                val colors = getResources().getStringArray( R.array.colors )
                setList( colors.toCollection( ArrayList() ) )
                setEnabled( true )
            }
            selectorColor.setOnClickListener { _ -> scrollPicker.setSelectorColor( getRandomColor() ) }
            textColor.setOnClickListener { _ -> scrollPicker.setTextColor( getRandomColor() ) }
        }

        return binding.root
    }

    fun getRandomColor(): Int {
        val rnd = Random()
        val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        return color
    }
}
