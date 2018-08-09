package sajti.srollpickerdemo

import android.databinding.ObservableBoolean
import android.databinding.ObservableInt

object MainFragmentViewModel {
    val pickerValue = ObservableInt( 5 )
    val isEnabled = ObservableBoolean( true )

    fun setValueTo( valueTo: Int ) {
        pickerValue.set( valueTo )
    }
}
