package com.example.simran.researchactivities;

/**
 * Created by Simran on 6/7/16.
 */
public class SeminarItemObjects
{
    private String title;
    private String speaker;
    private String date;
    private int photo;
    private String dateToAddToCalendar;
    private String seminarAbstract;

    public SeminarItemObjects(String title, String speaker, String date, int photo, String seminarAbstract, String dateToAddToCalendar)
    {
        this.title = title;
        this.speaker = speaker;
        this.date = date;
        this.photo = photo;
        this.seminarAbstract = seminarAbstract;
        this.dateToAddToCalendar = dateToAddToCalendar;
    }

    public String getTitle() {
        return title;
    }

    public String getSpeaker() {
        return speaker;
    }

    public String  getDate() {
        return date;
    }

    public String getSeminarAbstract() {return seminarAbstract;}

    public String getDateToAddToCalendar(){return  this.dateToAddToCalendar;}



    public void setTitle(String title) {
        this.title = title;
    }

    public void setSeminarAbstract(String seminarAbstract) {this.seminarAbstract = seminarAbstract;}

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPhoto() {
        return photo;
    }

    public void setDateToAddToCalendar(String dateToAddToCalendar){this.dateToAddToCalendar = dateToAddToCalendar;}

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}
