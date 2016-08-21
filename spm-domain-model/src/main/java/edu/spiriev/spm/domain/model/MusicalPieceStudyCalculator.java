/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.domain.model;

/**
 *
 * @author root_spiriev
 */
public class MusicalPieceStudyCalculator {
    
    final int maximumStudyPeriodInWeeks;

    public MusicalPieceStudyCalculator(int maximumStudyPeriodInWeeks) {
        this.maximumStudyPeriodInWeeks = maximumStudyPeriodInWeeks;
    }
    
    public int calculateStudyWeeks(Student student, MusicalPiece mPiece) {
        
        if ((mPiece.getComplexity() > 10 ^ mPiece.getComplexity() < 1) ||
             student.getAbility() > 10 ^ student.getAbility() < 1) {
            
            throw new IllegalArgumentException();
        }
        int weeks = 0;
        
        try {
            
            int learningCapability = student.getAbility() - mPiece.getComplexity();
            
            weeks = maximumStudyPeriodInWeeks - learningCapability;
        } catch (IllegalArgumentException e) {
            
            System.err.println("Student ability and musical piece complexity " +
                                "must  be numbers between 1 and 10 ");
        }
        
        return weeks;
    }
    
}
