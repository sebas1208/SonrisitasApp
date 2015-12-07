/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gr1.sonrisitas.modelos;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sebastian
 */
@Entity
@Table(name = "atencion_medica", catalog = "d5p5cglukdp5dk", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AtencionMedica.findAll", query = "SELECT a FROM AtencionMedica a"),
    @NamedQuery(name = "AtencionMedica.findByAtmFecha", query = "SELECT a FROM AtencionMedica a WHERE a.atmFecha = :atmFecha"),
    @NamedQuery(name = "AtencionMedica.findByAtmHoraInicio", query = "SELECT a FROM AtencionMedica a WHERE a.atmHoraInicio = :atmHoraInicio"),
    @NamedQuery(name = "AtencionMedica.findByAtmHoraFin", query = "SELECT a FROM AtencionMedica a WHERE a.atmHoraFin = :atmHoraFin"),
    @NamedQuery(name = "AtencionMedica.findByAtmActivo", query = "SELECT a FROM AtencionMedica a WHERE a.atmActivo = :atmActivo"),
    @NamedQuery(name = "AtencionMedica.findByAtmFechaRegistro", query = "SELECT a FROM AtencionMedica a WHERE a.atmFechaRegistro = :atmFechaRegistro"),
    @NamedQuery(name = "AtencionMedica.findByAtmId", query = "SELECT a FROM AtencionMedica a WHERE a.atmId = :atmId")})
public class AtencionMedica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "atm_fecha")
    @Temporal(TemporalType.DATE)
    private Date atmFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "atm_hora_inicio")
    @Temporal(TemporalType.TIME)
    private Date atmHoraInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "atm_hora_fin")
    @Temporal(TemporalType.TIME)
    private Date atmHoraFin;
    @Column(name = "atm_activo")
    private Boolean atmActivo;
    @Column(name = "atm_fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date atmFechaRegistro;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "atm_id")
    private Long atmId;
    @JoinColumn(name = "hcd_id", referencedColumnName = "hcd_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private HistoriaClinicaDetalle hcdId;
    @JoinColumn(name = "odo_id", referencedColumnName = "odo_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Odontologo odoId;
    @JoinColumn(name = "pac_id", referencedColumnName = "pac_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Paciente pacId;
    @JoinColumn(name = "tam_id", referencedColumnName = "tam_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoAtencionMedica tamId;

    public AtencionMedica() {
    }

    public AtencionMedica(Long atmId) {
        this.atmId = atmId;
    }

    public AtencionMedica(Long atmId, Date atmFecha, Date atmHoraInicio, Date atmHoraFin) {
        this.atmId = atmId;
        this.atmFecha = atmFecha;
        this.atmHoraInicio = atmHoraInicio;
        this.atmHoraFin = atmHoraFin;
    }

    public Date getAtmFecha() {
        return atmFecha;
    }

    public void setAtmFecha(Date atmFecha) {
        this.atmFecha = atmFecha;
    }

    public Date getAtmHoraInicio() {
        return atmHoraInicio;
    }

    public void setAtmHoraInicio(Date atmHoraInicio) {
        this.atmHoraInicio = atmHoraInicio;
    }

    public Date getAtmHoraFin() {
        return atmHoraFin;
    }

    public void setAtmHoraFin(Date atmHoraFin) {
        this.atmHoraFin = atmHoraFin;
    }

    public Boolean getAtmActivo() {
        return atmActivo;
    }

    public void setAtmActivo(Boolean atmActivo) {
        this.atmActivo = atmActivo;
    }

    public Date getAtmFechaRegistro() {
        return atmFechaRegistro;
    }

    public void setAtmFechaRegistro(Date atmFechaRegistro) {
        this.atmFechaRegistro = atmFechaRegistro;
    }

    public Long getAtmId() {
        return atmId;
    }

    public void setAtmId(Long atmId) {
        this.atmId = atmId;
    }

    public HistoriaClinicaDetalle getHcdId() {
        return hcdId;
    }

    public void setHcdId(HistoriaClinicaDetalle hcdId) {
        this.hcdId = hcdId;
    }

    public Odontologo getOdoId() {
        return odoId;
    }

    public void setOdoId(Odontologo odoId) {
        this.odoId = odoId;
    }

    public Paciente getPacId() {
        return pacId;
    }

    public void setPacId(Paciente pacId) {
        this.pacId = pacId;
    }

    public TipoAtencionMedica getTamId() {
        return tamId;
    }

    public void setTamId(TipoAtencionMedica tamId) {
        this.tamId = tamId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (atmId != null ? atmId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AtencionMedica)) {
            return false;
        }
        AtencionMedica other = (AtencionMedica) object;
        if ((this.atmId == null && other.atmId != null) || (this.atmId != null && !this.atmId.equals(other.atmId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gr1.sonrisitas.modelos.AtencionMedica[ atmId=" + atmId + " ]";
    }
    
}
