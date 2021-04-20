package com.example.mareu.Events;

import com.example.mareu.Model.Meeting;

public class DeleteMeetingEvent {

    /**
     * Meeting to delete
     * @param meeting
     * */

    public Meeting meeting;

    public DeleteMeetingEvent(Meeting meeting) {
        this.meeting = meeting;
    }
}
