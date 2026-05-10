package config;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/university_db";

    private static final String USER = "root";

    private static final String PASSWORD = "";

    public static Connection getConnection() {

        try {

            Connection connection =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("Connected Successfully");

            return connection;

        } catch (Exception e) {

            System.out.println("Connection Failed");

            return null;
        }
    }
}