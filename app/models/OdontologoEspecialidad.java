/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 *
 * @author sebastian
 */
@Entity
@Table(name = "odontologo_especialidad",  schema = "public")
public class OdontologoEspecialidad extends Model implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ode_id")
    private Long odeId;
    @JoinColumn(name = "esp_id", referencedColumnName = "esp_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private Especialidad espId;
    @JoinColumn(name = "odo_id", referencedColumnName = "odo_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Odontologo odoId;
    @Transient
    private String nombreEspecialidad;
    @Transient
    private String nombreArea;
    @Transient
    private Long idEspecialidad;

    public static Finder<Long,OdontologoEspecialidad> find = new Finder<Long,OdontologoEspecialidad>(Long.class, OdontologoEspecialidad.class);

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

    private String getNombreEspecialidad(){
        return this.espId.getEspNombre();
    }

    private void setNombreEspecialidad(String nombreEspecialidad){
        this.nombreEspecialidad = nombreEspecialidad;
    }

    private String getNombreArea(){
        return this.espId.getEspArea();
    }

    private void setNombreArea(String nombreArea){
        this.nombreArea = nombreArea;
    }

    public Long getIdEspecialidad(){
        return this.getEspId().getEspId();
    }

    public void setIdEspecialidad(Long idEspecialidad){
        this.idEspecialidad = idEspecialidad;
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
