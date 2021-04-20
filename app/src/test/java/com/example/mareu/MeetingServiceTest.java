package com.example.mareu;

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
        Meeting newMeeting = apiService.getAllMeetings().get(0);
        apiService.deleteMeeting(newMeeting);
        List<Meeting> allMeetings = apiService.getAllMeetings();
        assertNotEquals(allMeetings.get(0), newMeeting);
    }

    /**
     * Test the filter.
     * */


    @Test
    public void filterMeetings() {
        Meeting meeting = apiService.getAllMeetings().get(0);
        Meeting filteredMeeting = apiService.filterMeetings("Salle A").get(0);
        assertEquals(meeting, filteredMeeting);

    }

}