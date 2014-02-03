/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package internal.db;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "reservation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reservation.findAll", query = "SELECT r FROM Reservation r"),
    @NamedQuery(name = "Reservation.findByIdRes", query = "SELECT r FROM Reservation r WHERE r.idRes = :idRes"),
    @NamedQuery(name = "Reservation.findByArrivalDate", query = "SELECT r FROM Reservation r WHERE r.arrivalDate = :arrivalDate"),
    @NamedQuery(name = "Reservation.findByDepartureDate", query = "SELECT r FROM Reservation r WHERE r.departureDate = :departureDate")})
public class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_res")
    private Integer idRes;
    @Column(name = "arrival_date")
    private String arrivalDate;
    @Column(name = "departure_date")
    private String departureDate;
    @JoinTable(name = "res_cl", joinColumns = {
        @JoinColumn(name = "id_res", referencedColumnName = "id_res")}, inverseJoinColumns = {
        @JoinColumn(name = "id_client", referencedColumnName = "id_client")})
    @ManyToMany
    private Collection<Client> clientCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reservation")
    private Collection<SpecificRes> specificResCollection;

    public Reservation() {
    }

    public Reservation(Integer idRes) {
        this.idRes = idRes;
    }

    public Integer getIdRes() {
        return idRes;
    }

    public void setIdRes(Integer idRes) {
        this.idRes = idRes;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    @XmlTransient
    public Collection<Client> getClientCollection() {
        return clientCollection;
    }

    public void setClientCollection(Collection<Client> clientCollection) {
        this.clientCollection = clientCollection;
    }

    @XmlTransient
    public Collection<SpecificRes> getSpecificResCollection() {
        return specificResCollection;
    }

    public void setSpecificResCollection(Collection<SpecificRes> specificResCollection) {
        this.specificResCollection = specificResCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRes != null ? idRes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservation)) {
            return false;
        }
        Reservation other = (Reservation) object;
        if ((this.idRes == null && other.idRes != null) || (this.idRes != null && !this.idRes.equals(other.idRes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "internal.db.Reservation[ idRes=" + idRes + " ]";
    }
    
}
