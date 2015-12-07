/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gr1.sonrisitas.modelos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sebastian
 */
@Entity
@Table(name = "odontologo_especialidad", catalog = "d5p5cglukdp5dk", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OdontologoEspecialidad.findAll", query = "SELECT o FROM OdontologoEspecialidad o"),
    @NamedQuery(name = "OdontologoEspecialidad.findByOdeId", query = "SELECT o FROM OdontologoEspecialidad o WHERE o.odeId = :odeId")})
public class OdontologoEspecialidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ode_id")
    private Long odeId;
    @JoinColumn(name = "esp_id", referencedColumnName = "esp_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Especialidad espId;
    @JoinColumn(name = "odo_id", referencedColumnName = "odo_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Odontologo odoId;

    public OdontologoEspecialidad() {
    }

    public OdontologoEspecialidad(Long odeId) {
        this.odeId = odeId;
    }

    public Long getOdeId() {
        return odeId;
    }

    public void setOdeId(Long odeId) {
        this.odeId = odeId;
    }

    public Especialidad getEspId() {
        return espId;
    }

    public void setEspId(Especialidad espId) {
        this.espId = espId;
    }

    public Odontologo getOdoId() {
        return odoId;
    }

    public void setOdoId(Odontologo odoId) {
        this.odoId = odoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (odeId != null ? odeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OdontologoEspecialidad)) {
            return false;
        }
        OdontologoEspecialidad other = (OdontologoEspecialidad) object;
        if ((this.odeId == null && other.odeId != null) || (this.odeId != null && !this.odeId.equals(other.odeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gr1.sonrisitas.modelos.OdontologoEspecialidad[ odeId=" + odeId + " ]";
    }
    
}
