package com.example.webmusictest.beans;

import java.util.List;

/**
 * Created by King on 2017/6/4.
 */

public class Person {
    private int id;
    private String version;
    private List<Name> name;

    public static class Name{
        private String a;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }
    }

    public List<Name> getName() {
        return name;
    }

    public void setName(List<Name> name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getVersion() {
        return version;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
