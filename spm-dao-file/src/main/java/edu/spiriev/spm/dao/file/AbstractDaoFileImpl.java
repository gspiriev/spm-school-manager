/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.dao.file;

import edu.spiriev.spm.dao.api.AbstractDao;
import edu.spiriev.spm.dao.api.Parser;
import edu.spiriev.spm.domain.model.MusicalPiece;
import edu.spiriev.spm.domain.model.Student;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author root_spiriev
 */
public class AbstractDaoFileImpl<T, E> implements AbstractDao{
    
    private final File inputFile;
    private final Parser<T, E> parser;

    public AbstractDaoFileImpl(File inputFile, Parser<T, E> parser) {
        this.inputFile = inputFile;
        this.parser = parser;
    }

    @Override
    public List<T> loadAll() {
        
        ArrayList<T> dataList = new ArrayList<>();
        
        try(
        BufferedReader reader = new BufferedReader(new FileReader(inputFile)))
        {
            
            while(reader.ready()) {
                
                T dataPiece = parser.parse(reader.readLine());
                dataList.add(dataPiece);
            }
        } catch (Exception e) {
            System.err.println("Data file not found");
        }
        return dataList;
        
    }

    @Override
    public void persistStudent(Student student) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
    @Override
    public Student findStudent(String studentName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeStudent(String studentName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeMusicalPiece(String musicalPieceName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void persistMusicalPiece(MusicalPiece mPiece) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void persistDate(Integer[] date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeDate(Integer[] date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
}
