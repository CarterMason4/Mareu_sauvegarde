package com.example.mareu.DI;

import com.example.mareu.Api.DummyMeetingApiService;
import com.example.mareu.Api.MeetingApiService;

public class Di {

    private static MeetingApiService apiService = new DummyMeetingApiService();


    public static MeetingApiService getMeetingApiService() {
        return apiService;
    }
}
