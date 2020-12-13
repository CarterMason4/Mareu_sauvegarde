package com.example.mareu.Api;

import com.example.mareu.Model.Reunion;

import java.util.ArrayList;
import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {

    private List<Reunion> reunions = DummyMeetingGenerator.getReunions();

    @Override
    public List<Reunion> getAllMeetings() {
        return reunions;
    }

    @Override
    public void addMeeting(Reunion reunion) {
        reunions.add(0, reunion);
    }

    @Override
    public void deleteMeeting(Reunion reunion) {
        for(Reunion loopReunion : reunions) {
            if(loopReunion.equals(reunion)) {
                reunions.remove(reunion);
                break;
            }
        }
    }

    @Override
    public void deleteAllMeetings() {
        reunions.clear();
    }


    @Override
    public void populerListe() {
        reunions = DummyMeetingGenerator.getReunions();
    }
}
