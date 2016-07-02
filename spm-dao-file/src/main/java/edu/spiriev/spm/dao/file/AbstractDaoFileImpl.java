/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.dao.file;

import edu.spiriev.spm.dao.api.AbstractDao;
import edu.spiriev.spm.dao.api.EntityMarker;
import edu.spiriev.spm.dao.api.Parser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author root_spiriev
 */
public class AbstractDaoFileImpl<T extends Comparable<T>> implements AbstractDao{
    
    private File inputFile;
    private Parser<T, EntityMarker> parser;

    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

    public void setParser(Parser<T, EntityMarker> parser) {
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
    
       
    
}
