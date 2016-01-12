/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.application.file;

import edu.spiriev.spm.domain.model.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import edu.spiriev.spm.dao.file.*;
import java.util.Calendar;



/**
 * The main class which creates an annual disposition list, for all the students
 * in the Student resource file. When adding information about a student or 
 * school dates in the resource files, use "/"(forth slash) for delimiter
 * between tokens (tokens are grades, student names, ability, musical piece
 * names, complexity etc.)
 * @author root_spiriev
 */
public class AnnualLessonDispositionApp {
    
    private final Integer startYear;
    private final Integer endYear;
    

    public AnnualLessonDispositionApp() {
        
        System.out.println("Enter start and end year, each followed by enter key");
        Scanner scan = new Scanner(System.in);
        startYear = Integer.parseInt(scan.nextLine());
        endYear = Integer.parseInt(scan.nextLine());
        
    }
    
    public static void main(String[] args) throws Exception {
        AnnualLessonDispositionApp annualDisposition = new AnnualLessonDispositionApp();
        
        annualDisposition.run();
    }
    
    private void run() throws UnsupportedEncodingException{
        
        LinkedHashMap<Student, WeeklySchedule> lessonDisposition = createAllStudentDisposition();
        
        File outFile = new File(this.getProgramPath(), "AnnualLessonDisposition.txt");
        
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
            
            while(lessonDisposition.size() > 0) {
                
                Map.Entry<Student, WeeklySchedule> dispositionSetEntry =
                        lessonDisposition.entrySet().iterator().next();
                
                lessonDisposition.remove(dispositionSetEntry.getKey());
                String keySt = dispositionSetEntry.getKey().toString() + "\n" + "\n";
                String vaSt = dispositionSetEntry.getValue().toString();
                writer.write(keySt);
                writer.write(vaSt);
                writer.flush();
            }
           
        } catch (Exception e) {
            
            System.out.println("No output file or path found");
        }
        
        System.out.println("Disposition file created in " + this.getProgramPath());
        
    }
    
    private LinkedHashMap<Student, WeeklySchedule> createAllStudentDisposition() {
        
        LinkedHashMap<Student, WeeklySchedule> lessonDisposition = new LinkedHashMap<>();
        ArrayList<Student> studentList = new StudentLoader().readAndCreateStudentsList();
        ArrayList<StudyDate> studentSpecificDates;
        ArrayList<MusicalPiece> listOfPieces; 
        for (Student st: studentList) {
            
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
            
           studentSpecificDates = new SchoolDatesLoader().createStudyDatesList(startYear, endDate);
           listOfPieces = new MusicalPieceLoader().readAndCreateGradedMusicalPieceList(st.getGrade());
           
           WeeklySchedule specificSchedule = new AnnualLessonDisposition().createSpecificSchedule(st,
                                                                            studentSpecificDates,
                                                                            listOfPieces);
           lessonDisposition.put(st, specificSchedule);
        }
        return lessonDisposition;
    }
  
    private String getProgramPath() throws UnsupportedEncodingException {
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
