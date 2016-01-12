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
public class MusicalPiece {
    private String name;
    private String composer;
    private int complexity;
    private Grade grade;

    public MusicalPiece(String name, String composer, int complexity, 
                                                                Grade grade) {
        this.name = name;
        this.composer = composer;
        this.complexity = complexity;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "name: " + 
              name.trim() + ", composer: " + composer.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public int getComplexity() {
        return complexity;
    }

    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
    //work here on the algorithm
    
    
}
