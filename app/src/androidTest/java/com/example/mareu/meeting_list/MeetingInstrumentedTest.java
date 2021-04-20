package com.example.mareu.meeting_list;


import android.widget.EditText;
import com.example.mareu.Activity.AddMeetingActivity;
import com.example.mareu.Activity.MainActivity;
import com.example.mareu.R;
import com.example.mareu.utils.DeleteViewAction;
import com.example.mareu.utils.ReplaceTextViewAction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
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

    /**
     * Test if the meeting list is empty or not.
     * */

    @Test
    public void meetingsListShouldNotBeEmpty() {
        onView(ViewMatchers.withId(R.id.list_meetings)).check(matches(hasMinimumChildCount(1)));
    }

    /**
     * Test the delete function.
     * */

    @Test
    public void meetingListDeleteButtonShouldDeleteItem() {
        onView(ViewMatchers.withId(R.id.list_meetings)).check(withItemCount(list_size));

        onView(ViewMatchers.withId(R.id.list_meetings))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));

        onView(ViewMatchers.withId(R.id.list_meetings)).check(withItemCount(list_size - 1));
    }


    /**
     * Test the add meeting activity.
     * */

    @Test
    public void addMeetingActivityShouldAddMeeting() {

        onView(withId(R.id.list_meetings)).check(withItemCount(list_size));

        onView(withId(R.id.fab)).perform(click());

        onView(withId(R.id.editTextSalle)).check(matches(isDisplayed()));
        onView(withId(R.id.editTextViewIntervenants)).check(matches(isDisplayed()));
        onView(withId(R.id.editTextAPropos)).check(matches(isDisplayed()));

        onView(withId(R.id.heure)).perform(new ReplaceTextViewAction("09h30"));
        onView(withId(R.id.date)).perform(new ReplaceTextViewAction("15/05/2022"));

        onView(withId(R.id.editTextSalle)).perform(typeText("Peach"));
        onView(withId(R.id.editTextViewIntervenants)).perform(typeText("stephen@lamzone.com, chino@lamzone.com, sergio@lamzone.com, abe@lamzone.com"));
        onView(withId(R.id.editTextAPropos)).perform(typeText("Nouvel album"));

        onView(withId(R.id.valider_button))
                .perform(closeSoftKeyboard())
                .perform(click());

        // Return to main activity.

        onView(withId(R.id.list_meetings)).check(matches(isDisplayed()));
        onView(withId(R.id.list_meetings)).check(withItemCount(list_size + 1));
    }

    /**
     * Test the filter with room string.
     * */

    @Test
    public void filterShouldWorkWithRoom() {

        onView(withId(R.id.filtrer))
                .perform(click());

        onView(isAssignableFrom(EditText.class)).perform(typeText("Peach"));

        onView(ViewMatchers.withId(R.id.list_meetings)).check(matches(hasChildCount(4)));
    }

    /**
     * Test the filter with time string.
     * */

    @Test
    public void filterShouldWorkWithTime() {
        onView(withId(R.id.filtrer))
                .perform(click());

        onView(isAssignableFrom(EditText.class)).perform(typeText("15h00"));

        onView(ViewMatchers.withId(R.id.list_meetings)).check(matches(hasChildCount(3)));

    }
}