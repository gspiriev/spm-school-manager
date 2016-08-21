/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author root_spiriev
 */
public class InsertView {

    private final JFrame frame;
    private final JButton insert = new JButton("Insert");
    private final JButton cancel = new JButton("Cancel");
    private final JTextField[] fields;
    JLabel label = new JLabel();

    public InsertView(JFrame frame, JTextField[] fields) {
        this.frame = frame;
        this.frame.setSize(300, 150);
        this.frame.setResizable(false);
        this.fields = fields;
        this.frame.setLayout(new FlowLayout());
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        for (JTextField field : fields) {
            this.frame.add(field);
            field.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    field.selectAll();
                }
            });
        }

        this.frame.add(insert);
        this.frame.add(cancel);
        this.frame.add(label);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }

    public void addButtonEventsForStudent(InsertViewController ctrl) {
        this.insert.addActionListener((ActionEvent e) -> {
            ctrl.onClickInsertStudent();
        });
        this.cancel.addActionListener((ActionEvent e) -> {
            ctrl.onClickCancel();
        });

    }

    public void addButtonEventsForMusicalPiece(InsertViewController ctrl) {
        this.insert.addActionListener((ActionEvent e) -> {
            ctrl.onClickInsertMusicalPiece();
        });
        this.cancel.addActionListener((ActionEvent e) -> {
            ctrl.onClickCancel();
        });

    }

    public void addButtonEventsForDate(InsertViewController ctrl) {
        this.insert.addActionListener((ActionEvent e) -> {
            ctrl.onClickInsertDate();
        });
        this.cancel.addActionListener((ActionEvent e) -> {
            ctrl.onClickCancel();
        });

    }

    public JButton getInsert() {
        return insert;
    }

    public JButton getCancel() {
        return cancel;
    }

    public JTextField[] getFields() {
        return fields;
    }

    public JLabel getLabel() {
        return label;
    }
    
    public JFrame getFrame() {
        return frame;
    }

}
