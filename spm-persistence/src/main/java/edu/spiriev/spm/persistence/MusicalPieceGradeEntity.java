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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author root_spiriev
 */
@Entity
@XmlRootElement
@Table(name = "musicalPiece_grade")

public class MusicalPieceGradeEntity implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "musicalPiece_grade_id")
    private Integer musicalPieceGradeId;
    @JoinColumn(name = "grade_id", referencedColumnName = "grade_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GradeEntity gradeId;
    @JoinColumn(name = "musicalPiece_id", referencedColumnName = "musicalPiece_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MusicalPiecesEntity musicalPieceId;

    public MusicalPieceGradeEntity(Integer musicalPieceGradeId) {
        this.musicalPieceGradeId = musicalPieceGradeId;
    }

    public MusicalPieceGradeEntity() {
    }
    
    

    public Integer getMusicalPieceGradeId() {
        return musicalPieceGradeId;
    }

    public void setMusicalPieceGradeId(Integer musicalPieceGradeId) {
        this.musicalPieceGradeId = musicalPieceGradeId;
    }

    public GradeEntity getGradeId() {
        return gradeId;
    }

    public void setGradeId(GradeEntity gradeId) {
        this.gradeId = gradeId;
    }

    public MusicalPiecesEntity getMusicalPieceId() {
        return musicalPieceId;
    }

    public void setMusicalPieceId(MusicalPiecesEntity musicalPieceId) {
        this.musicalPieceId = musicalPieceId;
    }
    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (musicalPieceGradeId != null ? musicalPieceGradeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MusicalPieceGradeEntity)) {
            return false;
        }
        MusicalPieceGradeEntity other = (MusicalPieceGradeEntity) object;
        if ((this.musicalPieceGradeId == null && other.musicalPieceGradeId != null) || (this.musicalPieceGradeId != null && !this.musicalPieceGradeId.equals(other.musicalPieceGradeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.spiriev.spm.persistence.MusicalPieceGradeEntity[ id=" + musicalPieceGradeId + " ]";
    }
    
}
