/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.dao.database;



import edu.spiriev.spm.dao.api.Parser;
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
public class SchoolDatesDatabaseParser implements Parser<Date, Date>{

    @Override
    public Date parse(String stringToParse) {
                Date date = null;
                DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    date = sdf.parse(stringToParse);
                } catch(ParseException pe) {
                    System.err.println("Cannot parse date");
                }
                
                return date;
    }

    @Override
    public Date parse(Date entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isNumeric(String str) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
