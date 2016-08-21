/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.gui;

import edu.spiriev.spm.dao.api.BusinessConnection;
import javax.swing.SwingUtilities;

/**
 *
 * @author root_spiriev
 */
public class MainPanelController {
    
    private MainPanelView mainPanelView;
    private final BusinessConnection bc;

    public MainPanelController(BusinessConnection bc) {
        this.bc = bc;
    }
    
    public void start() {
        
        SwingUtilities.invokeLater(() -> {
            mainPanelView = new MainPanelView();
            mainPanelView.addButtonEventsMainPanel(this);
        });
    }
    
    public void onClickCreateSchedule() {
        StartYearController ctrl = new StartYearController(bc);
        ctrl.start();
    }
    
    public void onClickEditData() {
        EditDataController ctrl = new EditDataController(bc);
        ctrl.start();
    }
}
