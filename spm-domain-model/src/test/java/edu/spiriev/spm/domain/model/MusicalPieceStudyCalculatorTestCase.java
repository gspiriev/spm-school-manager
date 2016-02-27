/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.domain.model;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author root_spiriev
 */
public class MusicalPieceStudyCalculatorTestCase {
    
    @Test
    public void testAbilty10Complexity10Weeks10() {
        
        Student ablity10Student = new Student("John Smith", Grade.FIRST, 10);
        MusicalPiece complexity10Piece = new MusicalPiece("Waltz", "Strauss", 10, Grade.FIRST);
                MusicalPieceStudyCalculator testCalculator = new MusicalPieceStudyCalculator(10);
        int actualNumberOfWeeks = testCalculator.calculateStudyWeeks(ablity10Student, complexity10Piece);
        Assert.assertEquals(10, actualNumberOfWeeks);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAbilty10Complexity11Weeks10() {
        
        Student ablity10Student = new Student("John Smith", Grade.FIRST, 10);
        MusicalPiece complexity11Piece = new MusicalPiece("Waltz", "Strauss", 11, Grade.FIRST);
                MusicalPieceStudyCalculator testCalculator = new MusicalPieceStudyCalculator(10);
        int actualNumberOfWeeks = testCalculator.calculateStudyWeeks(ablity10Student, complexity11Piece);
        Assert.assertEquals(11, actualNumberOfWeeks);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAbilty11Complexity11Weeks11() {
        
        Student ablity10Student = new Student("John Smith", Grade.FIRST, 11);
        MusicalPiece complexity11Piece = new MusicalPiece("Waltz", "Strauss", 11, Grade.FIRST);
                MusicalPieceStudyCalculator testCalculator = new MusicalPieceStudyCalculator(11);
        int actualNumberOfWeeks = testCalculator.calculateStudyWeeks(ablity10Student, complexity11Piece);
        Assert.assertEquals(11, actualNumberOfWeeks);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAbilty11Complexity10Weeks10() {
        
        Student ablity10Student = new Student("John Smith", Grade.FIRST, 11);
        MusicalPiece complexity11Piece = new MusicalPiece("Waltz", "Strauss", 10, Grade.FIRST);
                MusicalPieceStudyCalculator testCalculator = new MusicalPieceStudyCalculator(10);
        int actualNumberOfWeeks = testCalculator.calculateStudyWeeks(ablity10Student, complexity11Piece);
        Assert.assertEquals(11, actualNumberOfWeeks);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAbiltyMinus1Complexity10Weeks10() {
        
        Student ablity10Student = new Student("John Smith", Grade.FIRST, -1);
        MusicalPiece complexity11Piece = new MusicalPiece("Waltz", "Strauss", 10, Grade.FIRST);
                MusicalPieceStudyCalculator testCalculator = new MusicalPieceStudyCalculator(10);
        int actualNumberOfWeeks = testCalculator.calculateStudyWeeks(ablity10Student, complexity11Piece);
        Assert.assertEquals(11, actualNumberOfWeeks);
    }
    
    
}
