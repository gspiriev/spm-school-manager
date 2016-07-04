/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.dao.database;

import edu.spiriev.spm.dao.api.AbstractDao;
import edu.spiriev.spm.dao.api.EntityMarker;
import edu.spiriev.spm.dao.api.Parser;
import edu.spiriev.spm.domain.model.MusicalPiece;
import edu.spiriev.spm.domain.model.Student;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author root_spiriev
 */
public class AbstractDaoJdbcImpl<T extends Comparable<T>, E extends EntityMarker> implements AbstractDao{
    
    private final Connection conn;
    
    private String sql;
    
    private Parser<T, E> parser;

    public AbstractDaoJdbcImpl(Connection conn) {
        this.conn = conn;
    }
    
    

    @Override
    public <T extends Comparable<T>> List<T> loadAll() {
        
        List<T> data = new ArrayList<>();
        
        try {
            
//            String sql = "SELECT MusicalPieces.composer, MusicalPieces.piece_name, MusicalPieces.complexity, grade.grade_name " +
//                         "FROM MusicalPieces " +
//                         "JOIN musicalPiece_grade ON MusicalPieces.musicalPiece_id = musicalPiece_grade.musicalPiece_id " +
//                         "JOIN grade ON musicalPiece_grade.grade_id = grade.grade_id" ;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            T piece = null;
            
            while(rs.next()) {
                
                piece = parser.parse(rs.next());
                
                data.add()
            }
            
        } catch (SQLException e) {
            
            System.err.println("Invalid SQL query");
            e.printStackTrace();
        }
        
        return data;
    }
}
    

