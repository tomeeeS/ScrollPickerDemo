package com.github.tomeees.scrollpickerdemo

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import java.util.*

class MainFragmentViewModel : ViewModel() {

    val numbers: List<Int> = listOf(-5, 3, -15, 7, 4, -50, 2, 70, 0, -114, -2, 93)
    private val dates: ArrayList<Date> = arrayListOf(Date(), Date(), Date())
    private val colors: Set<String> = setOf("red", "green", "blue", "yellow",
            "very very long color name that gets shrunk by autosizing and doesn't get cut off! take that, color! thought you could hack us?",
            "white", "llama color", "teal", "black", "pink", "orange", "gold", "aquamarine", "brown", "gray", "purple")
    var pickerValue: ObservableInt = ObservableInt()
    var isEnabled: ObservableBoolean = ObservableBoolean(true)
    var shownItems: ObservableField<Collection<Any>> = ObservableField()

    private var whichListToShow = 0

    init {
        initList()
    }

    fun setValueTo(valueTo: Int) {
        pickerValue.set(valueTo)
    }

    fun changeList() {
        whichListToShow = if (whichListToShow == 2) 0
        else whichListToShow + 1
        initList()
    }

    private fun initList() {
        shownItems.set(
            when (whichListToShow) {
                0 -> numbers
                1 -> colors
                else -> dates
        })
        pickerValue.set(2)
    }
}