/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Scanner;




/**
 * The main class which creates an annual disposition list, for all the students
 * in the Student resource file. When adding information about a student or 
 * school dates in the resource files, use "/"(forth slash) for delimiter
 * between tokens (tokens are grades, student names, ability, musical piece
 * names, complexity etc.)
 * @author root_spiriev
 */
public class AnnualLessonDisposition {
    
    private final Integer startYear;
    private final Integer endYear;
    

    public AnnualLessonDisposition() {
        
        System.out.println("Enter start and end year, each followed by enter key");
        Scanner scan = new Scanner(System.in);
        startYear = Integer.parseInt(scan.nextLine());
        endYear = Integer.parseInt(scan.nextLine());
        
    }
  
    /**
     * This method creates a specific to a certain student schedule according to
     * dates and musical pieces.
     * @param st - a student object
     * @param studyDatesList - a list of study dates for the specific student's grade
     * @param mpList - a list of musical pieces with appropriate grade, shuffled
     * @return 
     */
    public WeeklySchedule createSpecificSchedule(Student st, ArrayList<StudyDate> studyDatesList, 
                                                    ArrayList<MusicalPiece> mpList) {
        
        int index1 = 0; 
        int index2 = 1;
        int index3 = 2;
       
        int index1Count;
        int index2Count;
        int index3Count;
        
        int indexCounter1 = 0;
        int indexCounter2 = 0;
        int indexCounter3 = 0;
        
        //study date bounds for the different grades
        
        Collections.shuffle(mpList);
        
        LinkedHashMap<StudyDate, Lesson> schedule = new LinkedHashMap<>();
        //create an annual schedule by weeks for this student
        for (int index = 0; index < studyDatesList.size(); index++) {
            
            if (index1 == -1 || index2 == -1 || index3 == -1) {
                
                System.out.println("It seems that the ability of student " +
                                    st.getName() + " is too low or some pieces "
                                    + "have inapropriate complexity.");
                break;
            }
            if (index1 >= studyDatesList.size()) {
                index1 = studyDatesList.size() - 1;
            }
            if (index2 >= studyDatesList.size()) {
                index2 = studyDatesList.size() - 1;
            }
            if (index3 >= studyDatesList.size()) {
                index3 = studyDatesList.size() - 1;
            }
            
            index1Count = indexCount(mpList, st, index1);
            index2Count = indexCount(mpList, st, index2);
            index3Count = indexCount(mpList, st, index3);
            
            schedule.put(studyDatesList.get(index),
                         new Lesson(mpList.get(index1),
                                    mpList.get(index2),
                                    mpList.get(index3)));
            
            index1 = musicalPieceTransitionAlgorithm(mpList, st, index1, indexCounter1);
            indexCounter1 = indexCounter(index1Count, indexCounter1);
            
            index2 = musicalPieceTransitionAlgorithm(mpList, st, index2, indexCounter2);
            indexCounter2 = indexCounter(index2Count, indexCounter2);
            
            index3 = musicalPieceTransitionAlgorithm(mpList, st, index3, indexCounter3);
            indexCounter3 = indexCounter(index3Count, indexCounter3);
            
        }
        
        WeeklySchedule specificSchedule = new WeeklySchedule(schedule);
        
        return specificSchedule;
    }
    
    //this is the core algorithm for the specific study periods for the different
    //musical pieces depending on their complexity and the student's ability 
    private int musicalPieceTransitionAlgorithm(ArrayList<MusicalPiece> mpList,
                                                  Student student,
                                                  int index,
                                                  int count) {
        int learningCapability = 
                student.getAbility() - mpList.get(index).getComplexity();
        
        if(learningCapability < 0 &&
           index < mpList.size()) {
            
            index+=3;
            return -1;
        }
        if (index >= mpList.size() &&
            learningCapability < 0) {
            
            return -1;
        }
        if (index >= mpList.size()) {
            
            return index;
        }
        if (10 - learningCapability == count) {
            
            index+=3;
            return index;
        } else {
            
            return index;
        }
    }
    
    private int indexCount(ArrayList<MusicalPiece> mpList,
                                         Student student,
                                         int index) {
        
       int count = 10 - (student.getAbility() - mpList.get(index).getComplexity());
        
       return count;
    }
    
    private int indexCounter(int count, int counter) {
        
        if (counter >= count) {
                
                counter = 0;
            } else {
                counter++;
        }
        
        return counter;
    }
    
}
