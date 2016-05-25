/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.dao.database;

import edu.spiriev.spm.dao.api.BusinessConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author root_spiriev
 */
public class JdbcConnection implements BusinessConnection {
    
    private Connection conn;

    public Connection getConn() {
        
        return conn;
    }
    
    
    
    @Override
    public void makeConnection (String[] props) {
        
        
        try {
         
            Class.forName(props[0]);


            String databaseFile = this.getClass().getClassLoader().getResource(props[1]).getFile();

            this.conn = DriverManager.getConnection(props[2] + databaseFile);
            
            conn.setAutoCommit(false);
            
            System.out.println("Connected to database");
        
        } catch (ClassNotFoundException e) {
            System.err.println("Driver not found :" + e);
            
        } catch (SQLException e) {
            System.err.println("Database file may not be present: " + e);
            
        }
       
    }

    @Override
    public void commitTransaction() {
        
        try {
            if(this.conn != null) {
                    conn.commit();
                    conn.close();
                    
                    System.out.println("Disconnected from database");
                } else {
                conn.rollback();
            }
         } catch(SQLException e) {
                
            System.err.println("Invalid operation or missing database file: " + e);
        }
        
    }
    
    
    
}
