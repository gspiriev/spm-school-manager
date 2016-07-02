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
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author root_spiriev
 */
public class AnnualLessonDispositionAppOrm {

    public static void main(String[] args) throws Exception {
        AnnualLessonDispositionAppOrm annualDisposition = new AnnualLessonDispositionAppOrm();

        annualDisposition.run();
    }

    private void run() {
        
        
        Map.Entry<Integer, Integer> startEndYear = readUserInput();
        String[] props = {"Properties in persistence.xml"};

        Map<Student, WeeklySchedule> lessonDisposition = SpmBusinessProcess.instance
                .createAllStudentDisposition(
                        new JpaDatabaseConnection("manager1"),
                        props,
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
