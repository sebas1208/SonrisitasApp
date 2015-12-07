/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gr1.sonrisitas.modelos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sebastian
 */
@Entity
@Table(name = "especialidad", catalog = "d5p5cglukdp5dk", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Especialidad.findAll", query = "SELECT e FROM Especialidad e"),
    @NamedQuery(name = "Especialidad.findByEspNombre", query = "SELECT e FROM Especialidad e WHERE e.espNombre = :espNombre"),
    @NamedQuery(name = "Especialidad.findByEspArea", query = "SELECT e FROM Especialidad e WHERE e.espArea = :espArea"),
    @NamedQuery(name = "Especialidad.findByEspActivo", query = "SELECT e FROM Especialidad e WHERE e.espActivo = :espActivo"),
    @NamedQuery(name = "Especialidad.findByEspFechaRegistro", query = "SELECT e FROM Especialidad e WHERE e.espFechaRegistro = :espFechaRegistro"),
    @NamedQuery(name = "Especialidad.findByEspId", query = "SELECT e FROM Especialidad e WHERE e.espId = :espId")})
public class Especialidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "esp_nombre")
    private String espNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "esp_area")
    private String espArea;
    @Column(name = "esp_activo")
    private Boolean espActivo;
    @Column(name = "esp_fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date espFechaRegistro;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "esp_id")
    private Long espId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "espId", fetch = FetchType.LAZY)
    private List<OdontologoEspecialidad> odontologoEspecialidadList;

    public Especialidad() {
    }

    public Especialidad(Long espId) {
        this.espId = espId;
    }

    public Especialidad(Long espId, String espNombre, String espArea) {
        this.espId = espId;
        this.espNombre = espNombre;
        this.espArea = espArea;
    }

    public String getEspNombre() {
        return espNombre;
    }

    public void setEspNombre(String espNombre) {
        this.espNombre = espNombre;
    }

    public String getEspArea() {
        return espArea;
    }

    public void setEspArea(String espArea) {
        this.espArea = espArea;
    }

    public Boolean getEspActivo() {
        return espActivo;
    }

    public void setEspActivo(Boolean espActivo) {
        this.espActivo = espActivo;
    }

    public Date getEspFechaRegistro() {
        return espFechaRegistro;
    }

    public void setEspFechaRegistro(Date espFechaRegistro) {
        this.espFechaRegistro = espFechaRegistro;
    }

    public Long getEspId() {
        return espId;
    }

    public void setEspId(Long espId) {
        this.espId = espId;
    }

    @XmlTransient
    public List<OdontologoEspecialidad> getOdontologoEspecialidadList() {
        return odontologoEspecialidadList;
    }

    public void setOdontologoEspecialidadList(List<OdontologoEspecialidad> odontologoEspecialidadList) {
        this.odontologoEspecialidadList = odontologoEspecialidadList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (espId != null ? espId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Especialidad)) {
            return false;
        }
        Especialidad other = (Especialidad) object;
        if ((this.espId == null && other.espId != null) || (this.espId != null && !this.espId.equals(other.espId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gr1.sonrisitas.modelos.Especialidad[ espId=" + espId + " ]";
    }
    
}
