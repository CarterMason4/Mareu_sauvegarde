package com.example.mareu.Api;

import com.example.mareu.Model.Meeting;

import java.util.ArrayList;
import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> meetings = DummyMeetingGenerator.getMeetings();

    @Override
    public List<Meeting> getAllMeetings() {
        return meetings;
    }

    @Override
    public void addMeeting(Meeting meeting) {
        meetings.add(0, meeting);
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        for(Meeting loopMeeting : meetings) {
            if(loopMeeting.equals(meeting)) {
                meetings.remove(meeting);
                break;
            }
        }
    }

    @Override
    public void deleteAllMeetings() {
        meetings.clear();
    }

    @Override
    public List<Meeting> filterMeetings(CharSequence filtre) {
        List<Meeting> listeFiltree = new ArrayList<>();

        if(filtre == null || filtre.length() == 0) {
             return DummyMeetingGenerator.getMeetings();
        } else {

            String filterPattern = filtre.toString().toLowerCase();

            for(Meeting meeting : meetings) {
                if(meeting.getRoom().toLowerCase().contains(filterPattern) ||
                        meeting.getDate().toLowerCase().contains(filterPattern)) {
                    listeFiltree.add(meeting);
                }
            }
        }
        return listeFiltree;
    }
}