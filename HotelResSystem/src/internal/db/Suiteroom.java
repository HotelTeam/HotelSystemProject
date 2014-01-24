/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package internal.db;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "suiteroom")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Suiteroom.findAll", query = "SELECT s FROM Suiteroom s"),
    @NamedQuery(name = "Suiteroom.findByIdRoom", query = "SELECT s FROM Suiteroom s WHERE s.idRoom = :idRoom"),
    @NamedQuery(name = "Suiteroom.findByJacuzzi", query = "SELECT s FROM Suiteroom s WHERE s.jacuzzi = :jacuzzi"),
    @NamedQuery(name = "Suiteroom.findByBreakfast", query = "SELECT s FROM Suiteroom s WHERE s.breakfast = :breakfast"),
    @NamedQuery(name = "Suiteroom.findByMeal", query = "SELECT s FROM Suiteroom s WHERE s.meal = :meal"),
    @NamedQuery(name = "Suiteroom.findByDinner", query = "SELECT s FROM Suiteroom s WHERE s.dinner = :dinner")})
public class Suiteroom implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_room")
    private Integer idRoom;
    @Column(name = "jacuzzi")
    private Boolean jacuzzi;
    @Column(name = "breakfast")
    private Boolean breakfast;
    @Column(name = "meal")
    private Boolean meal;
    @Column(name = "dinner")
    private Boolean dinner;
    @JoinColumn(name = "id_room", referencedColumnName = "id_room", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Simpleroom simpleroom;

    public Suiteroom() {
    }

    public Suiteroom(Integer idRoom) {
        this.idRoom = idRoom;
    }

    public Integer getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Integer idRoom) {
        this.idRoom = idRoom;
    }

    public Boolean getJacuzzi() {
        return jacuzzi;
    }

    public void setJacuzzi(Boolean jacuzzi) {
        this.jacuzzi = jacuzzi;
    }

    public Boolean getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(Boolean breakfast) {
        this.breakfast = breakfast;
    }

    public Boolean getMeal() {
        return meal;
    }

    public void setMeal(Boolean meal) {
        this.meal = meal;
    }

    public Boolean getDinner() {
        return dinner;
    }

    public void setDinner(Boolean dinner) {
        this.dinner = dinner;
    }

    public Simpleroom getSimpleroom() {
        return simpleroom;
    }

    public void setSimpleroom(Simpleroom simpleroom) {
        this.simpleroom = simpleroom;
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
        if (!(object instanceof Suiteroom)) {
            return false;
        }
        Suiteroom other = (Suiteroom) object;
        if ((this.idRoom == null && other.idRoom != null) || (this.idRoom != null && !this.idRoom.equals(other.idRoom))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "internal.db.Suiteroom[ idRoom=" + idRoom + " ]";
    }
    
}
