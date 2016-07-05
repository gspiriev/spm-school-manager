/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.dao.file;

import edu.spiriev.spm.dao.api.Parser;
import edu.spiriev.spm.domain.model.Grade;
import edu.spiriev.spm.domain.model.Student;

/**
 *
 * @author root_spiriev
 */
public class StudentParser implements Parser<Student, Student>{

    @Override
    public Student parse(String stringToParse) {
        
        String[] unformatted = stringToParse.split("/");
        Student st = new Student(unformatted[0],
                                Grade.values()[Integer.parseInt(unformatted[1]) - 1],
                                Integer.parseInt(unformatted[2]));
    
        return st;
    }

    @Override
    public Student parse(Student entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
