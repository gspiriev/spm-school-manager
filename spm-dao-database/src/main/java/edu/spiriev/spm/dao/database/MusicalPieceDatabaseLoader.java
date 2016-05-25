/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.dao.database;

import edu.spiriev.spm.dao.api.MusicalPieceDao;
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
public class MusicalPieceDatabaseLoader implements MusicalPieceDao{
    
    private final Connection conn;

    public MusicalPieceDatabaseLoader(Connection conn) {
        this.conn = conn;
    }
    
    
    
    @Override
    public List<MusicalPiece> loadMusicalPieces(){
        
        List<MusicalPiece> musicalPieces = new ArrayList<>();
        
        try {
            
            String sql = "SELECT MusicalPieces.composer, MusicalPieces.piece_name, MusicalPieces.complexity, grade.grade_name " +
                         "FROM MusicalPieces " +
                         "JOIN musicalPiece_grade ON MusicalPieces.musicalPiece_id = musicalPiece_grade.musicalPiece_id " +
                         "JOIN grade ON musicalPiece_grade.grade_id = grade.grade_id" ;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()) {
                
                MusicalPiece mPiece = null;
                String composer = rs.getString(1);
                String pieceName = rs.getString(2);
                int complexity = rs.getInt(3);
                Grade grade = Grade.valueOf(rs.getString(4));
                
                mPiece = new MusicalPiece(pieceName, composer, complexity, grade);
                musicalPieces.add(mPiece);
            }
            
        } catch (SQLException e) {
            
            System.err.println("Invalid SQL query");
            e.printStackTrace();
        }
        
        return musicalPieces;
    }

}
