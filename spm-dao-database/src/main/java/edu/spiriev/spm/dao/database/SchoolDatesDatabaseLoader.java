/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.dao.database;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Loads school dates from the school dates resource file.
 * @author root_spiriev
 */
public class SchoolDatesDatabaseLoader{
//    
//    private final Connection conn;
//
//    public SchoolDatesDatabaseLoader(Connection conn) {
//        this.conn = conn;
//    }
//    
//    
//    @Override
//    public List<Date> loadDates(){
//
//        ArrayList<Date> noSchoolDateList = new ArrayList<>();
//        
//        try {
//            
//            Statement stmt = conn.createStatement();
//            String sql = "SELECT date_day, date_month, date_year FROM dates";
//            ResultSet rs = stmt.executeQuery(sql);
//          
//            while(rs.next()) {
//               
//                String dateString = rs.getString(1) + "/" + rs.getString(2) + "/" + rs.getString(3);
//                
//                DateFormat dFormat = new SimpleDateFormat("dd/MM/yyyy");
//                
//                try{
//                    
//                    Date date = dFormat.parse(dateString);
//                    noSchoolDateList.add(date);
//                } catch (ParseException e) {
//                    
//                    e.printStackTrace();
//                }
//                
//            }
//            
//        } catch (SQLException e) {
//            
//            System.err.println("Invalid SQL query");
//            e.printStackTrace();
//        }
//        
//        return noSchoolDateList;
//    }

    
}
