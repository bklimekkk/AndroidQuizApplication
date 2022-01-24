package com.example.androidquizapplication


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class FR6bEspressoTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun fR6bEspressoTest() {
        val materialButton = onView(
            allOf(
                withId(R.id.teacherButton), withText("Teacher"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.fragmentContainerView),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

        val floatingActionButton = onView(
            allOf(
                withId(R.id.add_bank),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.fragmentContainerView),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        floatingActionButton.perform(click())

        val appCompatEditText = onView(
            allOf(
                withId(R.id.bankNameInputField),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.fragmentContainerView),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("bank"), closeSoftKeyboard())

        val materialButton2 = onView(
            allOf(
                withId(R.id.createBankButton), withText("Create bank"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.fragmentContainerView),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        materialButton2.perform(click())

        val materialButton3 = onView(
            allOf(
                withId(R.id.teacherBanksBackButton), withText("Modes menu"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.fragmentContainerView),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        materialButton3.perform(click())

        val materialButton4 = onView(
            allOf(
                withId(R.id.studentButton), withText("Student"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.fragmentContainerView),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialButton4.perform(click())

        val recyclerView = onView(
            allOf(
                withId(R.id.quizzesRecyclerView),
                childAtPosition(
                    withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                    1
                )
            )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        val materialButton5 = onView(
            allOf(
                withId(R.id.finishQuiz), withText("Exit quiz"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.fragmentContainerView),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        materialButton5.perform(click())

        val materialButton6 = onView(
            allOf(
                withId(R.id.quizzesBackButton), withText("Modes menu"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.fragmentContainerView),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialButton6.perform(click())

        val materialButton7 = onView(
            allOf(
                withId(R.id.teacherButton), withText("Teacher"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.fragmentContainerView),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialButton7.perform(click())

        val recyclerView2 = onView(
            allOf(
                withId(R.id.banksRecyclerView),
                childAtPosition(
                    withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                    0
                )
            )
        )
        recyclerView2.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        val overflowMenuButton = onView(
            allOf(
                withContentDescription("More options"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.action_bar),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        overflowMenuButton.perform(click())

        val materialTextView = onView(
            allOf(
                withId(R.id.title), withText("Delete bank"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialTextView.perform(click())

        val materialButton8 = onView(
            allOf(
                withId(android.R.id.button1), withText("Yes"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    3
                )
            )
        )
        materialButton8.perform(scrollTo(), click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
