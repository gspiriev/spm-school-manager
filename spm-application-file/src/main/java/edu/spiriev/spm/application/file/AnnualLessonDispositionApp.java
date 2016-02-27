/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.application.file;

import edu.spiriev.spm.business.logic.SpmBusinessProcess;
import edu.spiriev.spm.domain.model.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Scanner;
import edu.spiriev.spm.dao.file.*;
import java.util.AbstractMap;

/**
 * The main class which creates an annual disposition list, for all the students
 * in the Student resource file. When adding information about a student or
 * school dates in the resource files, use "/"(forth slash) for delimiter
 * between tokens (tokens are grades, student names, ability, musical piece
 * names, complexity etc.)
 *
 * @author root_spiriev
 */
public class AnnualLessonDispositionApp {

    public static void main(String[] args) throws Exception {
        AnnualLessonDispositionApp annualDisposition = new AnnualLessonDispositionApp();

        annualDisposition.run();
    }

    private void run() throws UnsupportedEncodingException {

        Map.Entry<Integer, Integer> startEndYear = readUserInput();
        
       
        MusicalPieceLoader mpLoader = new MusicalPieceLoader(new File(
                getClass().getResource("/Graded_Pieces_All_CSV.csv").getFile()
        ));
        
        SchoolDatesLoader sdLoader = new SchoolDatesLoader(new File(
                    getClass().getResource("/deprecatedDatesFirst.txt").getFile()));
        
        StudentLoader stLoader = new StudentLoader(new File(
                    getClass().getResource("/Students.txt").getFile()));

        Map<Student, WeeklySchedule> lessonDisposition = SpmBusinessProcess.instance
                .createAllStudentDisposition(
                        stLoader,
                        mpLoader,
                        sdLoader,
                        startEndYear.getValue(),
                        startEndYear.getKey());

        writeOutput(lessonDisposition);
        
    }

    private Map.Entry<Integer, Integer> readUserInput() {

        System.out.println("Enter start and end year, each followed by enter key");
        Scanner scan = new Scanner(System.in);
        final Integer startYear = Integer.parseInt(scan.nextLine());
        final Integer endYear = Integer.parseInt(scan.nextLine());

        return new AbstractMap.SimpleEntry<Integer, Integer>(startYear, endYear);

    }

    private void writeOutput(Map<Student, WeeklySchedule> lessonDisposition) {

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
