package com.example.mareu.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Meeting implements Parcelable {

    /**
     * ID of the meeting in the list
     * @param id
     * */

    private int id;


    /**
     * Color of the meeting
     * @param color
    * */

    private int color;


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

    public Meeting() {}


    public Meeting(int id, int color, String date, String time, String room, String entrants, String subject) {
        setId(id);
        setColor(color);
        setDate(date);
        setTime(time);
        setRoom(room);
        setEntrants(entrants);
        setSubject(subject);
    }



    public int getId() {
        return id;
    }

    public int getColor() {
        return color;
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

    public void setColor(int color) {
        this.color = color;
    }

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
        if(entrants.isEmpty() ||entrants == null) {
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



    protected Meeting(Parcel in) {
        id = in.readInt();
        color = in.readInt();
        date = in.readString();
        time = in.readString();
        room = in.readString();
        entrants = in.readString();
        subject = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(color);
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

    public static final Creator<Meeting> CREATOR = new Creator<Meeting>() {
        @Override
        public Meeting createFromParcel(Parcel in) {
            return new Meeting(in);
        }

        @Override
        public Meeting[] newArray(int size) {
            return new Meeting[size];
        }
    };
}
