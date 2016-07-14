package com.example.simran.researchactivities;

/**
 * Created by Simran on 5/26/16.
 */
public class ResearchItemObjects
{
    private String text;
    private int photo;

    public ResearchItemObjects(String name, int photo)
    {
        this.text = name;
        this.photo = photo;
    }

    public String getName() {
        return text;
    }

    public void setName(String name) {
        this.text = name;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}
