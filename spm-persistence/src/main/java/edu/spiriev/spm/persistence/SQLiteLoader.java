/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.persistence;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author root_spiriev
 */
public class SQLiteLoader {
    
    protected List<DatesEntity> datesEntities;
    protected List<GradeEntity> gradeEntities;
    protected List<MusicalPiecesEntity> musicalPieceEntities;
    protected List<StudentEntity> studentEntities;
    protected List<MusicalPieceGradeEntity> musicalPieceGradeEntities;
    protected List<StudentGradeEntity> studentGradeEntities;

    public SQLiteLoader(ArrayList<DatesEntity> datesEntities, ArrayList<GradeEntity> gradeEntities, 
                          ArrayList<MusicalPiecesEntity> musicalPieceEntities,
                          ArrayList<StudentEntity> studentEntities, ArrayList<MusicalPieceGradeEntity> musicalPieceGradeEntities,
                          ArrayList<StudentGradeEntity> studentGradeEntities) {
        this.datesEntities = datesEntities;
        this.gradeEntities = gradeEntities;
        this.musicalPieceEntities = musicalPieceEntities;
        this.studentEntities = studentEntities;
        this.musicalPieceGradeEntities = musicalPieceGradeEntities;
        this.studentGradeEntities = studentGradeEntities;
    }

    public List<DatesEntity> getDatesEntities() {
        return datesEntities;
    }

    public List<GradeEntity> getGradeEntities() {
        return gradeEntities;
    }

    public List<MusicalPiecesEntity> getMusicalPieceEntities() {
        return musicalPieceEntities;
    }

    public List<StudentEntity> getStudentEntities() {
        return studentEntities;
    }

    public List<MusicalPieceGradeEntity> getMusicalPieceGradeEntities() {
        return musicalPieceGradeEntities;
    }

    public List<StudentGradeEntity> getStudentGradeEntities() {
        return studentGradeEntities;
    }
    
    public void loadFromSQLite() {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<DatesEntity> datesQuery = em.createNamedQuery("DatesEntity.findAll", DatesEntity.class);
        this.datesEntities = datesQuery.getResultList();
        TypedQuery<GradeEntity> gradesQuery = em.createNamedQuery("GradeEntity.findAll", GradeEntity.class);
        this.gradeEntities = gradesQuery.getResultList();
        TypedQuery<MusicalPiecesEntity> musicalPiecesQuery = em.createNamedQuery("MusicalPiecesEntity.findAll", MusicalPiecesEntity.class);
        this.musicalPieceEntities = musicalPiecesQuery.getResultList();
        TypedQuery<StudentEntity> studentsQuery = em.createNamedQuery("StudentEntity.findAll", StudentEntity.class);
        this.studentEntities = studentsQuery.getResultList();
        TypedQuery<MusicalPieceGradeEntity> musicalPieceGradesQuery = em.createNamedQuery("MusicalPieceGradeEntity.findAll", MusicalPieceGradeEntity.class);
        this.musicalPieceGradeEntities = musicalPieceGradesQuery.getResultList();
        TypedQuery<StudentGradeEntity> studentGradesQuery = em.createNamedQuery("StudentGradeEntity.findAll", StudentGradeEntity.class);
        this.studentGradeEntities = studentGradesQuery.getResultList();
        
        em.close();
        emf.close();
    }
    
    
}
