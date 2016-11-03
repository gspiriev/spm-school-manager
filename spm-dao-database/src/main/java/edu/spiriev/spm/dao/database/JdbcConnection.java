/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.dao.database;

import edu.spiriev.spm.dao.api.AbstractDao;
import edu.spiriev.spm.dao.api.BusinessConnection;
import edu.spiriev.spm.domain.model.MusicalPiece;
import edu.spiriev.spm.domain.model.Student;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

/**
 *
 * @author root_spiriev
 */
public class JdbcConnection implements BusinessConnection{

    private Connection conn;
    
    private final AbstractDao<Student> studentDao;
    private final AbstractDao<MusicalPiece> musicalPiecesDao;
    private final AbstractDao<Date> datesDao;

    public JdbcConnection(Properties props) {
        try {
            Class.forName(props.getProperty("driverName"));
            String databaseFile = this.getClass().getClassLoader().getResource(props.getProperty("dbName")).getFile();

            this.conn = DriverManager.getConnection(props.getProperty("connAndEngine") + databaseFile);
            
            conn.setAutoCommit(false);
            
            System.out.println("Connected to database");
        } catch (SQLException e) {
            System.err.println("Database file may not be present: " + e);
        } catch (ClassNotFoundException e) {
            System.err.println("Jdbc driver not found");
        }
        String studentsQueryColumns = "Student.student_name, student_grade.grade_id, Student.ability ";
        String studentsQueryTable = "Student JOIN student_grade ON Student.student_id=student_grade.student_id";
        
        String musicalPieceQueryColumns = "MusicalPieces.piece_name, MusicalPieces.composer, MusicalPieces.complexity, musicalPiece_grade.grade_id";
        String musicalPieceQueryTable = "MusicalPieces JOIN musicalPiece_grade ON MusicalPieces.musicalPiece_id=musicalPiece_grade.musicalPiece_id";
        
        String datesQueryColumns = "date_day, date_month, date_year";
        String datesQueryTable = "dates";
        this.studentDao = new AbstractDaoJdbcImpl<>(conn, new StudentDatabaseParser(), studentsQueryColumns, studentsQueryTable);
        this.musicalPiecesDao = new AbstractDaoJdbcImpl<>(conn, new MusicalPieceDatabaseParser(), musicalPieceQueryColumns, musicalPieceQueryTable);
        this.datesDao = new AbstractDaoJdbcImpl<>(conn, new SchoolDatesDatabaseParser(), datesQueryColumns, datesQueryTable);
    }

    public Connection getConn() {
        return conn;
    }
    
    @Override
    public void commitTransaction() {
        
        try {
            if(this.conn != null) {
                    conn.commit();
                } else {
                conn.rollback();
            }
         } catch(SQLException e) {
            System.err.println("Invalid operation or missing database file: " + e);
        }
        
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
        return musicalPiecesDao;
    }

    @Override
    public void close() throws Exception {
        conn.close();
    }
    
    
    
}
