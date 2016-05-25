/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.persistence;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Test;

/**
 *
 * @author root_spiriev
 */
public class PopulateHSQLDB {
    
//    @Test
//    public void populateHsql() {
//        DBLoader loader = new DBLoader(new ArrayList<>(),
//                                            new ArrayList<>(),
//                                            new ArrayList<>(),
//                                            new ArrayList<>(),
//                                            new ArrayList<>(),
//                                            new ArrayList<>());
//        loader.loadFromDB("manager1");
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager2");
//        EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin();
////        em.persist(loader.getDatesEntities().iterator().next());
////        em.flush();
////        em.getTransaction().commit();
//        loader.getGradeEntities().stream().forEach((grade) -> {
//            em.persist(grade);
//        });
//        loader.getDatesEntities().stream().forEach((date) -> {
//            em.persist(date);
//        });
//        loader.getMusicalPieceEntities().stream().forEach((mPiece) -> {
//            em.persist(mPiece);
//        });
//        loader.getStudentEntities().stream().forEach((student) -> {
//            em.persist(student);
//        });
//        loader.getMusicalPieceGradeEntities().stream().forEach((musicalPieceGrade) -> {
//            em.persist(musicalPieceGrade);
//        });
//        loader.getStudentGradeEntities().stream().forEach((studentGrade) -> {
//            em.persist(studentGrade);
//        });
//        
//        em.flush();
//        em.getTransaction().commit();
//        em.close();
//        emf.close();
//    }
    
}
