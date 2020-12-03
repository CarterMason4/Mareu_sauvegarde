package com.example.mareu.Api;

import com.example.mareu.Model.Meeting;

import java.util.Arrays;
import java.util.List;

public abstract class DummyMeetingGenerator {


    // TODO A remplir plus tard

    public static List<Meeting> meetings = Arrays.asList(
            new Meeting());


    public static List<Meeting> getMeetings() {
        return meetings;
    }


}
