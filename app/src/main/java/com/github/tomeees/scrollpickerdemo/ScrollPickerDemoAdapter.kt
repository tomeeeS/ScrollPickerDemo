package com.github.tomeees.scrollpickerdemo

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.github.tomeees.scrollpicker.ScrollPicker
import com.github.tomeees.scrollpicker.SelectorStyle
import com.github.tomeees.scrollpickerdemo.databinding.ViewPagerPage1Binding
import com.github.tomeees.scrollpickerdemo.databinding.ViewPagerPage2Binding
import java.util.*

class ScrollPickerDemoAdapter(private val context: Context
                              , private val scrollPicker: ScrollPicker
                              , private val mainFragmentViewModel: MainFragmentViewModel)
    : androidx.viewpager.widget.PagerAdapter() {

    private val layoutPage1 = R.layout.view_pager_page_1
    private val layoutPage2 = R.layout.view_pager_page_2

    private val colorRandomIntBound: Int = 256
    private val colorMaxAlpha: Int = 255

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p1 === p0
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return (position + 1).toString()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding: ViewDataBinding = when( position ) {
            0    -> setPage1Binding(container)
            else -> setPage2Binding(container)
        }
        container.addView( binding.root )
        return binding.root
    }

    private fun setPage2Binding(container: ViewGroup): ViewDataBinding {
        return DataBindingUtil.inflate<ViewPagerPage2Binding>(  LayoutInflater.from(context),
                                                                layoutPage2,
                                                                container,
                                                                false).apply {
            viewModel = mainFragmentViewModel
            selectorStyle1.setOnClickListener { scrollPicker.setSelectorStyle( SelectorStyle.RECTANGLE_FILLED ) }
            selectorStyle2.setOnClickListener { scrollPicker.setSelectorStyle( SelectorStyle.RECTANGLE ) }
            selectorStyle3.setOnClickListener { scrollPicker.setSelectorStyle( SelectorStyle.CLASSIC ) }

            shownItemCount1.setOnClickListener { scrollPicker.setShownItemCount( 3 ) }
            shownItemCount2.setOnClickListener { scrollPicker.setShownItemCount( 5 ) }
            shownItemCount3.setOnClickListener { scrollPicker.setShownItemCount( 6 ) }
            shownItemCount4.setOnClickListener { scrollPicker.setShownItemCount( 7 ) }

            selectedItemTextColor.setOnClickListener { scrollPicker.setSelectedTextColor(getRandomColor()) }
            setSelectedTextSize.setOnClickListener { scrollPicker.setSelectedTextSize(26f) }
        }
    }

    private fun setPage1Binding(container: ViewGroup): ViewDataBinding {
        return DataBindingUtil.inflate<ViewPagerPage1Binding>(  LayoutInflater.from(context),
                                                                layoutPage1,
                                                                container,
                                                                false).apply {
            viewModel = mainFragmentViewModel
            selectorColor.setOnClickListener { scrollPicker.setSelectorColor(getRandomColor()) }
            textColor.setOnClickListener { scrollPicker.setTextColor(getRandomColor()) }
            setTextSize.setOnClickListener { scrollPicker.textSize = 20f }
        }
    }

    private fun getRandomColor(): Int {
        val rnd = Random()
        fun randomColorChannelValue() = rnd.nextInt(colorRandomIntBound)
        return Color.argb(colorMaxAlpha, randomColorChannelValue(), randomColorChannelValue(), randomColorChannelValue())
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View)
    }
}
