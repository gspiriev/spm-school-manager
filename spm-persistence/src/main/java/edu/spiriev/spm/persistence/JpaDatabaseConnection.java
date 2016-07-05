/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.persistence;

import edu.spiriev.spm.dao.api.BusinessConnection;
import edu.spiriev.spm.domain.model.MusicalPiece;
import edu.spiriev.spm.domain.model.Student;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import edu.spiriev.spm.dao.api.AbstractDao;

/**
 *
 * @author root_spiriev
 */
public class JpaDatabaseConnection implements BusinessConnection {
    
    private final String managerName;
    private EntityManagerFactory emf;
    private EntityManager em;
    
    private final AbstractDao<Student> studentDao;
    private final AbstractDao<MusicalPiece> musicalPiecesDao;
    private final AbstractDao<Date> datesDao;

    public JpaDatabaseConnection(String managerName) {
            this.managerName = managerName;
            this.emf = Persistence.createEntityManagerFactory(managerName);
            em = emf.createEntityManager();
            studentDao = new AbstractDaoImpl<>(em, new StudentsHibernateParser(), StudentEntity.class);
            musicalPiecesDao = new AbstractDaoImpl<>(em, new MusicalPieceHibernateParser(), MusicalPiecesEntity.class);
            datesDao = new AbstractDaoImpl<>(em, new DatesHibernateParser(), DatesEntity.class);
    }
	
    public EntityManager getEm() {
        return em;
    }

    @Override
    public AbstractDao<Student> getStudentDao() {
            return studentDao;
    }

    @Override
    public AbstractDao<Date> getDatesDao() {
            return datesDao;
    }

    @Override
    public AbstractDao<MusicalPiece> getMusicalPiecesDao() {
            return musicalPiecesDao;
    }
	
    @Override
    public void commitTransaction() {
        
        if (em.isOpen()) {
            em.getTransaction().commit();
            System.out.println("Entity Manager is now closed");
        } else {
          
        }
    }

    @Override
    public void close() {
        try {
            em.close();
            emf.close();
            System.out.println("Manager factory closed");
        } catch (Exception e) {
            System.err.println("Exception: " + e);
        }
    }
}
