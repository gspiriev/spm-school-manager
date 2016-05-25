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
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author root_spiriev
 */
public class StudentsHibernateLoader implements StudentDao {

    private final EntityManager em;

    public StudentsHibernateLoader(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Student> loadStudents() {

        TypedQuery<StudentEntity> studentsQuery = em.createNamedQuery("StudentEntity.findAll", StudentEntity.class);
        List<StudentEntity> studentEntities = studentsQuery.getResultList();

        List<Student> students = new ArrayList<>();

        Student student = null;
        for (StudentEntity studentEntity : studentEntities) {

            student = new Student(studentEntity.getStudentName(),
                    Grade.valueOf(studentEntity.getStudentGradeEntity().getGradeId().getGradeName()),
                    studentEntity.getAbility());
            students.add(student);

        }
        return students;
    }

 

}
