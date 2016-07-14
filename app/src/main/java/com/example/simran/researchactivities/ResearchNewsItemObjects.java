package com.example.simran.researchactivities;

/**
 * Created by Simran on 6/26/16.
 */
public class ResearchNewsItemObjects
{
    private String title;
    private String newsAbstract;
    private int photo;
    private String authors;

    public ResearchNewsItemObjects(String title, String newsAbstract, String authors, int photo)
    {
        this.title = title;
        this.newsAbstract = newsAbstract;
        this.photo = photo;
        this.authors = authors;
    }

    public String getNewsTitle() {
        return title;
    }

    public String getNewsAbstract() {return newsAbstract;}

    public String getAuthors(){return authors;}

    public int getPhoto(){return photo;}


    public void setNewsTitle(String title) {
        this.title = title;
    }

    public void setNewsAbstract(String newsAbstract) {this.newsAbstract = newsAbstract;}

    public void setAuthors(String authors){this.authors = authors;}

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}
