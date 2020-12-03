package com.example.mareu.Api;

import com.example.mareu.Model.Meeting;

import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> meetings = DummyMeetingGenerator.getMeetings();

    @Override
    public List<Meeting> getAllMeetings() {
        return meetings;
    }

    @Override
    public void addMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        for(Meeting loopMeeting : meetings) {
            if(meeting.equals(loopMeeting)) {
                meetings.remove(meeting);
                break;
            }
        }
    }

    @Override
    public void deleteAllMeetings() {
        meetings.clear();
    }
}
