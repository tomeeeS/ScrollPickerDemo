package sajti.srollpickerdemo

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableInt

class MainFragmentViewModel : ViewModel() {
    var pickerValue: ObservableInt = ObservableInt( 5 )
    var isEnabled: ObservableBoolean = ObservableBoolean( true )

    fun setValueTo( valueTo: Int ) {
        pickerValue.set( valueTo )
    }

}