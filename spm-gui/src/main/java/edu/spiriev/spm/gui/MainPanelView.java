/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.gui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author root_spiriev
 */
public class MainPanelView  {
    
    private final JFrame mainFrame = new JFrame("School Project Manager");
    private final JButton createSchedule = new JButton("Create schedule");
    private final JButton editData = new JButton("Edit data");
    private final JLabel mainLabel = new JLabel("Welcome to \"School Project Manager\"", SwingConstants.CENTER);

    public MainPanelView() {
        
        mainFrame.setLayout(new GridLayout(0, 2));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(580, 200);
        mainFrame.setResizable(false);
        mainLabel.setFont(new Font("Courier New Regular", Font.BOLD, 13));
        editData.setFont(new Font("Courier New Regular", Font.BOLD, 13));
        createSchedule.setFont(new Font("Courier New Regular", Font.BOLD, 13));
        
        mainFrame.add(createSchedule);
        mainFrame.add(mainLabel);
        mainFrame.add(editData);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }
    
    public void addButtonEvents(MainPanelController ctrl) {
        
        this.createSchedule.addActionListener((ActionEvent e) -> {
            
            ctrl.onClickCreateSchedule();
        });
        
        this.editData.addActionListener((ActionEvent e) -> {
            
            ctrl.onClickEditData();
        });
        
    }
    
    
    
    
}
