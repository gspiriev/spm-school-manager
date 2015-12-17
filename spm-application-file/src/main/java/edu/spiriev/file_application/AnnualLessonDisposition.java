/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.file_application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import edu.spiriev.domain_model.*;
import edu.spiriev.file_daol.*;



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
    
    public static void main(String[] args) throws Exception {
        AnnualLessonDisposition annualDisposition = new AnnualLessonDisposition();
        
        annualDisposition.run();
    }
    
    private void run() throws UnsupportedEncodingException{
        
        LinkedHashMap<Student, WeeklySchedule> lessonDisposition = new LinkedHashMap<>();
        ArrayList<Student> studentList = new StudentLoader().loadStudentsFromFile();
        for (Student st: studentList) {
            
           lessonDisposition.put(st, createSpecificSchedule(st));
        }
        
        ClassLoader cl = getClass().getClassLoader();
        
        File outFile = new File(this.getProgramPath(), "AnnualLessonDisposition.txt");
        
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
            
            while(lessonDisposition.size() > 0) {
                
                Map.Entry<Student, WeeklySchedule> dispositionSet =
                        lessonDisposition.entrySet().iterator().next();
                
                lessonDisposition.remove(dispositionSet.getKey());
                String keySt = dispositionSet.getKey().toString() + "\n" + "\n";
                String vaSt = dispositionSet.getValue().toString();
                writer.write(keySt);
                writer.write(vaSt);
                writer.flush();
            }
           
        } catch (Exception e) {
            
            System.out.println("No output file or path found");
        }
        
        System.out.println("Disposition file created in " + this.getProgramPath());
        //createSpecificSchedule(studentList.get(0));
        
    }
    
    public WeeklySchedule createSpecificSchedule(Student st) {
        
        Calendar endDate = Calendar.getInstance();
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
        //a list of study dates for the specific student's grade
        ArrayList<StudyDate> studyDatesList = new SchoolDatesLoader().
                                           createStudyDatesList(startYear,
                                                                endDate);
        //a list of musical pieces with appropriate grade, shuffled
        ArrayList<MusicalPiece> mpList = new MusicalPieceLoader().
                        readAndCreateGradedMusicalPieceList(st.getGrade());
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
    public int musicalPieceTransitionAlgorithm(ArrayList<MusicalPiece> mpList,
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
    
    public int indexCount(ArrayList<MusicalPiece> mpList,
                                         Student student,
                                         int index) {
        
       int count = 10 - (student.getAbility() - mpList.get(index).getComplexity());
        
       return count;
    }
    
    public int indexCounter(int count, int counter) {
        
        if (counter >= count) {
                
                counter = 0;
            } else {
                counter++;
        }
        
        return counter;
    }
  
    public String getProgramPath() throws UnsupportedEncodingException {
        URL url = this.getClass().getProtectionDomain().getCodeSource().getLocation();
        String jarPath = URLDecoder.decode(url.getFile(), "UTF-8");
        String parentPath = new File(jarPath).getParentFile().getPath();
        return parentPath;
    }
    
    public static <E> void show(Collection<E> coll) {
        for (E element: coll) {
            System.out.println(element);
        }
    }
    
}
