package com.example.webmusictest.beans.BingPicture;

import java.util.List;

/**
 * Created by King on 2017/6/21.
 */

public class ResBody {
    private int ret_code;
    private List<Picture> list;

    public int getRet_code() {
        return ret_code;
    }

    public List<Picture> getList() {
        return list;
    }

    public void setRet_code(int ret_code) {
        this.ret_code = ret_code;
    }

    public void setList(List<Picture> list) {
        this.list = list;
    }
}
