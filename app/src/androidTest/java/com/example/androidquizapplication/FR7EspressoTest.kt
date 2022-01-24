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
class FR7EspressoTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun fR7EspressoTest() {
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

        val recyclerView = onView(
            allOf(
                withId(R.id.banksRecyclerView),
                childAtPosition(
                    withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                    0
                )
            )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        val floatingActionButton2 = onView(
            allOf(
                withId(R.id.add_question),
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
        floatingActionButton2.perform(click())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.question),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("androidx.core.widget.NestedScrollView")),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText2.perform(replaceText("question?"), closeSoftKeyboard())

        val materialButton3 = onView(
            allOf(
                withId(R.id.confirmButton), withText("Confirm"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("androidx.core.widget.NestedScrollView")),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialButton3.perform(click())

        val appCompatEditText3 = onView(
            allOf(
                withId(R.id.optionField),
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
        appCompatEditText3.perform(replaceText("1"), closeSoftKeyboard())

        val materialButton4 = onView(
            allOf(
                withId(R.id.addOptionButton), withText("Add option"),
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
        materialButton4.perform(click())

        val appCompatEditText4 = onView(
            allOf(
                withId(R.id.optionField),
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
        appCompatEditText4.perform(replaceText("2"), closeSoftKeyboard())

        val materialButton5 = onView(
            allOf(
                withId(R.id.addOptionButton), withText("Add option"),
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
        materialButton5.perform(click())

        val materialButton6 = onView(
            allOf(
                withId(R.id.nextButton), withText("Next"),
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
        materialButton6.perform(click())

        val materialButton7 = onView(
            allOf(
                withId(R.id.finishButton), withText("Finish"),
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
        materialButton7.perform(click())

        val materialButton8 = onView(
            allOf(
                withId(R.id.questionsBackButton), withText("Banks list"),
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
        materialButton8.perform(click())

        val materialButton9 = onView(
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
        materialButton9.perform(click())

        val materialButton10 = onView(
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
        materialButton10.perform(click())

        val recyclerView2 = onView(
            allOf(
                withId(R.id.quizzesRecyclerView),
                childAtPosition(
                    withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                    1
                )
            )
        )
        recyclerView2.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        val materialButton11 = onView(
            allOf(
                withId(R.id.nextQuestion), withText("Finish Quiz"),
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
        materialButton11.perform(click())

        val materialButton12 = onView(
            allOf(
                withId(R.id.doneButton), withText("Done"),
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
        materialButton12.perform(click())

        val materialButton13 = onView(
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
        materialButton13.perform(click())

        val materialButton14 = onView(
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
        materialButton14.perform(click())

        val recyclerView3 = onView(
            allOf(
                withId(R.id.banksRecyclerView),
                childAtPosition(
                    withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                    0
                )
            )
        )
        recyclerView3.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

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

        val materialButton15 = onView(
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
        materialButton15.perform(scrollTo(), click())
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
