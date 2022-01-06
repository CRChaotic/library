package com.library.models;

import com.library.MVC.Model;

public class User extends Model{
    private int id;
    private String username;
    private String password;
    private boolean admin = false;

    public User(){
        super("user",new String[]{"username", "password", "admin"},"id");
    }

    public User(String username, String password) {
        this();
        this.username = username;
        this.password = password;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin){
        this.admin = admin;
    }
}
