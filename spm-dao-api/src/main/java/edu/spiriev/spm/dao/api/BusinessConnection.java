/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.dao.api;

import edu.spiriev.spm.domain.model.MusicalPiece;
import edu.spiriev.spm.domain.model.Student;
import java.util.Date;
import java.util.List;

/**
 *
 * @author root_spiriev
 */
public interface BusinessConnection extends AutoCloseable{
    
    void commitTransaction();
    
    AbstractDao<Student> getStudentDao();
    
    AbstractDao<Date> getDatesDao();
    
    AbstractDao<MusicalPiece> getMusicalPiecesDao();
    
}
