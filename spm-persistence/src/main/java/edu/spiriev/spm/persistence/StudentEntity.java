/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.persistence;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author root_spiriev
 */
@Entity
@Table(name = "Student")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentEntity.findAll", query = "SELECT s FROM StudentEntity s"),
    @NamedQuery(name = "StudentEntity.findByStudentId", query = "SELECT s FROM StudentEntity s WHERE s.studentId = :studentId"),
    @NamedQuery(name = "StudentEntity.findByStudentName", query = "SELECT s FROM StudentEntity s WHERE s.studentName = :studentName"),
    @NamedQuery(name = "StudentEntity.findByAbility", query = "SELECT s FROM StudentEntity s WHERE s.ability = :ability")})
public class StudentEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "student_id")
    private Integer studentId;
    @Column(name = "student_name")
    private String studentName;
    @Column(name = "ability")
    private Integer ability;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "studentId", fetch = FetchType.LAZY)
    private StudentGradeEntity studentGradeEntity;

    public StudentEntity() {
    }

    public StudentEntity(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getAbility() {
        return ability;
    }

    public void setAbility(Integer ability) {
        this.ability = ability;
    }

    @XmlTransient
    public StudentGradeEntity getStudentGradeEntity() {
        return studentGradeEntity;
    }

    public void setStudentGradeEntity(StudentGradeEntity studentGradeEntity) {
        this.studentGradeEntity = studentGradeEntity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studentId != null ? studentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentEntity)) {
            return false;
        }
        StudentEntity other = (StudentEntity) object;
        if ((this.studentId == null && other.studentId != null) || (this.studentId != null && !this.studentId.equals(other.studentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.spiriev.spm.persistence.StudentEntity[ studentId=" + studentId + ", " + studentName + ", studentGradeEntity=" + studentGradeEntity +" ]";
    }
    
}
