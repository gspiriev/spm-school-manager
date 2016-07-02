/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.dao.file;

import edu.spiriev.spm.dao.api.BusinessConnection;
import edu.spiriev.spm.domain.model.MusicalPiece;
import edu.spiriev.spm.domain.model.Student;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 *
 * @author root_spiriev
 */
public class GetDataFromFiles implements BusinessConnection {
    
    private File[] resources;
    
    private List<Student> students;
    
    private List<MusicalPiece> musicalPieces;
    
    private List<Date> dates;

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

    public File[] getResources() {
        
        return resources;
    }
    
    @Override
    public void commitTransaction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void makeConnection(String[] props) {
        
        ClassLoader cl = this.getClass().getClassLoader();
        
        this.resources = new File[] {new File(cl.getResource(props[0]).getFile()),
                    new File(cl.getResource(props[1]).getFile()),
                    new File(cl.getResource(props[2]).getFile())};
  
        AbstractDaoFileImpl aDao = new AbstractDaoFileImpl();
        aDao.setInputFile(resources[0]);
        aDao.setParser(new StudentParser());
        students = aDao.loadAll();
        aDao.setInputFile(resources[1]);
        aDao.setParser(new MusicalPieceParser());
        musicalPieces = aDao.loadAll();
        aDao.setInputFile(resources[2]);
        aDao.setParser(new SchoolDatesParser());
        dates = aDao.loadAll();
    }
    
    
    
}
