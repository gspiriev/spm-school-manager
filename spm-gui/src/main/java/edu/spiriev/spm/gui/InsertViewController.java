/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.gui;

import edu.spiriev.spm.application.file.AnnualLessonDispositionAppOrm;
import edu.spiriev.spm.domain.model.Grade;
import edu.spiriev.spm.domain.model.MusicalPiece;
import edu.spiriev.spm.domain.model.Student;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author root_spiriev
 */
public class InsertViewController {
    
    public final InsertView insertView;
    private final AnnualLessonDispositionAppOrm app;
    private final DefaultTableModel tableModels;

    public InsertViewController(InsertView insertView, AnnualLessonDispositionAppOrm app, DefaultTableModel tableModels) {
        this.insertView = insertView;
        this.app = app;
        this.tableModels = tableModels;
    }

    

    public void startForStudent() {
        SwingUtilities.invokeLater(() -> {
            
            insertView.addButtonEventsForStudent(this);
        });
    }
    
    public void startForMusicalPiece() {
        SwingUtilities.invokeLater(() -> {
            
            insertView.addButtonEventsForMusicalPiece(this);
        });
    }
    
    public void startForDate() {
        SwingUtilities.invokeLater(() -> {
            
            insertView.addButtonEventsForDate(this);
        });
    }
    
    public void onClickInsertStudent() {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground() throws Exception {
                JTextField[] fields = insertView.getFields();
                String name = fields[0].getText();
                Integer grade = new Integer(fields[1].getText().trim());
                Integer ability = new Integer(fields[2].getText().trim());
                String[] modelUpdate = new String[]{name, Grade.values()[grade - 1].toString(), fields[2].getText().trim()};
                Student studentToInsert = new Student(name, Grade.values()[grade - 1], ability );
                app.persistStudent(studentToInsert);
                tableModels.addRow(modelUpdate);
                return null;
            }
        };
        
        worker.execute();
    }
    
    public void onClickInsertMusicalPiece() {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground() throws Exception {
                JTextField[] fields = insertView.getFields();
                String name = fields[0].getText();
                String composerName = fields[1].getText();
                Integer complexity = new Integer(fields[2].getText().trim());
                Integer grade = new Integer(fields[3].getText().trim());
                String[] modelUpdate = new String[]{name, composerName, fields[2].getText().trim(), Grade.values()[grade - 1].toString()};
                
                MusicalPiece mPiece = new MusicalPiece(name, composerName, complexity, Grade.values()[grade - 1]);
                app.persistMusicalPiece(mPiece);
                tableModels.addRow(modelUpdate);
                return null;
            }
        };
        
        worker.execute();
    }
    
    public void onClickInsertDate() {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground() throws Exception {
                JTextField[] fields = insertView.getFields();
                Integer[] date = new Integer[3];
                date[0] = new Integer(fields[0].getText().trim());
                date[1] = new Integer(fields[1].getText().trim());
                date[2] = new Integer(fields[2].getText().trim());
                String[] modelUpdate = new String[]{fields[0].getText().trim() + "/" + fields[1].getText().trim()
                                                                               + "/" + fields[2].getText().trim()};
                app.persistDate(date);
                tableModels.addRow(modelUpdate);
                return null;
            }
        };
        
        worker.execute();
    }
    
    public void onClickCancel() {
        
        JTextField[] fields  = insertView.getFields();
        for (JTextField field: fields) {
            field.setText("");
        }
    }
}
