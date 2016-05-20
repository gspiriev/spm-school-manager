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
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "MusicalPieces")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MusicalPiecesEntity.findAll", query = "SELECT m FROM MusicalPiecesEntity m"),
    @NamedQuery(name = "MusicalPiecesEntity.findByMusicalPieceid", query = "SELECT m FROM MusicalPiecesEntity m WHERE m.musicalPieceId = :musicalPieceId"),
    @NamedQuery(name = "MusicalPiecesEntity.findByComposer", query = "SELECT m FROM MusicalPiecesEntity m WHERE m.composer = :composer"),
    @NamedQuery(name = "MusicalPiecesEntity.findByPieceName", query = "SELECT m FROM MusicalPiecesEntity m WHERE m.pieceName = :pieceName"),
    @NamedQuery(name = "MusicalPiecesEntity.findByComplexity", query = "SELECT m FROM MusicalPiecesEntity m WHERE m.complexity = :complexity")})
public class MusicalPiecesEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "musicalPiece_id")
    private int musicalPieceId;
    @Column(name = "composer")
    private String composer;
    @Column(name = "piece_name")
    private String pieceName;
    @Column(name = "complexity")
    private Integer complexity;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "musicalPieceId", fetch = FetchType.LAZY)
    protected MusicalPieceGradeEntity musicalPiecesGradeEntity;

    public MusicalPiecesEntity() {
    }

    @XmlTransient
    public MusicalPieceGradeEntity getMusicalPieceGradeEntity() {
        return this.musicalPiecesGradeEntity;
    }
   
    
    public int getMusicalPieceid() {
        return musicalPieceId;
    }

    public void setMusicalPieceid(int musicalPieceid) {
        this.musicalPieceId = musicalPieceid;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public String getPieceName() {
        return pieceName;
    }

    public void setPieceName(String pieceName) {
        this.pieceName = pieceName;
    }

    public Integer getComplexity() {
        return complexity;
    }

    public void setComplexity(Integer complexity) {
        this.complexity = complexity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (musicalPiecesGradeEntity != null ? musicalPiecesGradeEntity.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MusicalPiecesEntity)) {
            return false;
        }
        MusicalPiecesEntity other = (MusicalPiecesEntity) object;
        if ((this.musicalPiecesGradeEntity == null && other.musicalPiecesGradeEntity != null) || (this.musicalPiecesGradeEntity != null && !this.musicalPiecesGradeEntity.equals(other.musicalPiecesGradeEntity))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.spiriev.spm.persistence.MusicalPiecesEntity[ musicalPieceId=" + musicalPieceId + " musicalPieceName=" + pieceName + " grade=" + musicalPiecesGradeEntity + " ]";
    }
    
}
