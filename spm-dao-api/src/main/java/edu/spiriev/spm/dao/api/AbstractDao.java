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
public interface AbstractDao<T> {
    
    List<T> loadAll();
    
    void persistStudent(Student student);
    
    void persistMusicalPiece(MusicalPiece mPiece);
    
    void persistDate(Integer[] date);
    
    void removeDate(Integer[] date);
    
    Student findStudent(String studentName);
    
    void removeStudent(String studentName);
    
    void removeMusicalPiece(String musicalPieceName);
}