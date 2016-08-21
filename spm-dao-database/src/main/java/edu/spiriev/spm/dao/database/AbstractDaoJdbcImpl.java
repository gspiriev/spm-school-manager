/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.dao.database;

import edu.spiriev.spm.dao.api.AbstractDao;

import edu.spiriev.spm.dao.api.Parser;
import edu.spiriev.spm.domain.model.MusicalPiece;
import edu.spiriev.spm.domain.model.Student;
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
 *
 * @author root_spiriev
 */
public class AbstractDaoJdbcImpl<T, E> implements AbstractDao{
   
    private final Connection conn;
    private final Parser<T, E> parser;
    private final String queryColumns;
    private final String queryTable;

    public AbstractDaoJdbcImpl(Connection conn, Parser<T, E> parser, String queryColumns, String queryTable) {
        this.conn = conn;
        this.parser = parser;
        this.queryColumns = queryColumns;
        this.queryTable = queryTable;
    }
    
    @Override
    public List<T> loadAll() {
        
        ArrayList<T> data = new ArrayList<>();
        
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT " +  queryColumns + " FROM " +  queryTable;
            String[] queryColumnsArray = queryColumns.split(",");
            ResultSet rs = stmt.executeQuery(sql);
            String dataString = "";
            while(rs.next()) {
                for (int i = 1; i <= queryColumnsArray.length; i++) {
                    if (i == queryColumnsArray.length) {
                        dataString += rs.getString(i);
                    } else {
                        dataString += (rs.getString(i) + "/");
                    }
                }
                T dataPiece = parser.parse(dataString);
                data.add(dataPiece);
                dataString = "";
            }
            
        } catch (SQLException e) {
            
            System.err.println("Invalid SQL query");
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public void persistStudent(Student student) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void persistMusicalPiece(MusicalPiece mPiece) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void persistDate(Integer[] date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeDate(Integer[] date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Student findStudent(String studentName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeStudent(String studentName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeMusicalPiece(String musicalPieceName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
    

