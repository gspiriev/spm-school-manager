/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.application.file;

import edu.spiriev.spm.business.logic.SpmBusinessProcess;
import edu.spiriev.spm.persistence.*;
import edu.spiriev.spm.domain.model.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.AbstractMap;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author root_spiriev
 */
public class AnnualLessonDispositionAppOrm {
    
    private final JpaDatabaseConnection bc = new JpaDatabaseConnection("manager1");
    
    public void persistStudent(Student st) {
        SpmBusinessProcess.instance.insertStudent(bc, st);
    }
    
    public void persistMusicalPiece(MusicalPiece mPiece) {
        SpmBusinessProcess.instance.insertMusicalPiece(bc, mPiece);
    }
    
    public void persistDate(Integer[] date) {
        SpmBusinessProcess.instance.insertDate(bc, date);
    }
    
    public void removeDate(Integer[] date) {
        SpmBusinessProcess.instance.removeDate(bc, date);
    }
    
    public void removeStudent(String studentName) {
        SpmBusinessProcess.instance.removeStudent(bc, studentName);
    }
    
    public void removeMusicalPiece(String musicalPieceName) {
        SpmBusinessProcess.instance.removeMusicalPiece(bc, musicalPieceName);
    }
    
    
    public List<Date> getAllDates() {
        
        List<Date> allDates = SpmBusinessProcess.instance.getDates(bc);
        return allDates;
    }
    
    public List<MusicalPiece> getAllMusicalPieces() {
        
        List<MusicalPiece> musicalPieces = SpmBusinessProcess.instance.getMusicalPieces(bc);
        return musicalPieces;
    }
    
    public List<Student> getAllStudents() {
        
        List<Student> allStudents = SpmBusinessProcess.instance.getStudents(bc);
        return allStudents;
    }

    public void createStudentDisposition(Map.Entry<Integer, Integer> startEndYear) {
        
        Map<Student, WeeklySchedule> lessonDisposition = SpmBusinessProcess.instance
                .createAllStudentDisposition(
                        bc,
                        startEndYear.getValue(),
                        startEndYear.getKey());
        
        writeOutput(lessonDisposition);

    }

    private Map.Entry<Integer, Integer> readUserInput() {

        System.out.println("Enter start year for the disposition,followed by enter key");
        Scanner scan = new Scanner(System.in);
        final Integer startYear = Integer.parseInt(scan.nextLine());
        final Integer endYear = startYear + 1;
        return new AbstractMap.SimpleEntry<Integer, Integer>(startYear, endYear);

    }

    public void writeOutput(Map<Student, WeeklySchedule> lessonDisposition) {

        File outFile = new File(".", "AnnualLessonDisposition.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {

            while (lessonDisposition.size() > 0) {

                Map.Entry<Student, WeeklySchedule> dispositionSetEntry
                        = lessonDisposition.entrySet().iterator().next();

                lessonDisposition.remove(dispositionSetEntry.getKey());
                String keySt = dispositionSetEntry.getKey().toString() + "\n" + "\n";
                String vaSt = dispositionSetEntry.getValue().toString();
                writer.write(keySt);
                writer.write(vaSt);
                writer.flush();
            }

            System.out.println("Disposition file created in " + System.getProperty("user.dir"));
        } catch (Exception e) {

            System.out.println("No output file or path found");
        }
    }

}
