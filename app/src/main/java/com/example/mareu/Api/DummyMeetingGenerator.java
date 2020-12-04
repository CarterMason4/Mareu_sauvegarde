package com.example.mareu.Api;

import com.example.mareu.Model.Meeting;
import com.example.mareu.R;

import java.util.Arrays;
import java.util.List;

public abstract class DummyMeetingGenerator {


    public static List<Meeting> meetings = Arrays.asList(
            new Meeting(1, R.drawable.ic_first_color,
                    "Mardi 2 Décembre 2020", "14h00", "Salle A", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                            "Développement de la nouvelle appli'"),
            new Meeting(2, R.drawable.ic_third_color,
                    "Mardi 2 Décembre 2020", "14h00", "Salle A", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'"),
            new Meeting(3, R.drawable.ic_second_color,
                    "Mardi 2 Décembre 2020", "14h00", "Salle A", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'"),
            new Meeting(4, R.drawable.ic_third_color,
                    "Mardi 2 Décembre 2020", "14h00", "Salle A", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'"),
            new Meeting(5, R.drawable.ic_third_color,
                    "Mardi 2 Décembre 2020", "14h00", "Salle A", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'"),
            new Meeting(7, R.drawable.ic_second_color,
                    "Mardi 2 Décembre 2020", "14h00", "Salle A", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'"),
            new Meeting(8, R.drawable.ic_first_color,
                    "Mardi 2 Décembre 2020", "14h00", "Salle A", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'"),
            new Meeting(9, R.drawable.ic_second_color,
                    "Mardi 2 Décembre 2020", "14h00", "Salle A", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'"),
            new Meeting(10, R.drawable.ic_second_color,
                    "Mardi 2 Décembre 2020", "14h00", "Salle A", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'"),
            new Meeting(11, R.drawable.ic_third_color,
                    "Mardi 2 Décembre 2020", "14h00", "Salle A", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'"),
            new Meeting(12, R.drawable.ic_first_color,
                    "Mardi 2 Décembre 2020", "14h00", "Salle A", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'"),
            new Meeting(13, R.drawable.ic_third_color,
                    "Mardi 2 Décembre 2020", "14h00", "Salle A", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'")
    );


    public static List<Meeting> getMeetings() {
        return meetings;
    }


}
