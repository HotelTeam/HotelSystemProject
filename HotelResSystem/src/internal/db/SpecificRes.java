/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package internal.db;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author elit
 */
@Entity
@Table(name = "specific_res")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SpecificRes.findAll", query = "SELECT s FROM SpecificRes s"),
    @NamedQuery(name = "SpecificRes.findByIdRoom", query = "SELECT s FROM SpecificRes s WHERE s.specificResPK.idRoom = :idRoom"),
    @NamedQuery(name = "SpecificRes.findByIdRes", query = "SELECT s FROM SpecificRes s WHERE s.specificResPK.idRes = :idRes"),
    @NamedQuery(name = "SpecificRes.findByPrice", query = "SELECT s FROM SpecificRes s WHERE s.price = :price")})
public class SpecificRes implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SpecificResPK specificResPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private Double price;
    @JoinColumn(name = "id_year", referencedColumnName = "id_year")
    @ManyToOne
    private Year idYear;
    @JoinColumn(name = "id_month", referencedColumnName = "id_month")
    @ManyToOne
    private Month idMonth;
    @JoinColumn(name = "id_res", referencedColumnName = "id_res", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Reservation reservation;
    @JoinColumn(name = "id_room", referencedColumnName = "id_room", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Rooms rooms;

    public SpecificRes() {
    }

    public SpecificRes(SpecificResPK specificResPK) {
        this.specificResPK = specificResPK;
    }

    public SpecificRes(int idRoom, int idRes) {
        this.specificResPK = new SpecificResPK(idRoom, idRes);
    }

    public SpecificResPK getSpecificResPK() {
        return specificResPK;
    }

    public void setSpecificResPK(SpecificResPK specificResPK) {
        this.specificResPK = specificResPK;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Year getIdYear() {
        return idYear;
    }

    public void setIdYear(Year idYear) {
        this.idYear = idYear;
    }

    public Month getIdMonth() {
        return idMonth;
    }

    public void setIdMonth(Month idMonth) {
        this.idMonth = idMonth;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Rooms getRooms() {
        return rooms;
    }

    public void setRooms(Rooms rooms) {
        this.rooms = rooms;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (specificResPK != null ? specificResPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpecificRes)) {
            return false;
        }
        SpecificRes other = (SpecificRes) object;
        if ((this.specificResPK == null && other.specificResPK != null) || (this.specificResPK != null && !this.specificResPK.equals(other.specificResPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "internal.db.SpecificRes[ specificResPK=" + specificResPK + " ]";
    }
    
}
