package com.example.mareu.Events;

import com.example.mareu.Model.Reunion;

import java.util.ArrayList;
import java.util.List;

public class DeleteMeetingEvent {

    /**
     * Meeting to delete
     * @param reunion
     * */

    public Reunion reunion;


    public DeleteMeetingEvent(Reunion reunion) {
        this.reunion = reunion;
    }
}
