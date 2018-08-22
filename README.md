# ScrollPickerDemo
Demo project to showcase and test my android [ScrollPicker](https://github.com/tomeeeS/ScrollPicker) custom view, which is a customizable and data-bindable NumberPicker-like UI element for android. 

## Functionality
### Similarities with NumberPicker
* You can set a list of strings or integers for it to display.
* You can set which item to select from the list. The default is the first one. Then you can scroll it  
This is property of which item is selected is called the 'value' of the scroll picker - just as with NumberPickers. If the list we set was such that its items are of String, then the value corresponds to the index of the selected item in the list, while in case of integers it is the item's int value.  

### Extras not available in NumberPicker
* Value is two-way data-bindable!

## Tests
* Setting an int list to the picker, then do successive scrolls on it many times, and do checks on it when it becomes stationary after it does the correction scroll to be in a perfect position selecting an item. We check if the vertical scroll position is perfect (is divisible by the height of one item) and if the value of the picker is correct.  
Then we repeat this test after setting a string list to the picker.
* Check set text size correctness.

## Dependencies

### The demo part (app module)
* written in kotlin 
* Minsdk is 16.  

### [ScrollPicker](https://github.com/tomeeeS/ScrollPicker) (scroll_picker library module, which will be your only dependency from here if you include it in your project)
* You don't need Java 8, nor kotlin compiler for this as it was written in Java 7.  
* Minsdk is 11.  
* Uses com.android.support:appcompat-v7:28.0.0-alpha1 version of the v7 support lib. If you use another support lib version in your app you might have a problem - which can be solved by either using this exact version mentioned OR with gradle dependency constraints as far as I know.

## Donation
If you found it useful, please consider donating. Thank you!  

[![paypal](https://www.paypalobjects.com/en_US/i/btn/btn_donateCC_LG.gif)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=6B7WYZW78DBS2)
