package com.example.webmusictest.beans.DailyNews;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by King on 2017/6/14.
 */

public class RBody {
    @SerializedName("list")
    private List<NewsList> newsLists;

    public List<NewsList> getNewsLists() {
        return newsLists;
    }
}
