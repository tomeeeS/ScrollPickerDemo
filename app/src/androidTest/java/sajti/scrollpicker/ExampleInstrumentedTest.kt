package sajti.scrollpicker

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
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
    fun scrollPickerPositionIsCorrect_onScroll() {
//        scrollPicker.perform( ViewActions.swipeDown() )check( matches(  ) )
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
