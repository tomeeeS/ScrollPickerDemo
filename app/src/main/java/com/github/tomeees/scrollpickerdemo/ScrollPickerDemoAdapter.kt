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

class ScrollPickerDemoAdapter( val context: Context, val scrollPicker: ScrollPicker, val mainFragmentViewModel: MainFragmentViewModel)
    : androidx.viewpager.widget.PagerAdapter() {

    val LAYOUT_PAGE_1 = R.layout.view_pager_page_1
    val LAYOUT_PAGE_2 = R.layout.view_pager_page_2

    val COLOR_RANDOM_INT_BOUND: Int = 256
    val COLOR_MAX_ALPHA: Int = 255

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

    fun setPage2Binding( container: ViewGroup): ViewDataBinding {
        return DataBindingUtil.inflate<ViewPagerPage2Binding>(LayoutInflater.from(context),
                LAYOUT_PAGE_2,
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
            setSelectedTextSize.setOnClickListener { scrollPicker.setSelectedTextSize(24f) }
        }
    }

    fun setPage1Binding(container: ViewGroup): ViewDataBinding {
        return DataBindingUtil.inflate<ViewPagerPage1Binding>(LayoutInflater.from(context),
                LAYOUT_PAGE_1,
                container,
                false).apply {
            viewModel = mainFragmentViewModel
            selectorColor.setOnClickListener { scrollPicker.setSelectorColor(getRandomColor()) }
            textColor.setOnClickListener { scrollPicker.setTextColor(getRandomColor()) }
            setTextSize.setOnClickListener { scrollPicker.textSize = context.resources.getDimension(R.dimen.bigger_text_size) }
        }
    }

    fun getRandomColor(): Int {
        val rnd = Random()
        fun randomColorChannelValue() = rnd.nextInt(COLOR_RANDOM_INT_BOUND)
        return Color.argb(COLOR_MAX_ALPHA, randomColorChannelValue(), randomColorChannelValue(), randomColorChannelValue())
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View)
    }
}
