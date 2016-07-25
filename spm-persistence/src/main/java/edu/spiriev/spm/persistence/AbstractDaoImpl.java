/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.persistence;


import edu.spiriev.spm.dao.api.AbstractDao;
import edu.spiriev.spm.dao.api.Parser;
import edu.spiriev.spm.domain.model.Grade;
import edu.spiriev.spm.domain.model.Student;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.Query;

/**
 *
 * @author root_spiriev
 */
public class AbstractDaoImpl<T, E> implements AbstractDao{
    
    private final EntityManager em;
    private final Parser<T, E> parser;
    private final Class<E> entityType;

    public AbstractDaoImpl(EntityManager em, Parser parser, Class<E> entityType) {
            this.em = em;
            this.parser = parser;
            this.entityType = entityType;
    }
 
    @Override
    public List<T> loadAll() {
        
        Query query = em.createNamedQuery(entityType.getSimpleName() + ".findAll");
        List<E> entities = query.getResultList();
        
        List<T> data = new ArrayList<>();
        
        entities.stream().forEach((entity) -> {
            data.add(parser.parse(entity));
        });
        
        return data;
    }

    @Override
    public void persistStudent(Student student) {
        
        StudentEntity stEntity = new StudentEntity();
        em.persist(stEntity);
        em.flush();
        
        StudentGradeEntity stGrade = new StudentGradeEntity();
        stGrade.setGradeId(em.find(GradeEntity.class, student.getGrade().ordinal() + 1));
        stGrade.setStudentId(stEntity);
        em.persist(stGrade);
        em.flush();
        
        stEntity.setStudentName(student.getName());
        stEntity.setAbility(student.getAbility());
        stEntity.setStudentGradeEntity(stGrade);
    }
    
    @Override
    public Student findStudent(String studentName) {
        Query query = em.createNamedQuery("StudentEntity.findByStudentName").setParameter("studentName", studentName);
        StudentEntity studentEntity = (StudentEntity)query.getSingleResult();
        Student foundStudent = new Student(studentEntity.getStudentName(),
                                           Grade.valueOf(studentEntity.getStudentGradeEntity().getGradeId().getGradeName()), 
                                           studentEntity.getAbility());
        return foundStudent;
    }

    @Override
    public void removeStudent(Integer studentId) {
        StudentEntity studentEntity = em.find(StudentEntity.class, studentId);
        em.remove(studentEntity);
    }

    
    
    
}
