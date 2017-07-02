package com.example.webmusictest.beans.Login;

import org.litepal.crud.DataSupport;

/**
 * Created by King on 2017/6/24.
 */

public class User extends DataSupport{
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
