package com.example.mareu;

import android.view.Gravity;
import android.widget.Toast;

import com.example.mareu.Api.DummyMeetingGenerator;
import com.example.mareu.Api.MeetingApiService;
import com.example.mareu.DI.DI;
import com.example.mareu.Model.Meeting;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MeetingServiceTest {

    private MeetingApiService apiService;

    /**
     * Generates a brand new instance of the api service.
     * */
    @Before
    public void setUp() {
        apiService = DI.getNewMeetingApiService();
    }


    /**
     * Retrieves all the meetings.
     * */
    @Test
    public void getAllMeetings() {
        List<Meeting> meetings = apiService.getAllMeetings();
        List<Meeting> expectedMeetings = DummyMeetingGenerator.getMeetings();
        assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeetings.toArray()));
    }

    /**
     * Test that adds a meeting and tests if the meeting has really been added.
     * */
    @Test
    public void addMeeting() {
        Meeting newMeeting = DummyMeetingGenerator.generateMeeting();
        apiService.addMeeting(newMeeting);
        assertTrue(apiService.getAllMeetings().contains(newMeeting));
    }


    /**
     * Test that deletes a meeting and tests if the meeting has really been deleted.
     * */
    @Test
    public void deleteMeeting() {

        /*1 - get a meeting */
        Meeting newMeeting = apiService.getAllMeetings().get(0);

        /*2 -  delete that meeting*/
        apiService.deleteMeeting(newMeeting);

        /*3 - retrieve all meetings */
        List<Meeting> allMeetings = apiService.getAllMeetings();

        /*4 - test that the retrieved meeting above has succeussfully been deleted. */
        assertNotEquals(allMeetings.get(0), newMeeting);
    }

    @Test
    public void filterMeetings() {
        // Tests the meetings filter.
    }

}