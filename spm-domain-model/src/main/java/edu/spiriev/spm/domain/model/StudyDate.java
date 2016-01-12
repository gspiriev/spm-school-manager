/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.domain.model;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author root_spiriev
 */
public class StudyDate implements Comparable<StudyDate>{
    private Date start;
    private Date end;

    public StudyDate(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
    
    @Override
    public String toString() {
        
        Format f = new SimpleDateFormat("dd/MM/yyyy");
        String startString = f.format(start);
        String endString = f.format(end);
        
        return startString + " - " + endString;
    }
    
    @Override
    public int compareTo(StudyDate anotherDate) {
        return anotherDate.getStart().compareTo(this.getStart());
    }
    
}
