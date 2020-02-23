package se.ecutb.cai.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DataBase {

    private static String USERNAME = "root";
    private static String PASSWORD = "pellepelle12";
    private static final String URL = "jdbc:mysql://localhost:3306/world?&autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Europe/Berlin";


    public static Connection getConnection() throws SQLException {

            Connection connection = null;

            try{
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("Connected");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return DriverManager.getConnection(URL,USERNAME,PASSWORD);
        }

}
