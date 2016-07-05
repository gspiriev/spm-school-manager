/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.dao.database;



import edu.spiriev.spm.dao.api.Parser;
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
//public class StudentDatabaseParser implements Parser<Student, EntityMarker>{
//
//    @Override
//    public Student parse(String stringToParse) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public Student parse(EntityMarker entity) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    
//    
//    
//    private final Connection conn;
//
//    public StudentDatabaseParser(Connection conn) {
//        this.conn = conn;
//    }
//    
//    
//    
//    @Override
//    public List<Student> loadStudents() {
//       
//        List<Student> students = new ArrayList<>();
//        
//        try {
//            
//            
//            String sql = "SELECT Student.student_name, Student.ability, grade.grade_name " +
//                         "FROM Student " +
//                         "JOIN student_grade ON Student.student_id = student_grade.student_grade_id " +
//                         "JOIN grade ON student_grade.grade_id = grade.grade_id" ;
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//            
//            while(rs.next()) {
//                
//                Student student = null;
//                
//                String stName = rs.getString(1);
//                int ability = rs.getInt(2);
//                Grade grade = Grade.valueOf(rs.getString(3));
//                
//                student = new Student(stName, grade, ability);
//                students.add(student);
//            }
//            
//                    
//        } catch (SQLException e) {
//            
//            System.err.println("Invalid SQL query");
//        }
//        return students;
//    }
//
//}
