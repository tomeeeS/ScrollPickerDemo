package com.github.tomeees.scrollpickerdemo

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableInt

class MainFragmentViewModel : ViewModel() {

    val DEFAULT_STRING_PICKER_VALUE: Int = 1

    val numbers: ArrayList< Int > = arrayListOf(-5, 3, -15, 7, 4, -50, 2, 70)
    val colors: ArrayList< String > = arrayListOf("red", "green", "blue", "yellow", "purple", "white", "llama color",
            "teal", "black", "pink", "orange", "gold", "aquamarine", "brown", "gray")
    var pickerValue: ObservableInt = ObservableInt()
    var isEnabled: ObservableBoolean = ObservableBoolean(true)
    var shownList: ObservableField< Collection< Any > > = ObservableField()

    private var showNumbers = true

    init {
        initList()
    }

    fun setValueTo(valueTo: Int) {
        pickerValue.set(valueTo)
    }

    fun changeList() {
        showNumbers = !showNumbers
        initList()
    }

    fun initList() {
        if (showNumbers) {
            shownList.set(numbers)
            pickerValue.set(numbers[ 1 ])
        } else {
            shownList.set(colors)
            pickerValue.set(DEFAULT_STRING_PICKER_VALUE)
        }
    }
}