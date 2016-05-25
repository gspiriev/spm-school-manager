package edu.spiriev.spm.gui;



import edu.spiriev.spm.business.logic.SpmBusinessProcess;
import edu.spiriev.spm.domain.model.Student;
import edu.spiriev.spm.domain.model.WeeklySchedule;
import edu.spiriev.spm.persistence.AbstractDaoImpl;
import edu.spiriev.spm.persistence.JpaDatabaseConnection;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.AbstractMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
public class View {
    
    private final JButton okButton = new JButton("OK");
    private final JButton cancelButton = new JButton("Cancel");
    private final JLabel label = new JLabel("Enter start year for the disposition");
    private final JTextField yearTextField = new JTextField(4);
    private final JFrame enterYearFrm = new JFrame("School Plan Manager");
    
    public View() {
        
        enterYearFrm.setLayout(new FlowLayout());
        enterYearFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        enterYearFrm.setSize(260, 120);
        
        
        enterYearFrm.add(this.label);
        enterYearFrm.add(this.yearTextField);
        enterYearFrm.add(this.okButton);
        enterYearFrm.add(this.cancelButton);
        
        enterYearFrm.setVisible(true);
        
    }
    
    public void addButtonEvents() {
        
        this.okButton.addActionListener((ActionEvent e) -> {
            
            Integer startYearInt = Integer.parseInt(this.yearTextField.getText());
            if (startYearInt < 2015 || startYearInt > 2099) {
                this.label.setText("Please enter a valid year");
                
            } else {
                Map.Entry<Integer, Integer> startEndYear = new AbstractMap.SimpleEntry<>(startYearInt, startYearInt + 1);
                JpaDatabaseConnection hDbConn = new JpaDatabaseConnection("manager1");
                hDbConn.makeConnection(new String[]{"Properties in persistence.xml"});


                Map<Student, WeeklySchedule> lessonDisposition = SpmBusinessProcess.instance
                        .createAllStudentDisposition(
                                new AbstractDaoImpl(hDbConn.getEm()),
                                startEndYear.getValue(),
                                startEndYear.getKey());

                hDbConn.commitTransaction();
                writeOutput(lessonDisposition);

                this.label.setText("Disposition file created in " + System.getProperty("user.dir"));
            }
        });
        
        this.cancelButton.addActionListener((ActionEvent e) -> {
            
            this.yearTextField.setText("");
            
            
        });
        
        
    }
    
    private void writeOutput(Map<Student, WeeklySchedule> lessonDisposition) {

        File outFile = new File(".", "AnnualLessonDisposition.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {

            while (lessonDisposition.size() > 0) {

                Map.Entry<Student, WeeklySchedule> dispositionSetEntry
                        = lessonDisposition.entrySet().iterator().next();

                lessonDisposition.remove(dispositionSetEntry.getKey());
                String keySt = dispositionSetEntry.getKey().toString() + "\n" + "\n";
                String vaSt = dispositionSetEntry.getValue().toString();
                writer.write(keySt);
                writer.write(vaSt);
                writer.flush();
            }
            
        } catch (Exception e) {

            System.out.println("No output file or path found");
        }
    }
    
    
}
