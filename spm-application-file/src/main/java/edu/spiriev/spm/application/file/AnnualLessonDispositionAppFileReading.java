/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.application.file;

import edu.spiriev.spm.business.logic.SpmBusinessProcess;
import edu.spiriev.spm.dao.file.MusicalPieceLoader;
import edu.spiriev.spm.dao.file.SchoolDatesLoader;
import edu.spiriev.spm.dao.file.StudentLoader;
import edu.spiriev.spm.domain.model.Student;
import edu.spiriev.spm.domain.model.WeeklySchedule;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.UnsupportedEncodingException;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author root_spiriev
 */
public class AnnualLessonDispositionAppFileReading {
    
    public static void main(String[] args) throws Exception {
        AnnualLessonDispositionAppFileReading annualDisposition = new AnnualLessonDispositionAppFileReading();

        annualDisposition.run();
    }

    private void run() throws UnsupportedEncodingException {
        
        Map.Entry<Integer, Integer> startEndYear = readUserInput();
     
        ClassLoader cl = AnnualLessonDispositionAppFileReading.class.getClassLoader();
        File[] resources = {new File(cl.getResource("Students.txt").getFile()),
                            new File(cl.getResource("Graded_Pieces_All_CSV.csv").getFile()),
                            new File(cl.getResource("deprecatedDatesFirst.txt").getFile())};

        Map<Student, WeeklySchedule> lessonDisposition = SpmBusinessProcess.instance
                .createAllStudentDisposition(
                        new StudentLoader(resources[0]),
                        new MusicalPieceLoader(resources[1]),
                        new SchoolDatesLoader(resources[2]),
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
