/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.gui;

import edu.spiriev.spm.application.file.AnnualLessonDispositionAppOrm;
import edu.spiriev.spm.domain.model.MusicalPiece;
import edu.spiriev.spm.domain.model.Student;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author root_spiriev
 */
public class EditDataController {

    EditDataView editDataView;
    String[] added = new String[3];
    public DefaultTableModel[] tableModels = new DefaultTableModel[3];
    private String[][] studentsArray;
    private String[][] musicalPieceArray;
    private String[][] datesArray;
    private final AnnualLessonDispositionAppOrm app;

    public EditDataController(AnnualLessonDispositionAppOrm app) {
        this.app = app;
    }

    public void start() {
        SwingUtilities.invokeLater(() -> {
            editDataView = new EditDataView();
            editDataView.addButtonEvents(this);
        });

    }

    public void onClickEditDates() {

        if (added[0] != null) {
            CardLayout cl = (CardLayout) editDataView.getPane().getLayout();
            cl.show(editDataView.getPane(), "1");
            
            for (ActionListener al: editDataView.getInsert().getActionListeners()) {
                        editDataView.getInsert().removeActionListener(al);
            }
            editDataView.getInsert().addActionListener((ActionEvent e) -> {
                        onClickInsertDate();
            });
            
        } else {
            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

                private List<Date> datesList = new ArrayList<>();

                @Override
                protected Void doInBackground() throws Exception {
                    datesList = app.getAllDates();
                    datesArray = new String[datesList.size()][1];

                    for (int i = 0; i < datesList.size(); i++) {
                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        String dateStringFormat = df.format(datesList.get(i));
                        datesArray[i][0] = dateStringFormat;
                    }
                    return null;
                }

                @Override
                protected void done() {
                    DefaultTableModel dtm = new DefaultTableModel(datesArray, new String[]{"Dates"});
                    tableModels[0] = dtm;
                    JTable table = new JTable(dtm);
                    JScrollPane newScrollPane = new JScrollPane(table);
                    editDataView.getPane().add(newScrollPane, "1");
                    CardLayout cl = (CardLayout) editDataView.getPane().getLayout();
                    cl.show(editDataView.getPane(), "1");
                    
                    for (ActionListener al: editDataView.getInsert().getActionListeners()) {
                        editDataView.getInsert().removeActionListener(al);
                    }
                    editDataView.getInsert().setEnabled(true);
                    editDataView.getRemove().setEnabled(true);
                    editDataView.getRemove().addActionListener((ActionEvent e) -> {
                        onClickRemoveDate(table);
                    });
                    editDataView.getInsert().addActionListener((ActionEvent e) -> {
                        onClickInsertDate();
                    });
                }
            };

            worker.execute();
            added[0] = "DateTable added";
        }
    }

    public void onClickEditMusicalPieces() {
        if (added[1] != null) {
            CardLayout cl = (CardLayout) editDataView.getPane().getLayout();
            cl.show(editDataView.getPane(), "2");
            
            for (ActionListener al: editDataView.getInsert().getActionListeners()) {
                        editDataView.getInsert().removeActionListener(al);
            }
            editDataView.getInsert().addActionListener((ActionEvent e) -> {
                        onClickInsertMusicalPiece();
            });
        } else {
            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

                private List<MusicalPiece> musicalPieceList = new ArrayList<>();

                @Override
                protected Void doInBackground() throws Exception {
                    musicalPieceList = app.getAllMusicalPieces();
                    musicalPieceArray = new String[musicalPieceList.size()][4];

                    for (int i = 0; i < musicalPieceList.size(); i++) {
                        musicalPieceArray[i][0] = musicalPieceList.get(i).getName();
                        musicalPieceArray[i][1] = musicalPieceList.get(i).getComposer();
                        musicalPieceArray[i][2] = musicalPieceList.get(i).getGrade().toString();
                        musicalPieceArray[i][3] = String.valueOf(musicalPieceList.get(i).getComplexity());
                    }

                    return null;
                }

                @Override
                protected void done() {

                    DefaultTableModel dtmPieces = new DefaultTableModel(musicalPieceArray, new String[]{"Name",
                        "Composer",
                        "Complexity",
                        "Grade"});
                    tableModels[1] = dtmPieces;
                    JTable musicalPieceTable = new JTable(dtmPieces);
                    JScrollPane newScrollPane = new JScrollPane(musicalPieceTable);
                    editDataView.getPane().add(newScrollPane, "2");
                    CardLayout cl = (CardLayout) editDataView.getPane().getLayout();
                    cl.show(editDataView.getPane(), "2");
                    
                    for (ActionListener al: editDataView.getInsert().getActionListeners()) {
                        editDataView.getInsert().removeActionListener(al);
                    }
                    editDataView.getInsert().setEnabled(true);
                    editDataView.getRemove().setEnabled(true);
                    editDataView.getRemove().addActionListener((ActionEvent e) -> {
                        onClickRemoveMusicalPiece(musicalPieceTable);
                    });
                    editDataView.getInsert().addActionListener((ActionEvent e) -> {
                        onClickInsertMusicalPiece();
                    });
                }
            };

            worker.execute();
            added[1] = "Musical Piece Table added";
        }

    }

    public void onClickEditStudents() {
        
        if (added[2] != null) {
            CardLayout cl = (CardLayout) editDataView.getPane().getLayout();
            cl.show(editDataView.getPane(), "3");
            
            for (ActionListener al: editDataView.getInsert().getActionListeners()) {
                editDataView.getInsert().removeActionListener(al);
            }
            editDataView.getInsert().addActionListener((ActionEvent e) -> {
                onClickInsertStudent();
            });
            
        } else {
            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

                private List<Student> studentsList = new ArrayList<>();

                @Override
                protected Void doInBackground() throws Exception {

                    studentsList = app.getAllStudents();
                    studentsArray = new String[studentsList.size()][3];
                    for (int i = 0; i < studentsList.size(); i++) {
                        studentsArray[i][0] = studentsList.get(i).getName();
                        studentsArray[i][1] = studentsList.get(i).getGrade().toString();
                        studentsArray[i][2] = String.valueOf(studentsList.get(i).getAbility());
                    }
                    return null;
                }

                @Override
                protected void done() {
                    DefaultTableModel dtmStudents = new DefaultTableModel(studentsArray, new String[]{"Name",
                                                                                              "Grade",
                                                                                              "Ability"});
                    tableModels[2] = dtmStudents;
                    JTable studentsTable = new JTable(dtmStudents);
                    JScrollPane newScrollPane = new JScrollPane(studentsTable);
                    editDataView.getPane().add(newScrollPane, "3");
                    CardLayout cl = (CardLayout) editDataView.getPane().getLayout();
                    cl.show(editDataView.getPane(), "3");
                    
                    for (ActionListener al: editDataView.getInsert().getActionListeners()) {
                        editDataView.getInsert().removeActionListener(al);
                    }
                    editDataView.getInsert().setEnabled(true);
                    editDataView.getRemove().setEnabled(true);
                    editDataView.getRemove().addActionListener((ActionEvent e) -> {
                        onClickRemoveStudent(studentsTable);
                    });
                    editDataView.getInsert().addActionListener((ActionEvent e) -> {
                        onClickInsertStudent();
                    });
                }
            };

            worker.execute();
            added[2] = "Students table added";
        }
    }

    public void onClickRemoveStudent(JTable table) {
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        if (table.getSelectedRow() != -1) {

            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

                @Override
                protected Void doInBackground() throws Exception {
                    String studentName = (String) dtm.getValueAt(table.getSelectedRow(), 0);
                    app.removeStudent(studentName);
                    return null;
                }

                @Override
                protected void done() {
                    dtm.removeRow(table.getSelectedRow());
                }
            };

            worker.execute();
        }
    }

    public void onClickInsertStudent() {
        JTextField[] fields = new JTextField[3];
        fields[0] = new JTextField("Student Name", 20);
        fields[1] = new JTextField("Grade", 6);
        fields[2] = new JTextField("Ability", 8);
        InsertViewController ctrl = new InsertViewController(new InsertView(new JFrame(), fields), app, tableModels[2]);
        ctrl.startForStudent();

    }

    public void onClickRemoveMusicalPiece(JTable table) {
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        if (table.getSelectedRow() != -1) {

            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

                @Override
                protected Void doInBackground() throws Exception {
                    String musicalPieceName = (String) dtm.getValueAt(table.getSelectedRow(), 0);

                    app.removeMusicalPiece(musicalPieceName);
                    return null;
                }

                @Override
                protected void done() {
                    dtm.removeRow(table.getSelectedRow());
                }
            };

            worker.execute();
        }

    }

    public void onClickInsertMusicalPiece() {
        JTextField[] fields = new JTextField[4];
        fields[0] = new JTextField("Musical Piece Name", 20);
        fields[1] = new JTextField("Composer", 15);
        fields[2] = new JTextField("Complexity", 8);
        fields[3] = new JTextField("Grade", 15);
        InsertViewController ctrl = new InsertViewController(new InsertView(new JFrame(), fields), app, tableModels[1]);
        ctrl.startForMusicalPiece();
    }

    public void onClickRemoveDate(JTable table) {
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        if (table.getSelectedRow() != -1) {

            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

                @Override
                protected Void doInBackground() throws Exception {
                    String dateString = (String) dtm.getValueAt(table.getSelectedRow(), 0);
                    String[] dateArray = dateString.split("/");
                    Integer[] dateToRemove = new Integer[3];
                    dateToRemove[0] = Integer.parseInt(dateArray[0]);
                    dateToRemove[1] = Integer.parseInt(dateArray[1]);
                    dateToRemove[2] = Integer.parseInt(dateArray[2]);
                    app.removeDate(dateToRemove);
                    return null;
                }

                @Override
                protected void done() {
                    dtm.removeRow(table.getSelectedRow());
                }
            };

            worker.execute();
        }
    }

    public void onClickInsertDate() {
        JTextField[] fields = new JTextField[3];
        fields[0] = new JTextField("Day", 5);
        fields[1] = new JTextField("Month", 10);
        fields[2] = new JTextField("Year", 7);

        InsertViewController ctrl = new InsertViewController(new InsertView(new JFrame(), fields), app, tableModels[0]);
        ctrl.startForDate();
    }

}
