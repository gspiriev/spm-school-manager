/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.business.logic;

import edu.spiriev.spm.domain.model.Student;

import java.util.ArrayList;

import edu.spiriev.spm.dao.api.*;
import edu.spiriev.spm.domain.model.AnnualLessonDisposition;
import edu.spiriev.spm.domain.model.StudyDate;
import edu.spiriev.spm.domain.model.WeeklySchedule;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author root_spiriev
 */
public class SpmBusinessProcess {
    
    public static final SpmBusinessProcess instance = new SpmBusinessProcess();
    
    public Map<Student, WeeklySchedule> createAllStudentDisposition(BusinessConnection bc, String[] props, Integer endYear, Integer startYear) {
        
        Map<Student, WeeklySchedule> lessonDisposition = new LinkedHashMap<>();
        
        bc.makeConnection(props);
        
        bc.commitTransaction();
        
        for (Student st: bc.getStudentList()) {
            
            Calendar endDate = Calendar.getInstance();
            switch(st.getGrade()) {
            
                case FIRST:
                case SECOND:
                case THIRD: 
                case FOURTH:
                    endDate.set(endYear, 5, 15);
                    break;
                case TWELVETH:
                    endDate.set(endYear, 4, 24);
                    break;
                default:
                    endDate.set(endYear, 5, 30);
                    break;
            }
            
           List<StudyDate> studentSpecificDates = createStudyDatesList(startYear, endDate, bc.getAllDates());
           
           
           WeeklySchedule specificSchedule = new AnnualLessonDisposition().createSpecificSchedule(st,
                                                                            studentSpecificDates,
                                                                            bc.getListOfPieces());
           lessonDisposition.put(st, specificSchedule);
        }
        return lessonDisposition;
    }
    
    public List<StudyDate> createStudyDatesList(int startYear,
                                                            Calendar endDate, List<Date> allDates){

        List<StudyDate> studyDatesList = new ArrayList<>();
        List<Date> specificDates = createDatesList(startYear, endDate.getTime(), allDates);

        for (int index = 1; index < specificDates.size(); index += 2) {

            studyDatesList.add(new StudyDate(specificDates.get(index - 1),
                  specificDates.get(index)));
        }

        return studyDatesList;
    }
    
    private List<Date> createDatesList(int startYear, Date endDate, List<Date> allDates){

        List<Date> datesInAWeek = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        c.set(startYear, 8, 15, 00, 00, 00);
        c.set(Calendar.MILLISECOND, 0);

        while (c.getTime().before(endDate)) {

            while (true) {
                if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                      || c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {

                    c.add(Calendar.DAY_OF_MONTH, 1);
                } else {
                    for (Date deprecated : allDates) {
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
                    for (Date deprecated : allDates) {
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
   
}
