package com.github.tomeees.scrollpickerdemo

import android.content.Context
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.tomeees.scrollpicker.ScrollPicker
import com.github.tomeees.scrollpickerdemo.databinding.ViewPagerPage1Binding
import java.util.*

class ScrollPickerDemoAdapter( val scrollPicker: ScrollPicker, val mainFragmentViewModel: MainFragmentViewModel) : PagerAdapter() {

    val LAYOUT_PAGE_1 = R.layout.view_pager_page_1
    val LAYOUT_PAGE_2 = R.layout.view_pager_page_1

    val COLOR_RANDOM_INT_BOUND: Int = 256
    val COLOR_MAX_ALPHA: Int = 255

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p1 === p0
    }

    override fun getCount(): Int {
        return 1
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = DataBindingUtil.inflate<ViewPagerPage1Binding>(container.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                if( position == 1 ) LAYOUT_PAGE_1 else LAYOUT_PAGE_2,
                container,
                false).apply {
            setViewModel( mainFragmentViewModel )
            selectorColor.setOnClickListener { _ -> scrollPicker.setSelectorColor(getRandomColor()) }
            textColor.setOnClickListener { _ -> scrollPicker.setTextColor(getRandomColor()) }
            setTextSize.setOnClickListener { _ -> scrollPicker.setTextSize(20f) }
        }
        container.addView( binding.root )
        return binding.root
    }

    fun getRandomColor(): Int {
        val rnd = Random()
        val color = Color.argb(COLOR_MAX_ALPHA, rnd.nextInt(COLOR_RANDOM_INT_BOUND), rnd.nextInt(COLOR_RANDOM_INT_BOUND), rnd.nextInt(COLOR_RANDOM_INT_BOUND))
        return color
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View)
    }
}
