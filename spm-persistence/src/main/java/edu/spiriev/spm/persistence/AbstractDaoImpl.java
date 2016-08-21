/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.persistence;


import edu.spiriev.spm.dao.api.AbstractDao;
import edu.spiriev.spm.dao.api.Parser;
import edu.spiriev.spm.domain.model.Grade;
import edu.spiriev.spm.domain.model.MusicalPiece;
import edu.spiriev.spm.domain.model.Student;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
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
        em.getTransaction().begin();
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
        em.getTransaction().begin();
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
    public void persistMusicalPiece(MusicalPiece mPiece) {
        em.getTransaction().begin();
        MusicalPiecesEntity musicalPieceEntity = new MusicalPiecesEntity();
        em.persist(musicalPieceEntity);
        em.flush();
        
        MusicalPieceGradeEntity musicalPieceGrade = new MusicalPieceGradeEntity();
        musicalPieceGrade.setGradeId(em.find(GradeEntity.class, mPiece.getGrade().ordinal() + 1));
        musicalPieceGrade.setMusicalPieceId(musicalPieceEntity);
        em.persist(musicalPieceGrade);
        em.flush();
        
        musicalPieceEntity.setPieceName(mPiece.getName());
        musicalPieceEntity.setComplexity(mPiece.getComplexity());
        musicalPieceEntity.setComposer(mPiece.getComposer());
        musicalPieceEntity.setMusicalPiecesGradeEntity(musicalPieceGrade);
    }

    @Override
    public void persistDate(Integer[] date) {
        em.getTransaction().begin();
        DatesEntity dateEntity = new DatesEntity();
        dateEntity.setDateDay(date[0]);
        dateEntity.setDateMonth(date[1]);
        dateEntity.setDateYear(date[2]);
        if (em.contains(dateEntity)) {
            em.merge(dateEntity);
        } else {
            em.persist(dateEntity);
        }
        
        em.flush();
    }
    
    @Override
    public Student findStudent(String studentName) {
        em.getTransaction().begin();
        Query query = em.createNamedQuery("StudentEntity.findByStudentName").setParameter("studentName", studentName);
        StudentEntity studentEntity = (StudentEntity)query.getSingleResult();
        Student foundStudent = new Student(studentEntity.getStudentName(),
                                           Grade.valueOf(studentEntity.getStudentGradeEntity().getGradeId().getGradeName()), 
                                           studentEntity.getAbility());
        return foundStudent;
    }

    @Override
    public void removeStudent(String studentName) {
        em.getTransaction().begin();
        Query query = em.createNamedQuery("StudentEntity.findByStudentName").setParameter("studentName", studentName);
        StudentEntity studentEntity = (StudentEntity)query.getSingleResult();
        if (em.contains(studentEntity)) {
            em.merge(studentEntity);
            em.remove(studentEntity);
        } else {
            em.remove(studentEntity);
        }
        em.flush();
    }

    @Override
    public void removeMusicalPiece(String musicalPiece) {
        em.getTransaction().begin();
        Query query = em.createNamedQuery("MusicalPiecesEntity.findByPieceName").setParameter("pieceName", musicalPiece);
        MusicalPiecesEntity mpEntity = (MusicalPiecesEntity)query.getSingleResult();
        if (em.contains(mpEntity)) {
            em.merge(mpEntity);
            em.remove(mpEntity);
        } else {
            em.remove(mpEntity);
        }
        em.flush();
    }

    @Override
    public void removeDate(Integer[] date) {
        em.getTransaction().begin();
        Query selectQuery = em.createNamedQuery("DatesEntity.findbyDayMonth").setParameter("dateDay", date[0])
                                                                             .setParameter("dateMonth", date[1]);
        DatesEntity entity = (DatesEntity)selectQuery.getSingleResult();
        if (em.contains(entity)) {
            em.merge(entity);
            em.remove(entity);
        } else {
            em.remove(entity);
        }
        em.flush();
    }

}
