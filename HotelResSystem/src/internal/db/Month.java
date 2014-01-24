/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package internal.db;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author elit
 */
@Entity
@Table(name = "month")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Month.findAll", query = "SELECT m FROM Month m"),
    @NamedQuery(name = "Month.findByIdMonth", query = "SELECT m FROM Month m WHERE m.idMonth = :idMonth"),
    @NamedQuery(name = "Month.findByMonth", query = "SELECT m FROM Month m WHERE m.month = :month")})
public class Month implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_month")
    private Integer idMonth;
    @Column(name = "month")
    private String month;
    @OneToMany(mappedBy = "idMonth")
    private Collection<SpecificRes> specificResCollection;
    @OneToMany(mappedBy = "idMonth")
    private Collection<Analysis> analysisCollection;

    public Month() {
    }

    public Month(Integer idMonth) {
        this.idMonth = idMonth;
    }

    public Integer getIdMonth() {
        return idMonth;
    }

    public void setIdMonth(Integer idMonth) {
        this.idMonth = idMonth;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    @XmlTransient
    public Collection<SpecificRes> getSpecificResCollection() {
        return specificResCollection;
    }

    public void setSpecificResCollection(Collection<SpecificRes> specificResCollection) {
        this.specificResCollection = specificResCollection;
    }

    @XmlTransient
    public Collection<Analysis> getAnalysisCollection() {
        return analysisCollection;
    }

    public void setAnalysisCollection(Collection<Analysis> analysisCollection) {
        this.analysisCollection = analysisCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMonth != null ? idMonth.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Month)) {
            return false;
        }
        Month other = (Month) object;
        if ((this.idMonth == null && other.idMonth != null) || (this.idMonth != null && !this.idMonth.equals(other.idMonth))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return month;
    }
    
}
