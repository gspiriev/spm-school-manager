/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.dao.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author root_spiriev
 */
public class DatabaseConnection {
    
    public Connection getConnection () throws SQLException, ClassNotFoundException {
        
        Class.forName("org.sqlite.JDBC");
        
        Connection conn = null;
        Properties connProperties = new Properties();
        
        connProperties.put("user: ", "spiriev");
        connProperties.put("password: ", "spiriev_pass");
        
        ClassLoader cLoader = this.getClass().getClassLoader();
        String databaseUrl = cLoader.getResource("/spmDB").toString();
        
        conn = DriverManager.getConnection("jdbc:sqlite:" + databaseUrl, connProperties);
        
        System.out.println("Connected to database");
        
        return conn;
    }
    
}
