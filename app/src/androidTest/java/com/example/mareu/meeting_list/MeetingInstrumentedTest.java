package com.example.mareu.meeting_list;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.example.mareu.Activity.AddMeetingActivity;
import com.example.mareu.Activity.MainActivity;
import com.example.mareu.Api.DummyMeetingApiService;
import com.example.mareu.Api.MeetingApiService;
import com.example.mareu.DI.DI;
import com.example.mareu.Model.Meeting;
import com.example.mareu.R;
import com.example.mareu.utils.CustomMatchers;
import com.example.mareu.utils.DeleteViewAction;
import com.example.mareu.utils.ReplaceTextViewAction;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static com.google.android.material.internal.ContextUtils.getActivity;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MeetingInstrumentedTest {


    private final int list_size = 12;
    private MeetingApiService apiService;
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
        apiService = DI.getNewMeetingApiService();
    }

    @Test
    public void meetingsListShouldNotBeEmpty() {
        onView(ViewMatchers.withId(R.id.list_meetings)).check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void meetingListDeleteButtonShouldDeleteItem() {
        onView(ViewMatchers.withId(R.id.list_meetings)).check(withItemCount(list_size));

        onView(ViewMatchers.withId(R.id.list_meetings))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));

        onView(ViewMatchers.withId(R.id.list_meetings)).check(withItemCount(list_size - 1));
    }

    @Test
    public void addMeetingActivityShouldAddMeeting() {

        onView(withId(R.id.list_meetings)).check(withItemCount(list_size));

        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), AddMeetingActivity.class);
        addMeetingActivity.launchActivity(intent);

        onView(withId(R.id.editTextSalle)).check(matches(isDisplayed()));
        onView(withId(R.id.editTextViewIntervenants)).check(matches(isDisplayed()));
        onView(withId(R.id.editTextAPropos)).check(matches(isDisplayed()));

        onView(withId(R.id.editTextSalle)).perform(typeText("Salle A"));
        onView(withId(R.id.editTextViewIntervenants)).perform(typeText("stephen@lamzone.com, chino@lamzone.com, sergio@lamzone.com, abe@lamzone.com"));
        onView(withId(R.id.editTextAPropos)).perform(typeText("Developpement de Genesis"));

        onView(withId(R.id.textViewTime)).perform(new ReplaceTextViewAction("09h30"));
        onView(withId(R.id.textViewDate)).perform(new ReplaceTextViewAction("15/05/2022"));

        onView(withId(R.id.valider_button))
                .perform(closeSoftKeyboard())
                .perform(click());

        /*Return to main activity.*/

        onView(withId(R.id.list_meetings)).check(matches(isDisplayed()));
        onView(withId(R.id.list_meetings)).check(withItemCount(list_size + 1));

        // On doit vérifier que le meeting ajouté est bien le bon.


        Meeting meeting = apiService.getAllMeetings().get(0);

        /*onView(withId(R.id.list_meetings))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, null))
                .check(matches(withText(meeting.get)));*/

    }

    @Test
    public void filterShouldWork() {
        onView(withId(R.id.filtrer))
                .perform(click());

        onView(isAssignableFrom(EditText.class)).perform(typeText("Salle A"));

        // onData(allOf(is(instanceOf(String.class)), withText("Réunion A"))).check(matches(isDisplayed()));

        onView(withText("Réunion A - 15h00 - Luidgi"))
                .inRoot(withDecorView(not(Matchers.is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));

        // onData(allOf(is(instanceOf(String.class)), CustomMatchers.withItemContent("Réunion A"))).check(matches(isDisplayed()));
    }


    /*@Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.mareu", appContext.getPackageName());
    }*/
}