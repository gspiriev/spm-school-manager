/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.dao.file;


import edu.spiriev.spm.dao.api.StudentDao;
import edu.spiriev.spm.domain.model.Grade;
import edu.spiriev.spm.domain.model.Student;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Loads a list a students from a resource file
 * @author root_spiriev
 */
public class StudentLoader implements StudentDao {
    
    private final File studentsFile;
    
    public StudentLoader(File studentsFile) {
//        
//        ClassLoader cl = getClass().getClassLoader();
//        this.studentsFile = cl.getResourceAsStream("Students");
        this.studentsFile = studentsFile;
    }
    
    @Override
    public List<Student> loadStudents() {
        
        ArrayList<Student> studentList = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(studentsFile))) {
       
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
