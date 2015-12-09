/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "agenda_odontologo", schema = "public")
public class AgendaOdontologo extends Model implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "age_dia")
    private short ageDia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "age_hora_inicio")
    @Temporal(TemporalType.TIME)
    private Date ageHoraInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "age_hora_fin")
    @Temporal(TemporalType.TIME)
    private Date ageHoraFin;
    @Column(name = "age_activo")
    private Boolean ageActivo;
    @Column(name = "age_fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date ageFechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "age_dia_nombre")
    private String ageDiaNombre;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "age_id")
    private Long ageId;
    @JoinColumn(name = "odo_id", referencedColumnName = "odo_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Odontologo odoId;

    public static Finder<Long,AgendaOdontologo> find = new Finder<Long,AgendaOdontologo>(Long.class, AgendaOdontologo.class);

    public AgendaOdontologo() {
    }

    public AgendaOdontologo(Long ageId) {
        this.ageId = ageId;
    }

    public AgendaOdontologo(Long ageId, short ageDia, Date ageHoraInicio, Date ageHoraFin, String ageDiaNombre) {
        this.ageId = ageId;
        this.ageDia = ageDia;
        this.ageHoraInicio = ageHoraInicio;
        this.ageHoraFin = ageHoraFin;
        this.ageDiaNombre = ageDiaNombre;
    }

    public short getAgeDia() {
        return ageDia;
    }

    public void setAgeDia(short ageDia) {
        this.ageDia = ageDia;
    }

    public Date getAgeHoraInicio() {
        return ageHoraInicio;
    }

    public void setAgeHoraInicio(Date ageHoraInicio) {
        this.ageHoraInicio = ageHoraInicio;
    }

    public Date getAgeHoraFin() {
        return ageHoraFin;
    }

    public void setAgeHoraFin(Date ageHoraFin) {
        this.ageHoraFin = ageHoraFin;
    }

    public Boolean getAgeActivo() {
        return ageActivo;
    }

    public void setAgeActivo(Boolean ageActivo) {
        this.ageActivo = ageActivo;
    }

    public Date getAgeFechaRegistro() {
        return ageFechaRegistro;
    }

    public void setAgeFechaRegistro(Date ageFechaRegistro) {
        this.ageFechaRegistro = ageFechaRegistro;
    }

    public String getAgeDiaNombre() {
        return ageDiaNombre;
    }

    public void setAgeDiaNombre(String ageDiaNombre) {
        this.ageDiaNombre = ageDiaNombre;
    }

    public Long getAgeId() {
        return ageId;
    }

    public void setAgeId(Long ageId) {
        this.ageId = ageId;
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
        hash += (ageId != null ? ageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AgendaOdontologo)) {
            return false;
        }
        AgendaOdontologo other = (AgendaOdontologo) object;
        if ((this.ageId == null && other.ageId != null) || (this.ageId != null && !this.ageId.equals(other.ageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gr1.sonrisitas.modelos.AgendaOdontologo[ ageId=" + ageId + " ]";
    }

}
