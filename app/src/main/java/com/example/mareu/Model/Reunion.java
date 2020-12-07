package com.example.mareu.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Reunion implements Parcelable {

    /**
     * ID of the meeting in the list
     * @param id
     * */

    private int id;


    /**
     * Couleur de la réunion
     * @param couleur
    * */

    private int couleur;


    /**
     * date of the meeting
     * @param date
     * */
    private String date;


    /**
     * Time of the meeting
     * @param time
     * */
    private String time;


    /**
     * Room of the meeting
     * @param room
     * */
    private String room;

    // TODO Peut-être devrais-je carrément en faire un objet.


    /**
     * Entrants of the meeting
     * @param date
     * */
    private String entrants;


    /**
     * Subject of the meeting
     * @param date
     * */
    private String subject;



    public Reunion(int id, int couleur, String date, String time, String room, String entrants, String subject) {
        setId(id);
        setCouleur(couleur);
        setDate(date);
        setTime(time);
        setRoom(room);
        setEntrants(entrants);
        setSubject(subject);
    }



    public int getId() {
        return id;
    }

    public int getCouleur() {
        return couleur;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getRoom() {
        return room;
    }

    public String getEntrants() {
        return entrants;
    }

    public String getSubject() {
        return subject;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCouleur(int couleur) {
        this.couleur = couleur;
    }

    // TODO Supprimer la vérification des NullPointerException

    public void setDate(String date) {
        if(date.isEmpty() || date == null) {
            this.date = "";
        } else {
            this.date = date;
        }
    }

    public void setTime(String time) {
        if(time.isEmpty() || time == null) {
            this.time = "";
        } else {
            this.time = time;
        }
    }

    public void setRoom(String room) {
        if(room.isEmpty() || room == null) {
            this.room = "";
        } else {
            this.room = room;
        }
    }

    public void setEntrants(String entrants) {
        if(entrants.isEmpty() || entrants == null) {
            this.entrants = "";
        } else {
            this.entrants = entrants;
        }
    }

    public void setSubject(String subject) {
        if(subject.isEmpty() || subject == null) {
            this.subject = "";
        } else {
            this.subject = subject;
        }
    }


    @Override
    public String toString() {
        return new StringBuffer()
                .append("ID : ").append(id).append('\n')
                .append("Date and time : ").append(date).append(' ').append(time).append('\n')
                .append("Room : ").append(room).append('\n')
                .append("Entrants : ").append(entrants).append('\n')
                .append("Subject : ").append(subject).append('\n')
                .toString();
    }



    protected Reunion(Parcel in) {
        id = in.readInt();
        couleur = in.readInt();
        date = in.readString();
        time = in.readString();
        room = in.readString();
        entrants = in.readString();
        subject = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(couleur);
        dest.writeString(date);
        dest.writeString(time);
        dest.writeString(room);
        dest.writeString(entrants);
        dest.writeString(subject);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Reunion> CREATOR = new Creator<Reunion>() {
        @Override
        public Reunion createFromParcel(Parcel in) {
            return new Reunion(in);
        }

        @Override
        public Reunion[] newArray(int size) {
            return new Reunion[size];
        }
    };
}
