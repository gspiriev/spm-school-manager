/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.dao.file;

import edu.spiriev.spm.dao.api.Parser;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author root_spiriev
 */
public class SchoolDatesParser implements Parser<Date, Date>{

    @Override
    public Date parse(String stringToParse) {
        
        Date date = new Date();
        DateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        
        try {
            date = f.parse(stringToParse);
        } catch (ParseException e) {
            
            System.err.println("Parse exception: " + e);
        }
        
        return date;
    }

    @Override
    public Date parse(Date entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
