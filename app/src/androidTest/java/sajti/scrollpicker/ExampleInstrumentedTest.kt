package sajti.scrollpicker

import android.os.SystemClock
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import sajti.scrollpickerdemo.MainActivity
import sajti.scrollpickerdemo.R

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    lateinit var scrollPicker: ViewInteraction

    @get:Rule
    val activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Before
    fun initFields() {
        scrollPicker = onView(withId(R.id.picker))
    }

    @Test
    fun scrollPickersItemTextSizeIsCorrect_onButtonPush() {
        onView(withId(R.id.setTextSize)).perform(click())
        scrollPicker.check(matches(withScrollPickerFontSize(22f)))
    }

    @Test
    fun scrollPickerPositionIsCorrect_onScroll() {
        onView(withId(R.id.setList)).perform(click())
        for( i in 1..100 ) {
            scrollPicker.perform( if( i % 2 == 0 ) ViewActions.swipeUp() else ViewActions.swipeDown() )
            SystemClock.sleep(1000) // todo: wait until it's finished, not until a fixed amount of time has passed
            scrollPicker.check(matches(withScrollPickerScrollYValid()))
        }
    }

    private fun withScrollPickerScrollYValid(): Matcher<View> {
        return object : BoundedMatcher<View, View>(View::class.java) {

            var scrollY = 0
            var cellHeight = 0
            var scrollYMod = 0

            public override fun matchesSafely(target: View): Boolean {
                if (target !is ScrollPicker) {
                    return false
                }
                scrollY = target.getListScrollY()
                cellHeight = target.cellHeight
                scrollYMod = scrollY % cellHeight
                val scrollYError = scrollYMod % cellHeight
                return scrollYError == 0
            }

            override fun describeTo(description: Description) {
                if( cellHeight != 0 )
                    description.appendText("scrollY: %d, cellHeight: %d, mod: %d".format(scrollY, cellHeight, scrollY % cellHeight))
            }
        }
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
