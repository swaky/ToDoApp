package com.swanand.todo.model;

/**
 * Created by swanand on 7/9/2016.
 */
public class Note {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String title;
    private String description;
    private String datetime;

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public Note(int id, String title, String description,String datetime) {
        this.id=id;
        this.title = title;
        this.description = description;
        this.datetime=datetime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
