package com.Database;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Mysql {
    
    private static Connection connection;
    private static Statement statement;

    private static String DATABASE = "1pemvis_tugasakhir";
    private static String USERNAME = "root";
    private static String PASSWORD = "";
    
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection
            ("jdbc:mysql://localhost:3306/"+DATABASE, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace(); //jika koneksi gagal
            //handle exeptions
        }
    }
    public static ResultSet execute(String query) throws SQLException{
        if (connection == null) {
            throw new SQLException("Database connection is not initialized.");
        }
        Statement statement = connection.createStatement();
        
        if (query.startsWith("SELECT")){
            //search query
            ResultSet resultset = statement.executeQuery(query);
            return resultset;
        } else {
            //insert, update, delete
            statement.executeUpdate(query);
        }
        return null;
    }
    public static Connection getConnection() {
        return connection;
    }
}