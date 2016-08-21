/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.dao.file;

import edu.spiriev.spm.dao.api.AbstractDao;
import edu.spiriev.spm.dao.api.Parser;
import edu.spiriev.spm.domain.model.MusicalPiece;
import edu.spiriev.spm.domain.model.Student;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author root_spiriev
 */
public class AbstractDaoFileImpl<T, E> implements AbstractDao{
    
    private final File inputFile;
    private final Parser<T, E> parser;

    public AbstractDaoFileImpl(File inputFile, Parser<T, E> parser) {
        this.inputFile = inputFile;
        this.parser = parser;
    }

    @Override
    public List<T> loadAll() {
        ArrayList<T> dataList = new ArrayList<>();
        String line = null;
        try  {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            while((line = reader.readLine()) != null) {
                line.trim();
                T dataPiece = parser.parse(line);
                dataList.add(dataPiece);
            }
            reader.close();
        } catch (Exception e) {
            System.err.println("Data file not found");
            System.err.println(e.getClass().toGenericString());
        }
        return dataList;
    }

    @Override
    public void persistStudent(Student student) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile, true))) {
            writer.write("\n" + student.getName() + "/" +  ((student.getGrade().ordinal()) + 1) + "/" + student.getAbility());
            writer.flush();
        } catch (IOException e) {
              System.err.println("Data file not found");
        }
    }
  
    @Override
    public Student findStudent(String studentName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeStudent(String studentName) {
        File tempFile = new File(inputFile.getAbsolutePath() + ".tmp");
        String line = null;
        
        try(BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                PrintWriter writer = new PrintWriter(tempFile)) {
                while ((line = reader.readLine()) != null) {
                    if (!line.trim().contains(studentName)) {
                        writer.println(line);
                        writer.flush();
                    }
                }
                if (!inputFile.delete()) {
                    System.err.println("Could not delete file");
                    return;
                 }
                if (!tempFile.renameTo(inputFile)) {
                     System.err.println("Could not rename file");
                 }
        }  catch (IOException e) {
                  System.err.println("Data file not found");
        }
    }

    @Override
    public void removeMusicalPiece(String musicalPieceName) {
        File tempFile = new File(inputFile.getAbsolutePath() + ".tmp");
        String line = null;
        
        try(BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                PrintWriter writer = new PrintWriter(tempFile)) {
                while ((line = reader.readLine()) != null) {
                    if (!line.trim().contains(musicalPieceName)) {
                        writer.println(line);
                        writer.flush();
                    }
                }
                if (!inputFile.delete()) {
                    System.err.println("Could not delete file");
                    return;
                 }
                if (!tempFile.renameTo(inputFile)) {
                     System.err.println("Could not rename file");
                 }
        }  catch (IOException e) {
                  System.err.println("Data file not found");
        }
    }

    @Override
    public void persistMusicalPiece(MusicalPiece mPiece) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile, true))) {
            writer.write((("\n" +  " " + "/" + mPiece.getGrade().ordinal()) + 1) + "/" + mPiece.getComposer() + "/" + mPiece.getName() + "/" + mPiece.getComplexity());
            writer.flush();
        } catch (IOException e) {
              System.err.println("Data file not found");
        }
    }

    @Override
    public void persistDate(Integer[] date) {
       try(BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile, true))) {
           writer.write("\n" + date[0] + "/" +  date[1] + "/" + date[2]);
           writer.flush();
        } catch (IOException e) {
              System.err.println("Data file not found");
        }
    }

    @Override
    public void removeDate(Integer[] date) {
        File tempFile = new File(inputFile.getAbsolutePath() + ".tmp");
        String lineToRemove;
        if ((date[0]) < 10) {
           lineToRemove = 0 + date[0] + "/" + date[1] + "/" + date[2];
        } else  if ((date[1]) < 10) {
           lineToRemove = date[0] + "/" + 0 + date[1] + "/" + date[2];
        } else if ((date[1]) < 10 && date[0] < 10) {
            lineToRemove = 0 + date[0] + "/" + 0 + date[1] + "/" + date[2];
        } else {
            lineToRemove = date[0] + "/" + date[1] + "/" + date[2];
        }
        String line = null;
        
        try(BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                PrintWriter writer = new PrintWriter(tempFile)) {
                while ((line = reader.readLine()) != null) {
                    if (!line.trim().equals(lineToRemove)) {
                        writer.println(line);
                        writer.flush();
                    }
                }
                if (!inputFile.delete()) {
                    System.err.println("Could not delete file");
                    return;
                 }
                if (!tempFile.renameTo(inputFile)) {
                     System.err.println("Could not rename file");
                 }
        }  catch (IOException e) {
                  System.err.println("Data file not found");
        }
    }
}
