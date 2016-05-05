/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.dao.database;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author root_spiriev
 */
public class DatabaseConnection {
    
    public Connection getConnection () throws SQLException, ClassNotFoundException{
        
        String[] props = null;
        props = getProperties();
        
        Class.forName(props[0]);

        Connection conn = null;

        String databaseFile = this.getClass().getClassLoader().getResource(props[1]).getFile();

        conn = DriverManager.getConnection(props[2] + databaseFile);

        System.out.println("Connected to database");

        return conn;
        
    }
    
    private String[] getProperties() {
        
        String[] resultString = new String[3];
        
        try {
            InputStream inputStream = null;

            Properties props = new Properties();
            String propFileName = "application.properties";

            inputStream = this.getClass().getClassLoader().getResourceAsStream(propFileName);
        
       
            if(inputStream != null) {

                props.load(inputStream);
            } else {

                throw new FileNotFoundException("Properties file " + propFileName + "is not on the classpath");
            }

            resultString[0] = props.getProperty("driverName");
            resultString[1] = props.getProperty("dbName");
            resultString[2] = props.getProperty("connAndEngine");
            
            inputStream.close();
        } catch(IOException e) {
            System.err.println("Exception: " + e);
        } 
        
        return resultString;
    }
    
}
