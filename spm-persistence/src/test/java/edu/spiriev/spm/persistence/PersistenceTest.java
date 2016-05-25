/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.persistence;

import edu.spiriev.spm.domain.model.Grade;
import edu.spiriev.spm.domain.model.Student;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Test;

/**
 *
 * @author root_spiriev
 */

public class PersistenceTest {
    
//    @Test
//    public void testStudentQuery() {
//        
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
//        EntityManager em = emf.createEntityManager();
//        StudentEntity se = em.find(StudentEntity.class, 11);
//        System.out.println(se);
//        em.close();
//        emf.close();
//    }
//    
//    @Test
//    public void testMusicalPieceQuery() {
//        
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
//        EntityManager em = emf.createEntityManager();
//        MusicalPiecesEntity se = em.find(MusicalPiecesEntity.class, 11);
//        System.out.println(se);
//        em.close();
//        emf.close();
//    }
    
//    @Test
//    public void testStudentInsert() {
//        Student st = new Student("Asen Petar", Grade.SEVENTH, 6);
//        
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
//        EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin();
//    
//       
//        StudentEntity stEntity = new StudentEntity();
//        em.persist(stEntity);
//        em.flush();
//        
//        StudentGradeEntity stGrade = new StudentGradeEntity();
//        stGrade.setGradeId(em.find(GradeEntity.class, st.getGrade().ordinal() + 1));
//        stGrade.setStudentId(stEntity);
//        em.persist(stGrade);
//        em.flush();
//        
//        
//        
//        stEntity.setStudentName(st.getName());
//        stEntity.setAbility(st.getAbility());
//        stEntity.setStudentGradeEntity(stGrade);
//        em.getTransaction().rollback();
//        em.close();
//        emf.close();
//    }
    
//    @Test
//    public void testStudentRemove() {
//       
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
//        EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin();
//        
//        StudentEntity stEntityRedundant = em.find(StudentEntity.class, 12);
//        em.remove(stEntityRedundant);
//        em.getTransaction().commit();
//        em.close();
//        emf.close();
//    }
    
}
