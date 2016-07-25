/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.dao.file;

import edu.spiriev.spm.dao.api.AbstractDao;
import edu.spiriev.spm.dao.api.BusinessConnection;
import edu.spiriev.spm.domain.model.MusicalPiece;
import edu.spiriev.spm.domain.model.Student;
import java.io.File;
import java.util.Date;

/**
 *
 * @author root_spiriev
 */
public class GetDataFromFiles implements BusinessConnection {
    
    private final File[] resources;
    
    private final AbstractDao<Student> studentDao;
    private final AbstractDao<MusicalPiece> musicalPiecesDao;
    private final AbstractDao<Date> datesDao;

    public GetDataFromFiles(String[] props) {
        ClassLoader cl = this.getClass().getClassLoader();
        
        this.resources = new File[] {new File(cl.getResource(props[0]).getFile()),
                    new File(cl.getResource(props[1]).getFile()),
                    new File(cl.getResource(props[2]).getFile())};
        this.studentDao = new AbstractDaoFileImpl<>(resources[0], new StudentParser());                
        this.musicalPiecesDao = new AbstractDaoFileImpl<>(resources[1], new MusicalPieceParser()); 
        this.datesDao = new AbstractDaoFileImpl<>(resources[2], new SchoolDatesParser()); 
    }

    public File[] getResources() {
        
        return resources;
    }
    
    @Override
    public void commitTransaction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        return  musicalPiecesDao;
    }

    @Override
    public void close() throws Exception {
    }
    
}
