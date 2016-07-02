/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.domain.model;

import java.util.Objects;

/**
 *
 * @author root_spiriev
 */
public class MusicalPiece implements Comparable<MusicalPiece> {
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.name);
        hash = 41 * hash + Objects.hashCode(this.composer);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MusicalPiece other = (MusicalPiece) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.composer, other.composer)) {
            return false;
        }
        return true;
    }
    
    
    @Override
    public int compareTo(MusicalPiece o) {
        return new Integer(this.hashCode()).compareTo(this.hashCode());
    }
    
    
    
    
}
