package com.jspider.product_management.jdbc.curd.conection;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionMyDb {
    public static Connection getConnectionMyDb() {
        try {
            // Step: 1 load/register driver

            Driver driver = new Driver(); // create Object of Driver class.
            // pass a driver object in DriverManager class, registerDriver() method.
            DriverManager.registerDriver(driver);

            // Step: 2 Connection

            String url= "jdbc:mysql://localhost:3306/my_db_Name";
            String user_name = "root";
            String passwordString = "root";
            // pass and return an Url, Username, Password in DriverManager class, getConnection(url,username,password) method.
            return DriverManager.getConnection(url,user_name,passwordString);

            // Step: 3 Crete pre-Statement in DAO package;
            // Step: 4 Execute Sql Query in DAO Package;
            // Step: 5 Closes Connection in DAO Package;
        }
        catch (Exception e){
            System.out.println("Connection Exception : "+ e.getMessage());
            return null;
        }
    }
}
