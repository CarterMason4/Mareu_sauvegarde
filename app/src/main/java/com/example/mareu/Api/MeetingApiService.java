package com.example.mareu.Api;

import com.example.mareu.Model.Reunion;

import java.util.List;

public interface MeetingApiService {

    // TODO Ajouter des annotations

    List<Reunion> getAllMeetings();

    void addMeeting(Reunion reunion);

    void deleteMeeting(Reunion reunion);

    void deleteAllMeetings();

    List<Reunion> filterMeetings(CharSequence filter);
}