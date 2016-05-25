/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.persistence;

import edu.spiriev.spm.dao.api.AbstractDao;
import edu.spiriev.spm.domain.model.Grade;
import edu.spiriev.spm.domain.model.MusicalPiece;
import edu.spiriev.spm.domain.model.Student;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author root_spiriev
 */
public class AbstractDaoImpl implements AbstractDao{
    
    private List<Student> students;
    private List<MusicalPiece> mPieces;
    private List<Date> dates;
    private final EntityManager em;

    public AbstractDaoImpl(EntityManager em) {
        this.em = em;
    }
    
    
    @Override
    public List<Student> getStudents() {
        return students;
    }
    
    @Override
    public List<MusicalPiece> getMusicalPieces() {
        return mPieces;
    }
    
    @Override
    public List<Date> getDates() {
        return dates;
    }
    
    
    @Override
    public void loadAll() {
        this.students = new StudentsHibernateLoader(this.em).loadStudents();
        this.dates = new DatesHibernateLoader(this.em).loadDates();
        this.mPieces = new MusicalPieceHibernateLoader(this.em).loadMusicalPieces();
        
    }
    
    

}
