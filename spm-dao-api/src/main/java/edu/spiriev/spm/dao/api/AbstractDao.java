/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.dao.api;


import edu.spiriev.spm.domain.model.Student;
import java.util.List;

/**
 *
 * @author root_spiriev
 */
public interface AbstractDao<T> {
    
    List<T> loadAll();
    
    void persistStudent(Student student);
    
    Student findStudent(String studentName);
    
    void removeStudent(Integer studentId);
}