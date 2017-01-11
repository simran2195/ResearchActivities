package com.example.simran.researchactivities;

/**
 * Created by Simran on 8/30/16.
 */
public class DirectoryItemObjects
{

    private String name;
    private String designation;
    private String room;
    private String extension;
    private String number;
    private int photo;
    private String email;

    public DirectoryItemObjects(String name, String designation, String room, String extension, String email, int photo)
    {
        this.name = name;
        this.designation = designation;
        this.room = room;
        this.extension = extension;
        this.photo = photo;
        this.email = email;
        this.number = "01126907" + this.extension;
    }

    public String getName() {
        return name;
    }

    public String getDesignation() {
        return designation;
    }

    public String  getRoom() {
        return room;
    }

    public String getExtension() {return extension;}

    public String getEmail(){return  email;}

    public int getPhoto() {return photo;}



    public void setName(String name) {
        this.name = name;
    }

    public void setDesignation(String designation) {this.designation = designation;}

    public void setRoom(String room) {
        this.room = room;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public void setEmail(String email){this.email = email;}

    public void setPhoto(int photo) {
        this.photo = photo;
    }

}
