/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.gui;

import edu.spiriev.spm.application.file.AnnualLessonDispositionAppOrm;

/**
 *
 * @author root_spiriev
 */
public class SpmSwingGuiRun {
    
    public static void main(String[] args) {
        
        new SpmSwingGuiRun().run();
    }
    
    private void run() {
        
        MainPanelController mainPanelController = new MainPanelController(new AnnualLessonDispositionAppOrm());
        mainPanelController.start();
        
    }
    
}
