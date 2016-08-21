/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.persistence;


import edu.spiriev.spm.dao.api.Parser;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author root_spiriev
 */
public class DatesHibernateParser implements Parser<Date, DatesEntity>{
    
    @Override
    public Date parse(String stringToParse) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Date parse(DatesEntity entity) {
        
        Date date = new Date();
        DateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        
        try {
            date = f.parse(entity.toString());
        } catch (ParseException e) {
            
            System.err.println("Parse exception: " + e);
        }
        
        return date;
    }
    
    @Override
    public boolean isNumeric(String str) {
        try {
            int i = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
}
