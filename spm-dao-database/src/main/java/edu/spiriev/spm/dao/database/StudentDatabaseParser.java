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
public class StudentDatabaseParser implements Parser<Student, Student>{

    @Override
    public Student parse(String stringToParse) {
                Student student = null;
                String[] stringToParseArray = stringToParse.split("/");
                String name = stringToParseArray[0];
                Grade grade = Grade.values()[Integer.parseInt(stringToParseArray[1]) - 1];
                int ability = Integer.parseInt(stringToParseArray[2]);
                student = new Student(name, grade, ability);
                return student;
    }

    @Override
    public Student parse(Student entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
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

//    private void insertStudentDataFromFileToDb (StudentLoader stLoader) {
//        
//        try (Connection conn = new DatabaseConnection().getConnection()){
//            
//            conn.setAutoCommit(false);
//            List<Student> studentListForDbInsertion = stLoader.loadStudents();
//            int studentId = 1;
//            
//            for (Student st: studentListForDbInsertion) {
//                
//                
//                String name = st.getName();
//                int ability = st.getAbility();
//                Grade grade = st.getGrade();
//                
//                String sql = "INSERT INTO Student (student_name, ability) " +
//                             "VALUES (?, ?)";
//                PreparedStatement stmt = conn.prepareStatement(sql);
//               
//                stmt.setString(1, name);
//                stmt.setInt(2, ability);
//                
//                stmt.executeUpdate();
//                
//                String gradeInsertionSql = "INSERT INTO student_grade (student_id, grade_id) " +
//                                           "VALUES (?, ?)";
//                PreparedStatement gradeInsertionStmt = conn.prepareStatement(gradeInsertionSql);
//                gradeInsertionStmt.setInt(1, studentId);
//                gradeInsertionStmt.setInt(2, (Grade.valueOf(grade.toString()).ordinal()) + 1);
//                
//                gradeInsertionStmt.executeUpdate();
//                
//                conn.commit();
//                studentId++;
//            }
//        } catch (SQLException e) {
//            
//            System.err.println("Invalid SQL query ");
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            
//            System.err.println("No sqlite driver found");
//        }
//    }

    @Override
    public boolean isNumeric(String str) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
