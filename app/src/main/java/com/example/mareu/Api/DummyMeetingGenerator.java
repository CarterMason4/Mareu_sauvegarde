package com.example.mareu.Api;

import com.example.mareu.Model.Reunion;
import com.example.mareu.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyMeetingGenerator {


    public static List<Reunion> REUNIONS = Arrays.asList(
            new Reunion(1, R.drawable.ic_first_color,
                    "Mardi 2 Décembre 2020", "14h00", "Salle A", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                            "Développement de la nouvelle appli'"),
            new Reunion(2, R.drawable.ic_third_color,
                    "Mardi 2 Décembre 2020", "14h00", "Salle A", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'"),
            new Reunion(3, R.drawable.ic_second_color,
                    "Mardi 2 Décembre 2020", "14h00", "Salle A", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'"),
            new Reunion(4, R.drawable.ic_third_color,
                    "Mardi 2 Décembre 2020", "14h00", "Salle A", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'"),
            new Reunion(5, R.drawable.ic_third_color,
                    "Mardi 2 Décembre 2020", "14h00", "Salle A", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'"),
            new Reunion(7, R.drawable.ic_second_color,
                    "Mardi 2 Décembre 2020", "14h00", "Salle A", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'"),
            new Reunion(8, R.drawable.ic_first_color,
                    "Mardi 2 Décembre 2020", "14h00", "Salle A", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'"),
            new Reunion(9, R.drawable.ic_second_color,
                    "Mardi 2 Décembre 2020", "14h00", "Salle A", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'"),
            new Reunion(10, R.drawable.ic_second_color,
                    "Mardi 2 Décembre 2020", "14h00", "Salle A", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'"),
            new Reunion(11, R.drawable.ic_third_color,
                    "Mardi 2 Décembre 2020", "14h00", "Salle A", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'"),
            new Reunion(12, R.drawable.ic_first_color,
                    "Mardi 2 Décembre 2020", "14h00", "Salle A", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'"),
            new Reunion(13, R.drawable.ic_third_color,
                    "Mardi 2 Décembre 2020", "14h00", "Salle A", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'")
    );


    public static List<Reunion> getReunions() {
        return new ArrayList<>(REUNIONS);
    }


}
