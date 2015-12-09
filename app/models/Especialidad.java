/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 *
 * @author sebastian
 */
@Entity
@Table(name = "especialidad",  schema = "public")
public class Especialidad extends Model implements Serializable {
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
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "espId", fetch = FetchType.LAZY)
    private List<OdontologoEspecialidad> odontologoEspecialidadList;

    public static Finder<Long,Especialidad> find = new Finder<Long,Especialidad>(Long.class, Especialidad.class);

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
