/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.gui;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author root_spiriev
 */
public class EditDataView {

    private final JFrame frame = new JFrame("Edit Data");
    private final JPanel pane = new JPanel(new CardLayout());
    private final JScrollPane scrollPane = new JScrollPane(new JTable(new DefaultTableModel()));

    private final JButton editStudents = new JButton("Edit Students");
    private final JButton editMusicalPieces = new JButton("Edit Musical Pieces");
    private final JButton editDates = new JButton("Edit Vacation Days");
    private final JButton insert = new JButton("Insert");
    private final JButton remove = new JButton("Remove");

    public EditDataView() {

        frame.setSize(500, 550);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        pane.setOpaque(true);

        pane.add(scrollPane);
        
        remove.setEnabled(false);
        insert.setEnabled(false);
        
        frame.add(pane);
        frame.add(editDates);
        frame.add(editMusicalPieces);
        frame.add(editStudents);
        frame.add(insert);
        frame.add(remove);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public JPanel getPane() {
        return pane;
    }

    public JButton getInsert() {
        return insert;
    }

    public JButton getRemove() {
        return remove;
    }
    
    

    public void addButtonEvents(EditDataController ctrl) {
        this.editDates.addActionListener((ActionEvent e) -> {
            ctrl.onClickEditDates();
        });
        this.editMusicalPieces.addActionListener((ActionEvent e) -> {
            ctrl.onClickEditMusicalPieces();
        });
        this.editStudents.addActionListener((ActionEvent e) -> {
            ctrl.onClickEditStudents();
        });
    }

}
