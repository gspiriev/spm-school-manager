package edu.spiriev.spm.gui;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author root_spiriev
 */
public class StartYearView {

    private final JButton okButton = new JButton("OK");
    private final JButton cancelButton = new JButton("Cancel");
    private final JLabel label = new JLabel("Enter start year for the disposition");
    private final JTextField yearTextField = new JTextField(4);
    private final JFrame enterYearFrm = new JFrame("School Plan Manager");

    public StartYearView() {

        enterYearFrm.setLayout(new FlowLayout());
        enterYearFrm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        enterYearFrm.setSize(260, 120);
        enterYearFrm.setResizable(false);
        okButton.setFont(new Font("Courier New Regular", Font.BOLD, 13));
        cancelButton.setFont(new Font("Courier New Regular", Font.BOLD, 13));
        label.setFont(new Font("Courier New Regular", Font.BOLD, 13));

        enterYearFrm.add(this.label);
        enterYearFrm.add(this.yearTextField);
        enterYearFrm.add(this.okButton);
        enterYearFrm.add(this.cancelButton);
        enterYearFrm.setLocationRelativeTo(null);
        enterYearFrm.setVisible(true);

    }

    public JButton getOkButton() {
        return okButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JLabel getLabel() {
        return label;
    }

    public JTextField getYearTextField() {
        return yearTextField;
    }

    public JFrame getEnterYearFrm() {
        return enterYearFrm;
    }

    public void addButtonEvents(StartYearController startCtrl) {

        this.okButton.addActionListener((ActionEvent e) -> {

            startCtrl.onClickOk();
        });

        this.cancelButton.addActionListener((ActionEvent e) -> {

            startCtrl.onClickCancel();
        });

    }

}
