package sajti.srollpickerdemo

import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ObservableBoolean
import android.databinding.ObservableInt
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import sajti.scroll_picker.ScrollPicker
import sajti.srollpickerdemo.databinding.ActivityMainBinding

class IntListActivity : AppCompatActivity()  {

    lateinit var scrollPicker: ScrollPicker

    private val LAYOUT = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pickerValue = ObservableInt( 5 )
        val isEnabled = ObservableBoolean( true )

        val binding: ActivityMainBinding = DataBindingUtil.setContentView( this, LAYOUT )
        binding.mainContent!!.setPickerValue( pickerValue )
        binding.mainContent.setIsEnabled( isEnabled )

        scrollPicker = findViewById( R.id.picker )
        scrollPicker.setShownItemCount( 4 )
        val intList = IntRange( 2, 20 )
        scrollPicker.setList( intList.toCollection( ArrayList() ) )

        fab.setOnClickListener { _ ->
            run {
                pickerValue.set( 2 )
            }
        }
        fab2.setOnClickListener { _ ->
            run {
                val setEnabled = !isEnabled.get()
                isEnabled.set( setEnabled )
            }
        }
    }

    //region optionsMenu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate( R.menu.menu_main, menu )
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when(item.itemId) {
            R.id.action_settings ->
                startActivity( Intent( this, IntListActivity::class.java ) )
            else -> super.onOptionsItemSelected( item )
        }
        return true
    }
    //endregion optionsMenu
}
