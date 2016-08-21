/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.gui;

import edu.spiriev.spm.business.logic.SpmBusinessProcess;
import edu.spiriev.spm.dao.api.BusinessConnection;
import java.util.AbstractMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

/**
 *
 * @author root_spiriev
 */
public class StartYearController {
    
    private StartYearView view;
    private final BusinessConnection bc;

    public StartYearController(BusinessConnection bc) {
        this.bc = bc;
    }
    
    public void start() {
            SwingUtilities.invokeLater(() -> {
            view = new StartYearView();
            view.addButtonEventsStartYear(this);
        });
    }

    public void onClickOk() {
        
        Integer startYearInt = Integer.parseInt(view.getYearTextField().getText());
        if (startYearInt < 2015 || startYearInt > 2099) {
            view.getLabel().setText("Please enter a valid year");

        } else {
            
            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

                @Override
                protected Void doInBackground() throws Exception {
                    Map.Entry<Integer, Integer> startEndYear = new AbstractMap.SimpleEntry<>(startYearInt, startYearInt + 1);
                    SpmBusinessProcess.instance.createAllStudentDisposition(bc, startEndYear.getValue(), startEndYear.getKey());
                    return null;
                } 
                
                @Override
                protected void done() {
                    try{
                     get();
                     view.getLabel().setText("Schedule created!");
                    } catch (ExecutionException | InterruptedException  e) {
                        String msg = e.getMessage();
                        JOptionPane.showMessageDialog(null, msg);
                    }                 
                }
            };
            
            worker.execute();
        }
    }
    
    public void onClickCancel() {
        
        view.getYearTextField().setText("");
        
    }
    
}
