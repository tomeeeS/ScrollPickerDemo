package com.github.tomeees.scrollpicker

import android.os.SystemClock
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.espresso.matcher.ViewMatchers.withContentDescription
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import com.github.tomeees.scrollpickerdemo.MainActivity
import com.github.tomeees.scrollpickerdemo.MainFragmentViewModel
import com.github.tomeees.scrollpickerdemo.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ScrollPickerInstrumentedTest {

    lateinit var scrollPicker: ViewInteraction

    @get:Rule
    val activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Before
    fun initFields() {
        scrollPicker = onView(withId(R.id.picker))
    }

    @Test
    fun contentDescriptionIsSet() {
        scrollPicker.check(matches(not( withContentDescription( "" ))))
    }

    // tests if scroll picker scrolls itself to an item perfectly and if it sets the value correctly on scroll.
    // we check it 50 times for int list and 50 times for string list
    @Test
    fun scrollPickerPositionAndValueAreCorrect_onScroll() {
        testScroll( true )
        onView(withId(R.id.setItems)).perform(click())
        testScroll( false )
    }

    fun testScroll(isNumeric: Boolean) {
        for(i in 1..20) {
            scrollPicker.perform(if(i % 2 == 0) ViewActions.swipeUp() else ViewActions.swipeDown()) // we do swipe downs and swipe ups in succession
            SystemClock.sleep(1000) // wait until animations are surely finished
            scrollPicker.check(matches(withScrollPicker_ScrollYAndValueAndContentDescription_AreCorrect(isNumeric)))
        }
    }

    private fun withScrollPicker_ScrollYAndValueAndContentDescription_AreCorrect(isNumeric: Boolean): Matcher<View> {
        return object : BoundedMatcher<View, View>(View::class.java) {

            var scrollY = 0
            var cellHeight = 0
            var scrollYError = 0
            var previousCellsCount = 0
            var pickerValue = 0
            val numbers = MainFragmentViewModel().numbers

            public override fun matchesSafely(target: View): Boolean {
                if (target !is ScrollPicker) {
                    return false
                }
                scrollY = target.getListScrollY()
                cellHeight = target.cellHeight
                previousCellsCount = scrollY / cellHeight
                pickerValue = target.value
                scrollYError = scrollY % cellHeight
                val isValueCorrect = if( isNumeric ) pickerValue == numbers[ previousCellsCount ] else pickerValue == previousCellsCount
                val isContentDescriptionCorrect = target.contentDescription == target.items.get(target.selectedIndex).toString()
                return scrollYError == 0 && isValueCorrect && isContentDescriptionCorrect
            }

            override fun describeTo(description: Description) {
                if( cellHeight != 0 )
                    description.appendText("scrollY: %d, cellHeight: %d, scrollYError: %d, previousCellsCount: %d, pickerValue: %d"
                            .format(scrollY, cellHeight, scrollYError, previousCellsCount, pickerValue))
            }
        }
    }

    @Test
    fun scrollPickersItemTextSizeIsCorrect_onButtonPush() {
        onView(withId(R.id.setTextSize)).perform(click())
        scrollPicker.check(matches(withScrollPickerFontSize(22f)))
    }

    fun withScrollPickerFontSize(expectedSize: Float): Matcher<View> {
        return object : BoundedMatcher<View, View>(View::class.java) {

            public override fun matchesSafely(target: View): Boolean {
                if (target !is ScrollPicker) {
                    return false
                }
                val targetScrollPicker = target
                val textSize = targetScrollPicker.getTextSize()
                return textSize.compareTo(expectedSize) == 0
            }

            override fun describeTo(description: Description) {
                description.appendText("with fontSize: ")
                description.appendValue(expectedSize)
            }
        }
    }
}
