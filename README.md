# ScrollPickerDemo
Demo project to showcase and test ScrollPicker, a customizable and data-bindable NumberPicker-like UI element for android that can work with an arbitrary Collection of items. See the [Ease of use](#Ease) section for more info.  
If you'd like to clone only the ScrollPicker and not all this demo project to change it for your needs, [here](https://github.com/tomeeeS/ScrollPicker) is the droid you're looking for.
  
<p >
  <img src="https://github.com/tomeeeS/ScrollPickerDemo/blob/master/raw/demo big.gif" width="240" />
  <img src="https://github.com/tomeeeS/ScrollPickerDemo/blob/master/raw/possibility showcase.png" width="500" /> 
</p>

## Functionality
### Extras not available in NumberPicker
#### Data binding! - get automatic syncronization with your ViewModel data
* Value is two-way data-bindable.
* isEnabled is data-bindable.
* The Collection of items is data-bindable, but you can only set it from code, not from xml due to lack of possible xml attribute types.  
In your viewmodel you could have
```kotlin
    var shownList: ObservableField< Collection< Any > > = ObservableField()
    val numbers: ArrayList< Int > // here's your data from the model
    shownList.set(numbers)
```
then in your view layer (fragment, activity, etc):
```kotlin
    scrollPicker.setItems(viewModel.shownList)
```

#### ScrollPicker is customizable
You can set  
* How many items should it display at a time, it's not always only 3 like in the case of NumberPicker.
* Text size and color for the items, with a possibility to set different values of these for the selected item.
* Selector style: classic 2 lines, rectangular or filled rectangle.
* Selector line width. If you set a 0 width, the selector won't be displayed.
* Bold text.

#### <a name="Ease"/>Ease of use
* You can set an arbitrary Collection with setItems(..). In case of Ints the value you'll get back will be the selected int item itself, in the other cases it will be the item's index and the items will be displayed with toString().  
So for example all of these work when you give them to setItems(..):  
```kotlin
    val numbers: List< Int > = listOf(-5, 3, -15, 7, 4, -50, 2, 70)
    val dates: ArrayList< Date > = arrayListOf(Date(), Date(), Date())
    val colors: Set< String > = setOf("red", "green", "blue")
```
Remember, you can data-bind on these collections too if you wrap them into ObservableField thanks to
```java
    public void setItems( final ObservableField< ? extends Collection > items )
```
* A Collection of integers can have arbitrary values and it's not limited to only a range of positives as with NumberPicker. No more conversion to strings and giving up of easy handling of int values is necessary.
* No setMinValue and setMaxValue call is necessary. Just set your Collection.

### Stuff that NumberPicker can do but ScrollPicker can't
* You can't have the items displayed in a loop (like as with wrapSelectorWheel in NumberPicker).
* The user can't edit the items from the UI.

## Download
In module build.gradle:
```
repositories {
    mavenCentral()
    ...
}
...

dependencies {
    implementation "com.github.tomeees:scrollpicker:1.5.0"
    ...
}
```
    
## Dependencies

### [ScrollPicker](https://github.com/tomeeeS/ScrollPicker) (scroll_picker library module, which will be your only dependency from here if you include it in your project)
* Purely written in Java 7.  
* Minsdk is 11.  
* Uses com.android.support:appcompat-v7:28.0.0-rc01 version of the v7 support lib. If you use another support lib version in your app you will have a problem - which can be solved either by using this exact version mentioned or with gradle dependency constraints as far as I know.

### The demo part (app module)
* Written in kotlin 
* Minsdk is 16.  

## Tests
* We set an int list to the picker, then perform scrolls on it. Between these scrolls, when it becomes stationary after it does the correction scroll to be in a perfect position selecting an item, we perform the checks on it. We check if the vertical scroll position is perfect (is divisible by the height of one item), if the value of the picker is correct and if the content description is correct (it's the selected item's displayed String 'value') for accessibility.  
Then we repeat this test after setting a string list to the picker.

## Donation
If you found it useful, please consider donating. Thank you!  

[![paypal](https://www.paypalobjects.com/en_US/i/btn/btn_donateCC_LG.gif)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=6B7WYZW78DBS2)
