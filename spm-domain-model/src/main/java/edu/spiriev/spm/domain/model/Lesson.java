/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.domain.model;

/**
 * Each lesson contains three musical pieces per week
 * @author root_spiriev
 */
public class Lesson {
    
    private MusicalPiece piece1;
    private MusicalPiece piece2;
    private MusicalPiece piece3;

    public Lesson(MusicalPiece piece1, MusicalPiece piece2, MusicalPiece piece3) {
        this.piece1 = piece1;
        this.piece2 = piece2;
        this.piece3 = piece3;
    }

    public MusicalPiece getPiece1() {
        return piece1;
    }

    public MusicalPiece getPiece2() {
        return piece2;
    }

    public MusicalPiece getPiece3() {
        return piece3;
    }

    public void setPiece1(MusicalPiece piece1) {
        this.piece1 = piece1;
    }

    public void setPiece2(MusicalPiece piece2) {
        this.piece2 = piece2;
    }

    public void setPiece3(MusicalPiece piece3) {
        this.piece3 = piece3;
    }
    
    

    @Override
    public String toString() {
        return "\n" + piece1 + "\n" + piece2 + "\n" + piece3 + "\n";
    }
    
}
