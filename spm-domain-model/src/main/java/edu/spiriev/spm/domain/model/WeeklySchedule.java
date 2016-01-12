/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.domain.model;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 *
 * @author root_spiriev
 */
public class WeeklySchedule {
    private final LinkedHashMap<StudyDate, Lesson> schedule;

    public WeeklySchedule(LinkedHashMap<StudyDate, Lesson> schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
      StringBuilder builder = new StringBuilder();
              
      while(this.schedule.size() > 0) {
          Map.Entry<StudyDate, Lesson> weeklySet = 
                                    this.schedule.entrySet().iterator().next();
          this.schedule.remove(weeklySet.getKey());
          builder.append(weeklySet.getKey().toString() + "\n" + "\n");
          builder.append(weeklySet.getValue().toString() + "\n" + "\n");
      }
      
      return builder.toString();
    }
        
    
    
    
    
}
