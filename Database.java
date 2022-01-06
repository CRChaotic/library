package com.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private String user;
    private String password;
    private String database;
    private Connection connection;

    public Database(String user, String password, String database) {
        this.user = user;
        this.password = password;
        this.database = database;
        init();
    }

    private boolean init() {
        String url = "jdbc:mysql://localhost:3306/" + this.database + "?serverTimezone=UTC&useUnicode=yes&&characterEncoding=UTF-8";
        try {
            this.connection = DriverManager.getConnection(url, this.user, password);
            return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public Connection getConnection(){
        return this.connection;
    }

}