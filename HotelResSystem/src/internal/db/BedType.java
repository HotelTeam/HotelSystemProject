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
@Table(name = "bed_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BedType.findAll", query = "SELECT b FROM BedType b"),
    @NamedQuery(name = "BedType.findByIdBed", query = "SELECT b FROM BedType b WHERE b.idBed = :idBed"),
    @NamedQuery(name = "BedType.findByNumber", query = "SELECT b FROM BedType b WHERE b.number = :number")})
public class BedType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_bed")
    private Integer idBed;
    @Column(name = "number")
    private Integer number;
    @OneToMany(mappedBy = "idBed")
    private Collection<Simpleroom> simpleroomCollection;

    public BedType() {
    }

    public BedType(Integer idBed) {
        this.idBed = idBed;
    }

    public Integer getIdBed() {
        return idBed;
    }

    public void setIdBed(Integer idBed) {
        this.idBed = idBed;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @XmlTransient
    public Collection<Simpleroom> getSimpleroomCollection() {
        return simpleroomCollection;
    }

    public void setSimpleroomCollection(Collection<Simpleroom> simpleroomCollection) {
        this.simpleroomCollection = simpleroomCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBed != null ? idBed.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BedType)) {
            return false;
        }
        BedType other = (BedType) object;
        if ((this.idBed == null && other.idBed != null) || (this.idBed != null && !this.idBed.equals(other.idBed))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "internal.db.BedType[ idBed=" + idBed + " ]";
    }
    
}
