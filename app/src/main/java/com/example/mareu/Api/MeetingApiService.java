package com.example.mareu.Api;

import com.example.mareu.Model.Meeting;

import java.util.List;

public interface MeetingApiService {

    // TODO Ajouter des annotations

    List<Meeting> getAllMeetings();

    void addMeeting(Meeting meeting);

    void deleteMeeting(Meeting meeting);

    void deleteAllMeetings();

    List<Meeting> filterMeetings(CharSequence filter);
}