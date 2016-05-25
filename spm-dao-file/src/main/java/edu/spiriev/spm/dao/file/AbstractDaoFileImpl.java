/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.dao.file;

import edu.spiriev.spm.dao.api.AbstractDao;
import edu.spiriev.spm.domain.model.MusicalPiece;
import edu.spiriev.spm.domain.model.Student;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 *
 * @author root_spiriev
 */
public class AbstractDaoFileImpl implements AbstractDao{

    
    private List<Student> students;
    private List<MusicalPiece> mPieces;
    private List<Date> dates;
    private final File[] resources;

    public AbstractDaoFileImpl(File[] resources) {
        this.resources = resources;
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
        this.students = new StudentLoader(resources[0]).loadStudents();
        this.dates = new SchoolDatesLoader(resources[2]).loadDates();
        this.mPieces = new MusicalPieceLoader(resources[1]).loadMusicalPieces();
        
    }
    
}
