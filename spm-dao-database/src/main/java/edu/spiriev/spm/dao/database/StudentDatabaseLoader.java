/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.dao.database;


import edu.spiriev.spm.dao.api.StudentDao;
import edu.spiriev.spm.domain.model.Grade;
import edu.spiriev.spm.domain.model.Student;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Loads a list a students from a resource file
 * @author root_spiriev
 */
public class StudentDatabaseLoader implements StudentDao {
    
    
    @Override
    public List<Student> loadStudents() {
       
        List<Student> students = new ArrayList<>();
        
        try(Connection connection = new DatabaseConnection().getConnection()) {
            
            connection.setAutoCommit(false);
            
            String sql = "SELECT Student.student_name, Student.ability, grade.grade_name " +
                         "FROM Student " +
                         "JOIN student_grade ON Student.student_id = student_grade.student_grade_id " +
                         "JOIN grade ON student_grade.grade_id = grade.grade_id" ;
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            connection.commit();
            
            while(rs.next()) {
                
                Student student = null;
                
                String stName = rs.getString(1);
                int ability = rs.getInt(2);
                Grade grade = Grade.valueOf(rs.getString(3));
                
                student = new Student(stName, grade, ability);
                students.add(student);
            }
            
                    
        } catch (SQLException e) {
            
            System.err.println("Invalid SQL query");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            
            System.err.println("No suitable SQLite driver found");
        }
        
        return students;
    }

}
