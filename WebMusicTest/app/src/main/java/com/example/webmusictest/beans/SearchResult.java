package com.example.webmusictest.beans;

/**
 * Created by King on 2017/6/4.
 */

public class SearchResult {
    private String rows;
    private String song_id;
    private String singer_id;
    private String song_name;
    private String singer_name;
    private String duration;
    private String url;

    public String getRows() {
        return rows;
    }

    public String getSong_id() {
        return song_id;
    }

    public String getSinger_id() {
        return singer_id;
    }

    public String getSong_name() {
        return song_name;
    }

    public String getSinger_name() {
        return singer_name;
    }

    public String getDuration() {
        return duration;
    }

    public String getUrl() {
        return url;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public void setSong_id(String song_id) {
        this.song_id = song_id;
    }

    public void setSinger_id(String singer_id) {
        this.singer_id = singer_id;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public void setSinger_name(String singer_name) {
        this.singer_name = singer_name;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
