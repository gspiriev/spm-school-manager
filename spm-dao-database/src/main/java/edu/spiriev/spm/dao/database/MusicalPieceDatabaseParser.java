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
//public class MusicalPieceDatabaseParser implements Parser<MusicalPiece, EntityMarker>{
//
//    @Override
//    public MusicalPiece parse(String stringToParse) {
//        MusicalPiece mPiece = null;
//                String composer = rs.getString(1);
//                String pieceName = rs.getString(2);
//                int complexity = rs.getInt(3);
//                Grade grade = Grade.valueOf(rs.getString(4));
//                
//                mPiece = new MusicalPiece(pieceName, composer, complexity, grade);
//                data.add(mPiece);
//    }
//
//    @Override
//    public MusicalPiece parse(EntityMarker entity) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    
//    
//    
//    private final Connection conn;
//
//    public MusicalPieceDatabaseParser(Connection conn) {
//        this.conn = conn;
//    }
//    
//    
//    
//    @Override
//    public List<MusicalPiece> loadMusicalPieces(){
//        
//
//
//}
