package com.example.calendar.Entity;

import java.util.Date;

public class Idea implements Comparable<Idea>{
    private int id;
    private String title;
    private String content;
    private Date date;

    @Override
    public String toString() {
        return "Idea{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                '}';
    }

    @Override
    public int compareTo(Idea i){
        if(this.getDate().after(i.getDate())){
            return 1;
        }
        else if(this.getDate().before(i.getDate())){
            return -1;
        }
        return 0;
    }

    public Idea(int id, String title, String content, Date date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
