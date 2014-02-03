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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "analysis")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Analysis.findAll", query = "SELECT a FROM Analysis a"),
    @NamedQuery(name = "Analysis.findByIdAn", query = "SELECT a FROM Analysis a WHERE a.idAn = :idAn"),
    @NamedQuery(name = "Analysis.findByProfit", query = "SELECT a FROM Analysis a WHERE a.profit = :profit"),
    @NamedQuery(name = "Analysis.findByCost", query = "SELECT a FROM Analysis a WHERE a.cost = :cost"),
    @NamedQuery(name = "Analysis.findByYearAndMonth", query = "SELECT a FROM Analysis a WHERE a.idYear.year = :year AND a.idMonth.month = :month")})
public class Analysis implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_an")
    private Integer idAn;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "profit")
    private Double profit;
    @Column(name = "cost")
    private Double cost;
    @JoinColumn(name = "id_year", referencedColumnName = "id_year")
    @ManyToOne
    private Year idYear;
    @JoinColumn(name = "id_month", referencedColumnName = "id_month")
    @ManyToOne
    private Month idMonth;

    public Analysis() {
    }

    public Analysis(Integer idAn) {
        this.idAn = idAn;
    }

    public Integer getIdAn() {
        return idAn;
    }

    public void setIdAn(Integer idAn) {
        this.idAn = idAn;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAn != null ? idAn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Analysis)) {
            return false;
        }
        Analysis other = (Analysis) object;
        if ((this.idAn == null && other.idAn != null) || (this.idAn != null && !this.idAn.equals(other.idAn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idYear.getYear();
    }
    
}
