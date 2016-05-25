/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.persistence;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author root_spiriev
 */
@Entity
@Table(name = "student_grade")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentGradeEntity.findAll", query = "SELECT s FROM StudentGradeEntity s"),
    @NamedQuery(name = "StudentGradeEntity.findByStudentGradeId", query = "SELECT s FROM StudentGradeEntity s WHERE s.studentGradeId = :studentGradeId")})
public class StudentGradeEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_grade_id")
    private Integer studentGradeId;
    @JoinColumn(name = "grade_id", referencedColumnName = "grade_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GradeEntity gradeId;
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private StudentEntity studentId;

    public StudentGradeEntity() {
    }

    public StudentGradeEntity(Integer studentGradeId) {
        this.studentGradeId = studentGradeId;
    }

    public StudentGradeEntity(GradeEntity gradeId) {
        this.gradeId = gradeId;
    }
    
    
    public Integer getStudentGradeId() {
        return studentGradeId;
    }

    public void setStudentGradeId(Integer studentGradeId) {
        this.studentGradeId = studentGradeId;
    }

    public GradeEntity getGradeId() {
        return gradeId;
    }

    public void setGradeId(GradeEntity gradeId) {
        this.gradeId = gradeId;
    }

    public StudentEntity getStudentId() {
        return studentId;
    }

    public void setStudentId(StudentEntity studentId) {
        this.studentId = studentId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studentGradeId != null ? studentGradeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentGradeEntity)) {
            return false;
        }
        StudentGradeEntity other = (StudentGradeEntity) object;
        if ((this.studentGradeId == null && other.studentGradeId != null) || (this.studentGradeId != null && !this.studentGradeId.equals(other.studentGradeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.spiriev.spm.persistence.StudentGradeEntity[ studentGradeId=" + studentGradeId + " gradeId=" + gradeId + " ]";
    }
    
}
