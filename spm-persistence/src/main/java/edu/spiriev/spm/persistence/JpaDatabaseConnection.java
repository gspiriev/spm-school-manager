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

/**
 *
 * @author root_spiriev
 */
public class JpaDatabaseConnection implements BusinessConnection {
    
    private final String managerName;
    private EntityManagerFactory emf;
    private EntityManager em;
    private List<Student> students;
    private List<MusicalPiece> musicalPieces;
    private List<Date> dates;
    
    public JpaDatabaseConnection(String managerName) {
        this.managerName = managerName;
    }
    
    public EntityManager getEm() {
        return em;
    }

    @Override
    public List<Student> getStudentList() {
        return students;
    }

    @Override
    public List<Date> getAllDates() {
        return dates;
    }

    @Override
    public List<MusicalPiece> getListOfPieces() {
        return musicalPieces;
    }
    
    @Override
    public void commitTransaction() {
        
        if (em.isOpen()) {
            em.getTransaction().commit();
            em.close();
            System.out.println("Entity Manager is now closed");
        } else {
          
        }
    }

    @Override
    public void makeConnection(String[] props) {
        
        this.emf = Persistence.createEntityManagerFactory(this.managerName);
        if(emf.isOpen()) {
            this.em = emf.createEntityManager();
            em.getTransaction().begin();
            AbstractDaoImpl aDao = new AbstractDaoImpl(em);
            
            aDao.setParser(new StudentsHibernateParser());
            aDao.setClassName("StudentEntity");
            this.students = aDao.loadAll();
            aDao.setParser(new MusicalPieceHibernateParser());
            aDao.setClassName("MusicalPiecesEntity");
            this.musicalPieces = aDao.loadAll();
            aDao.setParser(new DatesHibernateParser());
            aDao.setClassName("DatesEntity");
            this.dates = aDao.loadAll();
        } else {
            throw new IllegalArgumentException("Manager with given name does not exist or there are invalid connection properties." +
                                                "Check persistence.xml");
        }
        
    }
    
}
