/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.gui;

import edu.spiriev.spm.application.file.AnnualLessonDispositionAppOrm;
import javax.swing.SwingUtilities;

/**
 *
 * @author root_spiriev
 */
public class MainPanelController {
    
    private MainPanelView mainPanelView;
    private final AnnualLessonDispositionAppOrm app;

    public MainPanelController(AnnualLessonDispositionAppOrm app) {
        this.app = app;
    }
    
    public void start() {
        
        SwingUtilities.invokeLater(() -> {
            mainPanelView = new MainPanelView();
            mainPanelView.addButtonEvents(this);
        });
        
    }
    
    public void onClickCreateSchedule() {
        StartYearController ctrl = new StartYearController(app);
        ctrl.start();
    }
    
    public void onClickEditData() {
        EditDataController ctrl = new EditDataController(app);
        ctrl.start();
    }
}
