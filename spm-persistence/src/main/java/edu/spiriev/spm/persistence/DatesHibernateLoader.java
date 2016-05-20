/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.persistence;

import edu.spiriev.spm.dao.api.SchoolHolidaysDao;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 *
 * @author root_spiriev
 */
public class DatesHibernateLoader implements SchoolHolidaysDao  {
    
    protected final SQLiteLoader loadFromSQLite;
    
    public DatesHibernateLoader(SQLiteLoader loadFromSQLite1) {
        
        this.loadFromSQLite = loadFromSQLite1;
    }
    
    @Override
    public List<Date> loadDates() {
        List<Date> datesList = new ArrayList<>();
        List<DatesEntity> datesEntities = this.loadFromSQLite.getDatesEntities();
        
        DateFormat dFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date nextDate = null;
        for (DatesEntity dateEntity: datesEntities) {
            try {
                nextDate = dFormat.parse(dateEntity.toString());
                
            } catch(Exception e) {
                System.out.println("Cannot parse date, incorrect format");
            }
            datesList.add(nextDate);
        }
        
        return datesList;
    }
    
}
