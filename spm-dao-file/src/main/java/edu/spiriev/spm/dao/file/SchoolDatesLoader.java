/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.dao.file;

import edu.spiriev.spm.dao.api.DatesIO;
import edu.spiriev.spm.domain.model.StudyDate;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Loads school dates from the school dates resource file.
 * @author root_spiriev
 */
public class SchoolDatesLoader implements DatesIO{

    private final InputStream deprecatedDatesFile;

    public SchoolDatesLoader() {
        ClassLoader cl = getClass().getClassLoader();
        this.deprecatedDatesFile = cl.getResourceAsStream("deprecatedDatesFirst.txt");
    }

    
    
    @Override
    public ArrayList<Date> createNoSchoolDatesList(){

        ArrayList<Date> noSchoolDateList = new ArrayList<>();
        DateFormat f = new SimpleDateFormat("dd/MM/yyyy");

        try (BufferedReader datesReader = new BufferedReader(new InputStreamReader(deprecatedDatesFile))) {

            while (datesReader.ready()) {
                noSchoolDateList.add(f.parse(datesReader.readLine()));
            }
        } catch (Exception e) {
            System.err.println("No-school-dates file not found");
        }
        return noSchoolDateList;
    }

    
}
