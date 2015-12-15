/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.domain_model;

/**
 *
 * @author root_spiriev
 */
public class Student implements Comparable<Student>{
    private String name;
    private Grade grade;
    private int ability;

    public Student(String name, Grade grade, int ability) {
        this.name = name;
        this.grade = grade;
        this.ability = ability;
    }

    @Override
    public String toString() {
        return name + " " + grade + " grade";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public int getAbility() {
        return ability;
    }

    public void setAbility(int ability) {
        this.ability = ability;
    }

    @Override
    public int compareTo(Student st) {
        
        return this.getName().compareTo(st.getName());
    }
    
    
}
