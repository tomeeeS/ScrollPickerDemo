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

    val COLOR_RANDOM_INT_BOUND: Int = 256
    val COLOR_MAX_ALPHA: Int = 255

    lateinit var scrollPicker: ScrollPicker

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        val viewModel = ViewModelProviders.of( this ).get( MainFragmentViewModel::class.java )

        val binding = DataBindingUtil.inflate<FragmentMainBinding>( inflater, LAYOUT, container, false).apply {
            setViewModel( viewModel )
            setLifecycleOwner(this@MainFragment)

            scrollPicker = picker.apply {
                setShownItemCount( viewModel.SHOWN_ITEM_COUNT )
                setEnabled( true )
                setList( viewModel.shownList )
            }
            selectorColor.setOnClickListener { _ -> scrollPicker.setSelectorColor( getRandomColor() ) }
            textColor.setOnClickListener { _ -> scrollPicker.setTextColor( getRandomColor() ) }
            setTextSize.setOnClickListener { _ -> scrollPicker.setTextSize( 22f ) }
        }

        return binding.root
    }

    fun getRandomColor(): Int {
        val rnd = Random()
        val color = Color.argb( COLOR_MAX_ALPHA, rnd.nextInt( COLOR_RANDOM_INT_BOUND ), rnd.nextInt( COLOR_RANDOM_INT_BOUND ), rnd.nextInt( COLOR_RANDOM_INT_BOUND ) )
        return color
    }
}
