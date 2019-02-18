package com.github.tomeees.scrollpickerdemo

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import java.util.*

class MainFragmentViewModel : ViewModel() {

    val numbers: List< Int > = listOf(-5, 3, -15, 7, 4, -50, 2, 70, 0, -114, -2, 93)
    val dates: ArrayList< Date > = arrayListOf( Date(), Date(), Date() )
    val colors: Set< String > = setOf("red", "green", "blue", "yellow", "purple", "white", "llama color",
            "teal", "black", "pink", "orange", "gold", "aquamarine", "brown", "gray")
    var pickerValue: ObservableInt = ObservableInt()
    var isEnabled: ObservableBoolean = ObservableBoolean(true)
    var shownItems: ObservableField< Collection< Any > > = ObservableField()

    private var whichListToShow = 0

    init {
        initList()
    }

    fun setValueTo(valueTo: Int) {
        pickerValue.set(valueTo)
    }

    fun changeList() {
        whichListToShow = if( whichListToShow == 2 ) 0 else whichListToShow + 1
        initList()
    }

    fun initList() {
        shownItems.set( when( whichListToShow ) {
            0 -> numbers
            1 -> colors
            else -> dates
        } )
        pickerValue.set( 2 )
    }
}