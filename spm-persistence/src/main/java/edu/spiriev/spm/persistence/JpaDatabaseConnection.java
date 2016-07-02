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

	public JpaDatabaseConnection(String managerName, EntityManagerFactory emf) {
		this.managerName = managerName;
		this.emf = emf;
		this.em = em;
		this.studentDao = studentDao;
		this.musicalPiecesDao = musicalPiecesDao;
		this.datesDao = datesDao;
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
            em.close();
            System.out.println("Entity Manager is now closed");
        } else {
          
        }
    }

	@Override
	public void close() throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
	
	/**
	 * 1. Creates entity manager factory.
	 * 2. Creates entity manager.
	 * 3. Begins transaction. 
	 * 4. Loads all data in every list of business connection.
	 * @param props 
	 */
    public void makeConnection(String[] props) {
        
        this.emf = Persistence.createEntityManagerFactory(this.managerName);
        if(emf.isOpen()) {
            this.em = emf.createEntityManager();
            em.getTransaction().begin();
            AbstractDao<Student> studentDao = new AbstractDaoImpl<>(em, new StudentsHibernateParser(), StudentEntity.class);
            
//            aDao.setParser();
//            aDao.setClassName("StudentEntity");
            this.students = aDao.loadAll();
//            aDao.setParser(new MusicalPieceHibernateParser());
//            aDao.setClassName("MusicalPiecesEntity");
            this.musicalPieces = aDao.loadAll();
//            aDao.setParser(new DatesHibernateParser());
//            aDao.setClassName("DatesEntity");
            this.dates = aDao.loadAll();
        } else {
            throw new IllegalArgumentException("Manager with given name does not exist or there are invalid connection properties." +
                                                "Check persistence.xml");
        }
        
    }
    
}
