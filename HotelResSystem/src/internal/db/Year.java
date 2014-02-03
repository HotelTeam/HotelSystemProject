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
@Table(name = "year")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Year.findAll", query = "SELECT y FROM Year y"),
    @NamedQuery(name = "Year.findByIdYear", query = "SELECT y FROM Year y WHERE y.idYear = :idYear"),
    @NamedQuery(name = "Year.findByYear", query = "SELECT y FROM Year y WHERE y.year = :year")})
public class Year implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_year")
    private Integer idYear;
    @Column(name = "year")
    private String year;
    @OneToMany(mappedBy = "idYear")
    private Collection<SpecificRes> specificResCollection;
    @OneToMany(mappedBy = "idYear")
    private Collection<Analysis> analysisCollection;

    public Year() {
    }

    public Year(Integer idYear) {
        this.idYear = idYear;
    }

    public Integer getIdYear() {
        return idYear;
    }

    public void setIdYear(Integer idYear) {
        this.idYear = idYear;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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
        hash += (idYear != null ? idYear.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Year)) {
            return false;
        }
        Year other = (Year) object;
        if ((this.idYear == null && other.idYear != null) || (this.idYear != null && !this.idYear.equals(other.idYear))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return year;
    }
    
}
