/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package internal.db;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author elit
 */
@Embeddable
public class SpecificResPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_room")
    private int idRoom;
    @Basic(optional = false)
    @Column(name = "id_res")
    private int idRes;

    public SpecificResPK() {
    }

    public SpecificResPK(int idRoom, int idRes) {
        this.idRoom = idRoom;
        this.idRes = idRes;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public int getIdRes() {
        return idRes;
    }

    public void setIdRes(int idRes) {
        this.idRes = idRes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idRoom;
        hash += (int) idRes;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpecificResPK)) {
            return false;
        }
        SpecificResPK other = (SpecificResPK) object;
        if (this.idRoom != other.idRoom) {
            return false;
        }
        if (this.idRes != other.idRes) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "internal.db.SpecificResPK[ idRoom=" + idRoom + ", idRes=" + idRes + " ]";
    }
    
}
