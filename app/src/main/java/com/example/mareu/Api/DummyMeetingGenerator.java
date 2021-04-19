package com.example.mareu.Api;

import com.example.mareu.Model.Meeting;
import com.example.mareu.Utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyMeetingGenerator {

    public static List<Meeting> MEETINGS = Arrays.asList(
            new Meeting(1, Utils.getImageDrawable(),
                    "02/12/2020", "15h00", "Peach", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                            "Développement de la nouvelle appli'"),
            new Meeting(2, Utils.getImageDrawable(),
                    "15/02/2002", "15h00", "Mario", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'"),
            new Meeting(3, Utils.getImageDrawable(),
                    "12/08/2020", "16h00", "Luidgi", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'"),
            new Meeting(4, Utils.getImageDrawable(),
                    "01/01/2021", "17h00", "Peach", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'"),
            new Meeting(5, Utils.getImageDrawable(),
                    "02/12/2020", "14h30", "Mario", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'"),
            new Meeting(6, Utils.getImageDrawable(),
                    "04/03/2021", "15h00", "Luidgi", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'"),
            new Meeting(7, Utils.getImageDrawable(),
                    "05/05/2022", "09h00", "Mario", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'"),
            new Meeting(8, Utils.getImageDrawable(),
                    "06/06/2021", "14h00", "Mario", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'"),
            new Meeting(9, Utils.getImageDrawable(),
                    "24/12/2020", "14h00", "Peach", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'"),
            new Meeting(10, Utils.getImageDrawable(),
                    "29/05/2020", "14h00", "Peach", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'"),
            new Meeting(11, Utils.getImageDrawable(),
                    "15/05/2020", "14h00", "Mario", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'"),
            new Meeting(12, Utils.getImageDrawable(),
                    "02/02/2020", "14h00", "Luidgi", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'"));


    public static List<Meeting> getMeetings() {
        return new ArrayList<>(MEETINGS);
    }

    public static Meeting generateMeeting() {
        return new Meeting(MEETINGS.size() + 1, Utils.getImageDrawable(),
                "02/12/2020",
                "15h00",
                "Mario",
                "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                "Développement de la nouvelle appli'");
    }

}