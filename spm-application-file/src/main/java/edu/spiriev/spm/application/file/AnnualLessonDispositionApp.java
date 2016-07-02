/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.application.file;

import edu.spiriev.spm.dao.database.*;
import edu.spiriev.spm.business.logic.SpmBusinessProcess;
import edu.spiriev.spm.domain.model.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;
import java.util.AbstractMap;
import java.util.Properties;

/**
 * The main class which creates an annual disposition list, for all the students
 * in the Student resource file. When adding information about a student or
 * school dates in the resource files, use "/"(forth slash) for delimiter
 * between tokens (tokens are grades, student names, ability, musical piece
 * names, complexity etc.)
 *
 * @author root_spiriev
 */
public class AnnualLessonDispositionApp {

//    public static void main(String[] args) throws Exception {
//        AnnualLessonDispositionApp annualDisposition = new AnnualLessonDispositionApp();
//
//        annualDisposition.run();
//    }
//
//    private void run() throws UnsupportedEncodingException, SQLException {
//        
//        try {
//            Map.Entry<Integer, Integer> startEndYear = readUserInput();
//
//
//            JdbcConnection jdbcConn = new JdbcConnection();
//            jdbcConn.makeConnection(getProperties());
//            
//            Connection conn = jdbcConn.getConn();
//
//            Map<Student, WeeklySchedule> lessonDisposition = SpmBusinessProcess.instance
//                    .createAllStudentDisposition(
//                            new AbstractDaoJdbcImpl(conn),
//                            startEndYear.getValue(),
//                            startEndYear.getKey());
//            
//            jdbcConn.commitTransaction();
//            
//            writeOutput(lessonDisposition);
//        
//        } catch (Exception e) {
//            
//            throw new SQLException(e);
//        }
//        
//    }
//
//    private Map.Entry<Integer, Integer> readUserInput() {
//
//        System.out.println("Enter start year for the disposition,followed by enter key");
//        Scanner scan = new Scanner(System.in);
//        final Integer startYear = Integer.parseInt(scan.nextLine());
//        final Integer endYear = startYear + 1;
//        return new AbstractMap.SimpleEntry<Integer, Integer>(startYear, endYear);
//
//    }
//
//    private void writeOutput(Map<Student, WeeklySchedule> lessonDisposition) {
//
//        File outFile = new File(".", "AnnualLessonDisposition.txt");
//
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
//
//            while (lessonDisposition.size() > 0) {
//
//                Map.Entry<Student, WeeklySchedule> dispositionSetEntry
//                        = lessonDisposition.entrySet().iterator().next();
//
//                lessonDisposition.remove(dispositionSetEntry.getKey());
//                String keySt = dispositionSetEntry.getKey().toString() + "\n" + "\n";
//                String vaSt = dispositionSetEntry.getValue().toString();
//                writer.write(keySt);
//                writer.write(vaSt);
//                writer.flush();
//            }
//
//            System.out.println("Disposition file created in " + System.getProperty("user.dir"));
//        } catch (Exception e) {
//
//            System.out.println("No output file or path found");
//        }
//    }
//    
//    private String[] getProperties() {
//        
//        String[] resultString = new String[3];
//        
//        try {
//            InputStream inputStream = null;
//
//            Properties props = new Properties();
//            String propFileName = "application.properties";
//
//            inputStream = this.getClass().getClassLoader().getResourceAsStream(propFileName);
//        
//       
//            if(inputStream != null) {
//
//                props.load(inputStream);
//            } else {
//
//                throw new FileNotFoundException("Properties file " + propFileName + "is not on the classpath");
//            }
//
//            resultString[0] = props.getProperty("driverName");
//            resultString[1] = props.getProperty("dbName");
//            resultString[2] = props.getProperty("connAndEngine");
//            
//            inputStream.close();
//        } catch(IOException e) {
//            System.err.println("Exception: " + e);
//        } 
//        
//        return resultString;
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

}
