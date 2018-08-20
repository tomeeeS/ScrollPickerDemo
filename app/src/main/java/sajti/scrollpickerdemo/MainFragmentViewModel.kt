package sajti.scrollpickerdemo

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableInt

class MainFragmentViewModel : ViewModel() {

    val SHOWN_ITEM_COUNT: Int = 13
    val DEFAULT_STRING_PICKER_VALUE: Int = 1

    val numbers: ArrayList< Any > = arrayListOf(-5, 3, -15, 7, 4, -50, 2, 70)
    val colors: ArrayList< Any > = arrayListOf("red", "green", "blue", "yellow", "purple", "white", "llama color",
            "teal", "black", "pink", "orange", "gold", "aquamarine", "brown", "gray")
    var pickerValue: ObservableInt = ObservableInt()
    var isEnabled: ObservableBoolean = ObservableBoolean(true)
    var shownList: ObservableField< ArrayList< Any > > = ObservableField()

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
            pickerValue.set(numbers[ 2 ] as Int)
        } else {
            shownList.set(colors)
            pickerValue.set(DEFAULT_STRING_PICKER_VALUE)
        }
    }
}