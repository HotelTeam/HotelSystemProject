/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package internal.db;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author elit
 */
@Entity
@Table(name = "simpleroom")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Simpleroom.findAll", query = "SELECT s FROM Simpleroom s"),
    @NamedQuery(name = "Simpleroom.findByIdRoom", query = "SELECT s FROM Simpleroom s WHERE s.idRoom = :idRoom"),
    @NamedQuery(name = "Simpleroom.findByAirCon", query = "SELECT s FROM Simpleroom s WHERE s.airCon = :airCon"),
    @NamedQuery(name = "Simpleroom.findByMultimedia", query = "SELECT s FROM Simpleroom s WHERE s.multimedia = :multimedia"),
    @NamedQuery(name = "Simpleroom.findByWiFi", query = "SELECT s FROM Simpleroom s WHERE s.wiFi = :wiFi"),
    @NamedQuery(name = "Simpleroom.findByTv", query = "SELECT s FROM Simpleroom s WHERE s.tv = :tv"),
    @NamedQuery(name = "Simpleroom.findByRefrigerator", query = "SELECT s FROM Simpleroom s WHERE s.refrigerator = :refrigerator")})
public class Simpleroom implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_room")
    private Integer idRoom;
    @Column(name = "air_con")
    private Boolean airCon;
    @Column(name = "multimedia")
    private Boolean multimedia;
    @Column(name = "wi_fi")
    private Boolean wiFi;
    @Column(name = "tv")
    private Boolean tv;
    @Column(name = "refrigerator")
    private Boolean refrigerator;
    @JoinColumn(name = "id_bed", referencedColumnName = "id_bed")
    @ManyToOne
    private BedType idBed;
    @JoinColumn(name = "id_room", referencedColumnName = "id_room", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Rooms rooms;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "simpleroom")
    private Suiteroom suiteroom;

    public Simpleroom() {
    }

    public Simpleroom(Integer idRoom) {
        this.idRoom = idRoom;
    }

    public Integer getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Integer idRoom) {
        this.idRoom = idRoom;
    }

    public Boolean getAirCon() {
        return airCon;
    }

    public void setAirCon(Boolean airCon) {
        this.airCon = airCon;
    }

    public Boolean getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(Boolean multimedia) {
        this.multimedia = multimedia;
    }

    public Boolean getWiFi() {
        return wiFi;
    }

    public void setWiFi(Boolean wiFi) {
        this.wiFi = wiFi;
    }

    public Boolean getTv() {
        return tv;
    }

    public void setTv(Boolean tv) {
        this.tv = tv;
    }

    public Boolean getRefrigerator() {
        return refrigerator;
    }

    public void setRefrigerator(Boolean refrigerator) {
        this.refrigerator = refrigerator;
    }

    public BedType getIdBed() {
        return idBed;
    }

    public void setIdBed(BedType idBed) {
        this.idBed = idBed;
    }

    public Rooms getRooms() {
        return rooms;
    }

    public void setRooms(Rooms rooms) {
        this.rooms = rooms;
    }

    public Suiteroom getSuiteroom() {
        return suiteroom;
    }

    public void setSuiteroom(Suiteroom suiteroom) {
        this.suiteroom = suiteroom;
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
        if (!(object instanceof Simpleroom)) {
            return false;
        }
        Simpleroom other = (Simpleroom) object;
        if ((this.idRoom == null && other.idRoom != null) || (this.idRoom != null && !this.idRoom.equals(other.idRoom))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "internal.db.Simpleroom[ idRoom=" + idRoom + " ]";
    }
    
}
