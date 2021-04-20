package com.example.mareu.Api;

import com.example.mareu.Model.Meeting;

import java.util.List;

public interface MeetingApiService {

    List<Meeting> getAllMeetings();

    void addMeeting(Meeting meeting);

    void deleteMeeting(Meeting meeting);

    List<Meeting> filterMeetings(CharSequence filter);
}