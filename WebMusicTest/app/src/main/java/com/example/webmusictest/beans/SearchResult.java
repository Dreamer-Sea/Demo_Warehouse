package com.example.webmusictest.beans;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by King on 2017/6/5.
 */

public class SearchResult {
    private int rows;
    private List<Data> data;

    public void setData(List<Data> data) {
        this.data = data;
    }

    public List<Data> getData() {

        return data;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public static class Data{
        @SerializedName("song_name")
        private String songName;

        @SerializedName("singer_name")
        private String singerName;

        public void setSongName(String songName) {
            this.songName = songName;
        }

        public void setSingerName(String singerName) {
            this.singerName = singerName;
        }

        public String getSongName() {

            return songName;
        }

        public String getSingerName() {
            return singerName;
        }

        public void setAuditonList(List<AuditionList> auditonList) {
            this.auditonList = auditonList;
        }

        public void setMvList(List<MvList> mvList) {
            this.mvList = mvList;
        }

        public List<AuditionList> getAuditonList() {
            return auditonList;
        }

        public List<MvList> getMvList() {
            return mvList;
        }

        @SerializedName("audition_list")
        private List<AuditionList> auditonList;
        public static class AuditionList{
            private String duration;
            private String format;
            private String url;
            private String size;

            public void setDuration(String duration) {
                this.duration = duration;
            }

            public void setFormat(String format) {
                this.format = format;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public void setSize(String size) {
                this.size = size;
            }

            public String getDuration() {

                return duration;
            }

            public String getFormat() {
                return format;
            }

            public String getUrl() {
                return url;
            }

            public String getSize() {
                return size;
            }
        }

        @SerializedName("mv_list")
        private List<MvList> mvList;
        public static class MvList{
            private String id;
            private String format;
            private String type_description;
            private String size;
            private String url;
            private String duration;
            private String pic_url;

            public void setFormat(String format) {
                this.format = format;
            }

            public String getFormat() {

                return format;
            }
            public String getId() {
                return id;
            }

            public String getType_description() {
                return type_description;
            }

            public String getSize() {
                return size;
            }

            public String getUrl() {
                return url;
            }

            public String getDuration() {
                return duration;
            }

            public String getPic_url() {
                return pic_url;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setType_description(String type_description) {
                this.type_description = type_description;
            }

            public void setSize(String size) {
                this.size = size;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            public void setPic_url(String pic_url) {
                this.pic_url = pic_url;
            }
        }
    }
}
