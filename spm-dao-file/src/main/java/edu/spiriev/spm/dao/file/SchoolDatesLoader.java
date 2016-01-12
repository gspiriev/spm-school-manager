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

    private ArrayList<Date> createDatesList(int startYear, Date endDate){

        ArrayList<Date> datesInAWeek = new ArrayList<>();
        ArrayList<Date> noSchoolDatesList = createNoSchoolDatesList();
        Calendar c = Calendar.getInstance();
        c.set(startYear, 8, 15, 00, 00, 00);
        c.set(Calendar.MILLISECOND, 0);

        while (c.getTime().before(endDate)) {

            while (true) {
                if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                      || c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {

                    c.add(Calendar.DAY_OF_MONTH, 1);
                } else {
                    for (Date deprecated : noSchoolDatesList) {
                        if (c.getTime().compareTo(deprecated) == 0) {
                            c.add(Calendar.DAY_OF_MONTH, 1);
                        }
                    }
                    datesInAWeek.add(c.getTime());
                    break;
                }
            }
            
            while (c.get(Calendar.DAY_OF_WEEK) != (Calendar.SATURDAY)) {
                
                if (c.getTime().compareTo(endDate) > 0) {

                    datesInAWeek.add(endDate);
                    break;
                }
                
                if (c.get(Calendar.DAY_OF_WEEK) == (Calendar.FRIDAY)) {
                    for (Date deprecated : noSchoolDatesList) {
                        if (c.getTime().compareTo(deprecated) == 0) {
                            c.add(Calendar.DAY_OF_MONTH, -1);
                        }
                    }
                    datesInAWeek.add(c.getTime());
                    c.add(Calendar.DAY_OF_MONTH, 3);
                    break;
                } else {
                    c.add(Calendar.DAY_OF_MONTH, 1);
                }
            }
        }

        return datesInAWeek;
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

    public ArrayList<StudyDate> createStudyDatesList(int startYear,
                                                            Calendar endDate){

        ArrayList<StudyDate> studyDatesList = new ArrayList<>();
        ArrayList<Date> allDates = createDatesList(startYear, endDate.getTime());

        for (int index = 1; index < allDates.size(); index += 2) {

            studyDatesList.add(new StudyDate(allDates.get(index - 1),
                  allDates.get(index)));
        }

        return studyDatesList;
    }
}
