package com.example.mareu.meeting_list;

import android.content.Context;
import android.content.Intent;

import com.example.mareu.Activity.AddMeetingActivity;
import com.example.mareu.Activity.MainActivity;
import com.example.mareu.R;
import com.example.mareu.utils.DeleteViewAction;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.example.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MeetingInstrumentedTest {


    private final int list_size = 12;
    private MainActivity mActivity;

    @Rule
    public IntentsTestRule<MainActivity> mActivityRule = new IntentsTestRule(MainActivity.class);

    @Rule
    public ActivityTestRule<AddMeetingActivity> addMeetingActivity =
            new ActivityTestRule(AddMeetingActivity.class, true, false);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    @Test
    public void meetingsListShouldNotBeEmpty() {
        onView(ViewMatchers.withId(R.id.list_meetings)).check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void meetingListDeleteButtonShouldDeleteItem() {
        onView(ViewMatchers.withId(R.id.list_meetings)).check(withItemCount(list_size));
        // When perform a click on a delete icon
        onView(ViewMatchers.withId(R.id.list_meetings))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(ViewMatchers.withId(R.id.list_meetings)).check(withItemCount(list_size - 1));
    }

    @Test
    public void addMeetingActivityShouldAddMeeting() {
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), AddMeetingActivity.class);
        addMeetingActivity.launchActivity(intent);

        onView(withId(R.id.list_meetings)).check(withItemCount(list_size));

        onView(withId(R.id.editTextSalle)).check(matches(isDisplayed()));
        onView(withId(R.id.editTextViewIntervenants)).check(matches(isDisplayed()));
        onView(withId(R.id.editTextAPropos)).check(matches(isDisplayed()));

        onView(withId(R.id.editTextSalle)).perform(clearText(),typeText("Salle A"));
        onView(withId(R.id.editTextViewIntervenants)).perform(clearText(), typeText("stephen@lamzone.com, chino@lamzone.com, sergio@lamzone.com, abe@lamzone.com"));
        onView(withId(R.id.editTextAPropos)).perform(clearText(), typeText("Développement de l'album \"Genesis\""));

        onView(withId(R.id.textViewTime)).perform(clearText(), typeText("09h30"));
        onView(withId(R.id.textViewDate)).perform(clearText(), typeText("04/03/2021"));

        onView(withId(R.id.valider_button)).perform(click());

        onView(withId(R.id.list_meetings)).check(matches(isDisplayed()));

        onView(withId(R.id.list_meetings)).check(withItemCount(list_size + 1));


    }



    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.mareu", appContext.getPackageName());
    }
}