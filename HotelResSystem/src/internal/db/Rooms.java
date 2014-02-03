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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author elit
 */
@Entity
@Table(name = "rooms")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rooms.findAll", query = "SELECT r FROM Rooms r"),
    @NamedQuery(name = "Rooms.findByIdRoom", query = "SELECT r FROM Rooms r WHERE r.idRoom = :idRoom"),
    @NamedQuery(name = "Rooms.findByNumber", query = "SELECT r FROM Rooms r WHERE r.number = :number"),
    @NamedQuery(name = "Rooms.findByPrice", query = "SELECT r FROM Rooms r WHERE r.price = :price"),
    @NamedQuery(name = "Rooms.findByOffer", query = "SELECT r FROM Rooms r WHERE r.offer = :offer")})
public class Rooms implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_room")
    private Integer idRoom;
    @Basic(optional = false)
    @Column(name = "number")
    private int number;
    @Column(name = "price")
    private Integer price;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "offer")
    private Double offer;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "rooms")
    private Simpleroom simpleroom;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rooms")
    private Collection<SpecificRes> specificResCollection;

    public Rooms() {
    }

    public Rooms(Integer idRoom) {
        this.idRoom = idRoom;
    }

    public Rooms(Integer idRoom, int number) {
        this.idRoom = idRoom;
        this.number = number;
    }

    public Integer getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Integer idRoom) {
        this.idRoom = idRoom;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Double getOffer() {
        return offer;
    }

    public void setOffer(Double offer) {
        this.offer = offer;
    }

    public Simpleroom getSimpleroom() {
        return simpleroom;
    }

    public void setSimpleroom(Simpleroom simpleroom) {
        this.simpleroom = simpleroom;
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
        hash += (idRoom != null ? idRoom.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rooms)) {
            return false;
        }
        Rooms other = (Rooms) object;
        if ((this.idRoom == null && other.idRoom != null) || (this.idRoom != null && !this.idRoom.equals(other.idRoom))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "internal.db.Rooms[ idRoom=" + idRoom + " ]";
    }
    
}
