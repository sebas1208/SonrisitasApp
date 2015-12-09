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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "historia_clinica_cabecera",  schema = "public")
public class HistoriaClinicaCabecera extends Model implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "hcc_antecedentes_familiares")
    private String hccAntecedentesFamiliares;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "hcc_antecedentes_personales")
    private String hccAntecedentesPersonales;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "hcc_enfermedad_actual")
    private String hccEnfermedadActual;
    @Column(name = "hcc_activo")
    private Boolean hccActivo;
    @Column(name = "hcc_fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date hccFechaRegistro;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "hcc_id")
    private Long hccId;
    @JoinColumn(name = "pac_id", referencedColumnName = "pac_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Paciente pacId;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hccId", fetch = FetchType.LAZY)
    private List<HistoriaClinicaDetalle> historiaClinicaDetalleList;

    public static Finder<Long,HistoriaClinicaCabecera> find = new Finder<Long,HistoriaClinicaCabecera>(Long.class, HistoriaClinicaCabecera.class);

    public HistoriaClinicaCabecera() {
    }

    public HistoriaClinicaCabecera(Long hccId) {
        this.hccId = hccId;
    }

    public HistoriaClinicaCabecera(Long hccId, String hccAntecedentesFamiliares, String hccAntecedentesPersonales, String hccEnfermedadActual) {
        this.hccId = hccId;
        this.hccAntecedentesFamiliares = hccAntecedentesFamiliares;
        this.hccAntecedentesPersonales = hccAntecedentesPersonales;
        this.hccEnfermedadActual = hccEnfermedadActual;
    }

    public String getHccAntecedentesFamiliares() {
        return hccAntecedentesFamiliares;
    }

    public void setHccAntecedentesFamiliares(String hccAntecedentesFamiliares) {
        this.hccAntecedentesFamiliares = hccAntecedentesFamiliares;
    }

    public String getHccAntecedentesPersonales() {
        return hccAntecedentesPersonales;
    }

    public void setHccAntecedentesPersonales(String hccAntecedentesPersonales) {
        this.hccAntecedentesPersonales = hccAntecedentesPersonales;
    }

    public String getHccEnfermedadActual() {
        return hccEnfermedadActual;
    }

    public void setHccEnfermedadActual(String hccEnfermedadActual) {
        this.hccEnfermedadActual = hccEnfermedadActual;
    }

    public Boolean getHccActivo() {
        return hccActivo;
    }

    public void setHccActivo(Boolean hccActivo) {
        this.hccActivo = hccActivo;
    }

    public Date getHccFechaRegistro() {
        return hccFechaRegistro;
    }

    public void setHccFechaRegistro(Date hccFechaRegistro) {
        this.hccFechaRegistro = hccFechaRegistro;
    }

    public Long getHccId() {
        return hccId;
    }

    public void setHccId(Long hccId) {
        this.hccId = hccId;
    }

    public Paciente getPacId() {
        return pacId;
    }

    public void setPacId(Paciente pacId) {
        this.pacId = pacId;
    }

    public List<HistoriaClinicaDetalle> getHistoriaClinicaDetalleList() {
        return historiaClinicaDetalleList;
    }

    public void setHistoriaClinicaDetalleList(List<HistoriaClinicaDetalle> historiaClinicaDetalleList) {
        this.historiaClinicaDetalleList = historiaClinicaDetalleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hccId != null ? hccId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoriaClinicaCabecera)) {
            return false;
        }
        HistoriaClinicaCabecera other = (HistoriaClinicaCabecera) object;
        if ((this.hccId == null && other.hccId != null) || (this.hccId != null && !this.hccId.equals(other.hccId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gr1.sonrisitas.modelos.HistoriaClinicaCabecera[ hccId=" + hccId + " ]";
    }

}
