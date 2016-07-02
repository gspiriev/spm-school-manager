/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.gui;

import edu.spiriev.spm.application.file.AnnualLessonDispositionAppOrm;
import edu.spiriev.spm.business.logic.SpmBusinessProcess;
import edu.spiriev.spm.domain.model.Student;
import edu.spiriev.spm.domain.model.WeeklySchedule;
import edu.spiriev.spm.persistence.JpaDatabaseConnection;
import java.util.AbstractMap;
import java.util.Map;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

/**
 *
 * @author root_spiriev
 */
public class StartYearController {
    
    private StartYearView view;
    
    public void start() {
        
        SwingUtilities.invokeLater(() -> {
            view = new StartYearView();
            view.addButtonEvents(this);
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
                    String[] props = {"Properties in persistence.xml"};
                    Map<Student, WeeklySchedule> lessonDisposition = SpmBusinessProcess.instance
                            .createAllStudentDisposition(
                                    new JpaDatabaseConnection("manager1"),
                                    props,
                                    startEndYear.getValue(),
                                    startEndYear.getKey());

                    
                    new AnnualLessonDispositionAppOrm().writeOutput(lessonDisposition);
                    
                    return null;
                }
                
                @Override
                protected void done() {
                    
                    view.getLabel().setText("Disposition file created in " + System.getProperty("user.dir"));
                }
            };
            
            worker.execute();
        }
    }
    
    public void onClickCancel() {
        
        view.getYearTextField().setText("");
        
    }
    
}