package com.blmsr.manager.models;

import java.io.Serializable;

/**
 * Created by LakshmiMadhav on 8/5/2015.
 */
public class User implements Serializable{

    private String password;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
