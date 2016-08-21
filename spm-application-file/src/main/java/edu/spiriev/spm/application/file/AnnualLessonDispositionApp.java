/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.application.file;

import edu.spiriev.spm.dao.database.JdbcConnection;
import edu.spiriev.spm.dao.file.GetDataFromFiles;
import edu.spiriev.spm.persistence.*;
import edu.spiriev.spm.gui.MainPanelController;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

/**
 *
 * @author root_spiriev
 */
public class AnnualLessonDispositionApp {
    
    private JpaDatabaseConnection hibernateConnection;
    private GetDataFromFiles fileConnection;
    private JdbcConnection jdbcConnection;
    private final Properties props;

    public AnnualLessonDispositionApp() {
        props = new Properties();
        try {
            InputStream inputStream = null;
            String propFileName = "application.properties";
            inputStream = this.getClass().getClassLoader().getResourceAsStream(propFileName);
       
            if(inputStream != null) {
                props.load(inputStream);
            } else {
                throw new FileNotFoundException("Properties file " + propFileName + "is not on the classpath");
            }
            inputStream.close();
        } catch(IOException e) {
            System.err.println("Exception: " + e);
        } 
        
    }
    
    public static void main(String[] args) throws Exception {
        AnnualLessonDispositionApp annualDisposition = new AnnualLessonDispositionApp();

        annualDisposition.run();
    }
            
    private void run() throws UnsupportedEncodingException {

        if ("gui".equals(props.getProperty("userInterface")) && "orm".equals(props.getProperty("persistence"))) {
             this.hibernateConnection = new JpaDatabaseConnection("manager4");
            MainPanelController ctrl = new MainPanelController(hibernateConnection);
            ctrl.start();
        } else  if ("gui".equals(props.getProperty("userInterface")) && "file".equals(props.getProperty("persistence"))) {
                this.fileConnection = new GetDataFromFiles(props);
                MainPanelController ctrl = new MainPanelController(fileConnection);
                ctrl.start();
         } else if ("gui".equals(props.getProperty("userInterface")) && "jdbc".equals(props.getProperty("persistence"))) {
                this.jdbcConnection = new JdbcConnection(props);
                MainPanelController ctrl = new MainPanelController(jdbcConnection);
                ctrl.start();
        }
    }

    private Map.Entry<Integer, Integer> readUserInput() {

        System.out.println("Enter start year for the disposition,followed by enter key");
        Scanner scan = new Scanner(System.in);
        final Integer startYear = Integer.parseInt(scan.nextLine());
        final Integer endYear = startYear + 1;
        return new AbstractMap.SimpleEntry<Integer, Integer>(startYear, endYear);

    }
}
