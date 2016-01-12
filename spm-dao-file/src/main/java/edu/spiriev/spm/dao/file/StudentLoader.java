/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.file_daol;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import edu.spiriev.domain_model.*;
import edu.spiriev.file_daol.*;
import edu.spiriev.file_manipulation.*;

/**
 * Loads a list a students from a resource file
 * @author root_spiriev
 */
public class StudentLoader implements StudentIO {
    
    private final InputStream studentsFile;
    
    public StudentLoader() {
        
        ClassLoader cl = getClass().getClassLoader();
        this.studentsFile = cl.getResourceAsStream("Students");
    }
    
    @Override
    public ArrayList<Student> readAndCreateStudentsList() {
        
        ArrayList<Student> studentList = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(this.studentsFile))) {
       
            while(br.read() != -1) {
                String[] unformatted = br.readLine().split("/");
                studentList.add(new Student(unformatted[0],
                                            Grade.values()[Integer.parseInt(unformatted[1]) - 1],
                                            Integer.parseInt(unformatted[2])));
            }
            
        } catch (Exception e) {
            System.err.println("Students file not found");
        }
        
        return studentList;
    }
}
