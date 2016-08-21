/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.gui;

import edu.spiriev.spm.business.logic.SpmBusinessProcess;
import edu.spiriev.spm.dao.api.BusinessConnection;
import edu.spiriev.spm.domain.model.Grade;
import edu.spiriev.spm.domain.model.MusicalPiece;
import edu.spiriev.spm.domain.model.Student;
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
    private final DefaultTableModel tableModels;
    private final BusinessConnection bc;

    public InsertViewController(InsertView insertView, DefaultTableModel tableModels, BusinessConnection bc) {
        this.insertView = insertView;
        this.tableModels = tableModels;
        this.bc = bc;
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
                if (grade > 12 || grade <= 0 || ability > 10 || ability <= 0) {
                    insertView.getLabel().setText("Invalid student field!");
                    return null;
                } else {
                    String[] modelUpdate = new String[]{name, Grade.values()[grade - 1].toString(), fields[2].getText().trim()};
                    Student studentToInsert = new Student(name, Grade.values()[grade - 1], ability );
                    SpmBusinessProcess.instance.insertStudent(bc, studentToInsert);
                    tableModels.addRow(modelUpdate);
                    return null;
                }
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
                Integer grade = new Integer(fields[2].getText().trim());
                Integer complexity = new Integer(fields[3].getText().trim());
                
                if (grade > 12 || grade <= 0 || complexity > 10 || complexity <= 0) {
                    insertView.getLabel().setText("Invalid musical piece field!");
                    return null;
                } else {
                    String[] modelUpdate = new String[]{name, composerName,  Grade.values()[grade - 1].toString(), fields[3].getText().trim()};
                    MusicalPiece mPiece = new MusicalPiece(name, composerName, complexity, Grade.values()[grade - 1]);
                    SpmBusinessProcess.instance.insertMusicalPiece(bc, mPiece);
                    tableModels.addRow(modelUpdate);
                    return null;
                }
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
                if (date[0] > 31 || date[1] > 12 || date[2] > 2099 || 
                    date[0] <= 0 || date[1] <= 0 || date[2] <= 0) {
                    
                    insertView.getLabel().setText("Please enter a valid date");
                    return null;
                } else {
                    String[] modelUpdate = new String[]{fields[0].getText().trim() + "/" + fields[1].getText().trim()
                                                                                   + "/" + fields[2].getText().trim()};
                    SpmBusinessProcess.instance.insertDate(bc, date);
                    tableModels.addRow(modelUpdate);
                    return null;
                }
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
