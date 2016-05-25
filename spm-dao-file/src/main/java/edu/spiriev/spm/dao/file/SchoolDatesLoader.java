/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.dao.file;

import edu.spiriev.spm.dao.api.SchoolHolidaysDao;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Loads school dates from the school dates resource file.
 * @author root_spiriev
 */
public class SchoolDatesLoader implements SchoolHolidaysDao{

    private final File datesFile;

    public SchoolDatesLoader(File datesFile) {
//        ClassLoader cl = getClass().getClassLoader();
//        this.deprecatedDatesFile = cl.getResourceAsStream("deprecatedDatesFirst.txt");
        this.datesFile = datesFile;
    }

    
    
    @Override
    public List<Date> loadDates(){

        ArrayList<Date> noSchoolDateList = new ArrayList<>();
        DateFormat f = new SimpleDateFormat("dd/MM/yyyy");

        try (BufferedReader datesReader = new BufferedReader(new FileReader(datesFile))) {

            while (datesReader.ready()) {
                noSchoolDateList.add(f.parse(datesReader.readLine()));
            }
        } catch (Exception e) {
            System.err.println("No-school-dates file not found");
        }
        return noSchoolDateList;
    }

    
}
