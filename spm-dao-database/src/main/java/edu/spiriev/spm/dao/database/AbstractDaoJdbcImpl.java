/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.dao.database;

import edu.spiriev.spm.dao.api.AbstractDao;

import edu.spiriev.spm.dao.api.Parser;
import edu.spiriev.spm.domain.model.Grade;
import edu.spiriev.spm.domain.model.MusicalPiece;
import edu.spiriev.spm.domain.model.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author root_spiriev
 */
public class AbstractDaoJdbcImpl<T, E> implements AbstractDao {

    private final Connection conn;
    private final Parser<T, E> parser;
    private final String queryColumns;
    private final String queryTable;

    public AbstractDaoJdbcImpl(Connection conn, Parser<T, E> parser, String queryColumns, String queryTable) {
        this.conn = conn;
        this.parser = parser;
        this.queryColumns = queryColumns;
        this.queryTable = queryTable;
    }

    @Override
    public List<T> loadAll() {

        ArrayList<T> data = new ArrayList<>();

        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT " + queryColumns + " FROM " + queryTable;
            String[] queryColumnsArray = queryColumns.split(",");
            ResultSet rs = stmt.executeQuery(sql);
            String dataString = "";
            while (rs.next()) {
                for (int i = 1; i <= queryColumnsArray.length; i++) {
                    if (i == queryColumnsArray.length) {
                        dataString += rs.getString(i);
                    } else {
                        dataString += (rs.getString(i) + "/");
                    }
                }
                T dataPiece = parser.parse(dataString);
                data.add(dataPiece);
                dataString = "";
            }

        } catch (SQLException e) {
            System.err.println("Invalid SQL query");
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public void persistStudent(Student student) {

        try {
            String name = student.getName();
            int ability = student.getAbility();
            Grade grade = student.getGrade();

            String sql = "INSERT INTO Student (student_name, ability) "
                    + "VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, name);
            stmt.setInt(2, ability);

            stmt.executeUpdate();

            Statement studentIdQuery = conn.createStatement();
            ResultSet rs = studentIdQuery.executeQuery("SELECT student_id FROM Student WHERE student_name="
                    + "'" + name + "'");
            rs.next();
            int studentId = rs.getInt(1);

            String gradeInsertionSql = "INSERT INTO student_grade (student_id, grade_id) "
                    + "VALUES (?, ?)";
            PreparedStatement gradeInsertionStmt = conn.prepareStatement(gradeInsertionSql);
            gradeInsertionStmt.setInt(1, studentId);
            gradeInsertionStmt.setInt(2, (Grade.valueOf(grade.toString()).ordinal()) + 1);

            gradeInsertionStmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Invalid SQL query ");
            e.printStackTrace();
        }
    }

    @Override
    public void persistMusicalPiece(MusicalPiece mPiece) {
        try {
            String name = mPiece.getName();
            String composer = mPiece.getComposer();
            int complexity = mPiece.getComplexity();
            Grade grade = mPiece.getGrade();

            String sql = "INSERT INTO MusicalPieces (composer, piece_name, complexity) "
                    + "VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, composer);
            stmt.setString(2, name);
            stmt.setInt(3, complexity);

            stmt.executeUpdate();

            Statement studentIdQuery = conn.createStatement();
            ResultSet rs = studentIdQuery.executeQuery("SELECT musicalPiece_id FROM MusicalPieces WHERE piece_name="
                    + "'" + name + "'");
            rs.next();
            int musicalPieceId = rs.getInt(1);

            String gradeInsertionSql = "INSERT INTO musicalPiece_grade (musicalPiece_id, grade_id) "
                    + "VALUES (?, ?)";
            PreparedStatement gradeInsertionStmt = conn.prepareStatement(gradeInsertionSql);
            gradeInsertionStmt.setInt(1, musicalPieceId);
            gradeInsertionStmt.setInt(2, (Grade.valueOf(grade.toString()).ordinal()) + 1);

            gradeInsertionStmt.executeUpdate();

        } catch (SQLException e) {

            System.err.println("Invalid SQL query ");
            e.printStackTrace();
        }
    }

    @Override
    public void persistDate(Integer[] date) {
        try {
            String sql = "INSERT INTO dates (date_day, date_month, date_year) "
                    + "VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, date[0]);
            stmt.setInt(2, date[1]);
            stmt.setInt(3, date[2]);

            stmt.executeUpdate();

        } catch (SQLException e) {

            System.err.println("Invalid SQL query ");
            e.printStackTrace();
        }
    }

    @Override
    public void removeDate(Integer[] date) {
        try {
            String sql = " DELETE FROM dates WHERE date_day=" + "'" + date[0] + "'" + " AND date_month="
                    + "'" + date[1] + "'";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Invalid SQL query ");
            e.printStackTrace();
        }
    }

    @Override
    public Student findStudent(String studentName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeStudent(String studentName) {
        try {
            String sqlSelect = "SELECT student_id FROM Student WHERE student_name=" + "'" + studentName + "'";
            String sqlDeleteStudent = "DELETE FROM Student WHERE student_name=" + "'" + studentName + "'";
            Statement select = conn.createStatement();
            ResultSet rs = select.executeQuery(sqlSelect);
            rs.next();
            int studentId = rs.getInt(1);
             String sqlDeleteGrade =  "DELETE FROM student_grade WHERE student_id=" +  studentId;
            PreparedStatement deleteGradeStatement = conn.prepareStatement(sqlDeleteGrade);
            deleteGradeStatement.executeUpdate();
            PreparedStatement stmt = conn.prepareStatement(sqlDeleteStudent);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Invalid SQL query ");
            e.printStackTrace();
        }
    }

    @Override
    public void removeMusicalPiece(String musicalPieceName) {
        try {
            String sqlSelect = "SELECT musicalPiece_id FROM MusicalPieces WHERE piece_name=" + "'" + musicalPieceName + "'";
            String sqlDeletePiece = "DELETE FROM MusicalPieces WHERE piece_name=" + "'" + musicalPieceName + "'";
            Statement select = conn.createStatement();
            ResultSet rs = select.executeQuery(sqlSelect);
            rs.next();
            int musicalPieceId = rs.getInt(1);
            String sqlDeleteGrade =  "DELETE FROM musicalPiece_grade WHERE id=" + musicalPieceId;
            PreparedStatement deleteGradeStatement = conn.prepareStatement(sqlDeleteGrade);
            deleteGradeStatement.executeUpdate();
            PreparedStatement stmt = conn.prepareStatement(sqlDeletePiece);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Invalid SQL query ");
            e.printStackTrace();
        }
    }

}
