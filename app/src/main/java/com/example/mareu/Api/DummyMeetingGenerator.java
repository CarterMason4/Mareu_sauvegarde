package com.example.mareu.Api;

import com.example.mareu.Model.Reunion;
import com.example.mareu.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyMeetingGenerator {

    // TODO mettre toutes les dates au format du premier item.



    public static List<Reunion> REUNIONS = Arrays.asList(
            new Reunion(1, R.drawable.ic_first_color,
                    "02/12/2020", "15h00", "Salle A", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                            "Développement de la nouvelle appli'"),
            new Reunion(2, R.drawable.ic_third_color,
                    "15/02/2002", "15h00", "Salle B", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'"),
            new Reunion(3, R.drawable.ic_second_color,
                    "12/08/2020", "16h00", "Salle B", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'"),
            new Reunion(4, R.drawable.ic_third_color,
                    "01/01/2021", "17h00", "Salle E", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'"),
            new Reunion(5, R.drawable.ic_third_color,
                    "02/12/2020", "14h30", "Salle J", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'"),
            new Reunion(7, R.drawable.ic_second_color,
                    "04/03/2021", "15h00", "Salle J", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'"),
            new Reunion(8, R.drawable.ic_first_color,
                    "05/05/2022", "09h00", "Salle J", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'"),
            new Reunion(9, R.drawable.ic_second_color,
                    "06/06/2021", "14h00", "Salle D", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'"),
            new Reunion(10, R.drawable.ic_second_color,
                    "24/12/2020", "14h00", "Salle E", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'"),
            new Reunion(11, R.drawable.ic_third_color,
                    "29/05/2020", "14h00", "Salle C", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'"),
            new Reunion(12, R.drawable.ic_first_color,
                    "15/05/2020", "14h00", "Salle G", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'"),
            new Reunion(13, R.drawable.ic_third_color,
                    "02/02/2020", "14h00", "Salle G", "bill@lamzone.com, bill@lamzone.com, bill@lamzone.combill@lamzone.com",
                    "Développement de la nouvelle appli'")
    );


    public static List<Reunion> getReunions() {
        return new ArrayList<>(REUNIONS);
    }

}