/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.persistence;


import edu.spiriev.spm.dao.api.StudentDao;
import edu.spiriev.spm.domain.model.Grade;

import edu.spiriev.spm.domain.model.Student;
import java.util.ArrayList;
import java.util.List;/**
 *
 * @author root_spiriev
 */
public class StudentsHibernateLoader implements StudentDao{
    
    protected final SQLiteLoader loadFromSQLite;

    public StudentsHibernateLoader(SQLiteLoader loadFromSQLite) {
        this.loadFromSQLite = loadFromSQLite;
    }
    
    @Override
    public List<Student> loadStudents(){
        List<Student> students = new ArrayList<>();
        List<StudentEntity> studentEntities = this.loadFromSQLite.getStudentEntities();
        
        Student student = null;
        for (StudentEntity studentEntity: studentEntities) {
            
            student = new Student(studentEntity.getStudentName(),
                                  Grade.valueOf(studentEntity.getStudentGradeEntity().getGradeId().getGradeName()),
                                  studentEntity.getAbility());
            students.add(student);
            
        }
        return students;
    }
    
}
