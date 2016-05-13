/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.persistence;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author root_spiriev
 */
@Entity
@Table(name = "grade")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GradeEntity.findAll", query = "SELECT g FROM GradeEntity g"),
    @NamedQuery(name = "GradeEntity.findByGradeId", query = "SELECT g FROM GradeEntity g WHERE g.gradeId = :gradeId"),
    @NamedQuery(name = "GradeEntity.findByGradeName", query = "SELECT g FROM GradeEntity g WHERE g.gradeName = :gradeName")})
public class GradeEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "grade_id")
    private Integer gradeId;
    @Column(name = "grade_name")
    private String gradeName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gradeId", fetch = FetchType.LAZY)
    private Collection<StudentGradeEntity> studentGradeEntityCollection;

    public GradeEntity() {
    }

    public GradeEntity(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    @XmlTransient
    public Collection<StudentGradeEntity> getStudentGradeEntityCollection() {
        return studentGradeEntityCollection;
    }

    public void setStudentGradeEntityCollection(Collection<StudentGradeEntity> studentGradeEntityCollection) {
        this.studentGradeEntityCollection = studentGradeEntityCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gradeId != null ? gradeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GradeEntity)) {
            return false;
        }
        GradeEntity other = (GradeEntity) object;
        if ((this.gradeId == null && other.gradeId != null) || (this.gradeId != null && !this.gradeId.equals(other.gradeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.spiriev.spm.persistence.GradeEntity[ gradeId=" + gradeId + " ]";
    }
    
}
