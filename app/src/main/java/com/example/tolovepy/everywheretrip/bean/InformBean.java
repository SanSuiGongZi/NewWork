package com.example.tolovepy.everywheretrip.bean;

public class InformBean {

    private String time;
    private String title;
    private String text;

    public InformBean() {
    }

    public InformBean(String time, String title, String text) {
        this.time = time;
        this.title = title;
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "InformBean{" +
                "time='" + time + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
