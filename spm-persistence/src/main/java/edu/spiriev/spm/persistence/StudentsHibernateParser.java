/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.persistence;

import edu.spiriev.spm.dao.api.Parser;
import edu.spiriev.spm.domain.model.Grade;
import edu.spiriev.spm.domain.model.Student;

/**
 *
 * @author root_spiriev
 */
public class StudentsHibernateParser implements Parser<Student, StudentEntity> {

    @Override
    public Student parse(String stringToParse) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Student parse(StudentEntity entity) {
        
        Student student = new Student(entity.getStudentName(),
                        Grade.valueOf(entity.getStudentGradeEntity().getGradeId().getGradeName()),
                        entity.getAbility());
        
        return student;
    }
    
    @Override
    public boolean isNumeric(String str) {
        try {
            int i = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
