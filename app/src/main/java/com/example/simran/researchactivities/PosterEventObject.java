package com.example.simran.researchactivities;

import android.net.Uri;

import java.net.URI;

/**
 * Created by Simran on 10/3/16.
 */
public class PosterEventObject
{

    private String postedBy;
    private String description;
    private int image;
    private Uri imguri = null;

    public PosterEventObject(String postedBy, String description, int image)
    {
        this.postedBy = postedBy;
        this.description = description;
        this.image = image;
    }

    public PosterEventObject(String postedBy, String description, Uri image)
    {
        this.postedBy = postedBy;
        this.description = description;
        this.imguri = image;
    }

    public String getPostedBy()
    {
        return postedBy;
    }

    public void setPostedBy(String postedBy)
    {
        this.postedBy = postedBy;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getImage()
    {
        return image;
    }

    public void setImage(int image)
    {
        this.image = image;
    }

    public Uri getImguri() {
        return imguri;
    }

    public void setImguri(Uri imguri) {
        this.imguri = imguri;
    }


}
