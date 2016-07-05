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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author root_spiriev
 */
@Entity
@Table(name = "dates")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DatesEntity.findAll", query = "SELECT d FROM DatesEntity d"),
    @NamedQuery(name = "DatesEntity.findByDateId", query = "SELECT d FROM DatesEntity d WHERE d.dateId = :dateId"),
    @NamedQuery(name = "DatesEntity.findByDateDay", query = "SELECT d FROM DatesEntity d WHERE d.dateDay = :dateDay"),
    @NamedQuery(name = "DatesEntity.findByDateMonth", query = "SELECT d FROM DatesEntity d WHERE d.dateMonth = :dateMonth"),
    @NamedQuery(name = "DatesEntity.findByDateYear", query = "SELECT d FROM DatesEntity d WHERE d.dateYear = :dateYear")})
public class DatesEntity implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "date_id")
    private Integer dateId;
    @Column(name = "date_day")
    private Integer dateDay;
    @Column(name = "date_month")
    private Integer dateMonth;
    @Column(name = "date_year")
    private Integer dateYear;

    public DatesEntity() {
    }

    public DatesEntity(Integer dateId) {
        this.dateId = dateId;
    }

    public Integer getDateId() {
        return dateId;
    }

    public void setDateId(Integer dateId) {
        this.dateId = dateId;
    }

    public Integer getDateDay() {
        return dateDay;
    }

    public void setDateDay(Integer dateDay) {
        this.dateDay = dateDay;
    }

    public Integer getDateMonth() {
        return dateMonth;
    }

    public void setDateMonth(Integer dateMonth) {
        this.dateMonth = dateMonth;
    }

    public Integer getDateYear() {
        return dateYear;
    }

    public void setDateYear(Integer dateYear) {
        this.dateYear = dateYear;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dateId != null ? dateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatesEntity)) {
            return false;
        }
        DatesEntity other = (DatesEntity) object;
        if ((this.dateId == null && other.dateId != null) || (this.dateId != null && !this.dateId.equals(other.dateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return dateDay + "/" + dateMonth + "/" + dateYear;
    }
    
}
