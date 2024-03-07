package com.lseg.testframework.utils.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection instance;
    private Connection connection;

    private DBConnection(String url, String user, String password) {
        try {
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public static DBConnection getInstance(String url, String user, String password) throws SQLException {
        if (instance == null) {
            instance = new DBConnection(url, user, password);
        } else if (instance.getConnection().isClosed()) {
            instance = new DBConnection(url, user, password);
        }
        return instance;
    }
}

