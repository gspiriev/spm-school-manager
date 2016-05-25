/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.dao.database;

import edu.spiriev.spm.dao.api.AbstractDao;
import edu.spiriev.spm.domain.model.MusicalPiece;
import edu.spiriev.spm.domain.model.Student;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author root_spiriev
 */
public class AbstractDaoJdbcImpl implements AbstractDao{
    
    private List<Student> students;
    private List<MusicalPiece> mPieces;
    private List<Date> dates;
    private final Connection conn;

    public AbstractDaoJdbcImpl(Connection conn) {
        this.conn = conn;
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
        this.students = new StudentDatabaseLoader(this.conn).loadStudents();
        this.dates = new SchoolDatesDatabaseLoader(this.conn).loadDates();
        this.mPieces = new MusicalPieceDatabaseLoader(this.conn).loadMusicalPieces();
        
    }
    
}
