/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.dao.database;



import edu.spiriev.spm.dao.api.Parser;
import edu.spiriev.spm.domain.model.Grade;
import edu.spiriev.spm.domain.model.MusicalPiece;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



/**
 * Loads and creates a musical piece list from a database
 * @author root_spiriev
 */
public class MusicalPieceDatabaseParser implements Parser<MusicalPiece, MusicalPiece>{
     
    @Override
    public MusicalPiece parse(String stringToParse) {
                MusicalPiece mPiece = null;
                String[] stringToParseArray = stringToParse.split("/");
                String name = stringToParseArray[0];
                String composer = stringToParseArray[1];
                int complexity = Integer.parseInt(stringToParseArray[2]);
                Grade grade = Grade.values()[Integer.parseInt(stringToParseArray[3]) - 1];
                mPiece = new MusicalPiece(name, composer, complexity, grade);
                return mPiece;
    }

    @Override
    public MusicalPiece parse(MusicalPiece entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean isNumeric(String str) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
