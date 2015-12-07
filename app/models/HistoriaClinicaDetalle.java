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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "historia_clinica_detalle", catalog = "d5p5cglukdp5dk", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistoriaClinicaDetalle.findAll", query = "SELECT h FROM HistoriaClinicaDetalle h"),
    @NamedQuery(name = "HistoriaClinicaDetalle.findByHcdExploracionFisica", query = "SELECT h FROM HistoriaClinicaDetalle h WHERE h.hcdExploracionFisica = :hcdExploracionFisica"),
    @NamedQuery(name = "HistoriaClinicaDetalle.findByHcdDiagnostico", query = "SELECT h FROM HistoriaClinicaDetalle h WHERE h.hcdDiagnostico = :hcdDiagnostico"),
    @NamedQuery(name = "HistoriaClinicaDetalle.findByHcdEvolucion", query = "SELECT h FROM HistoriaClinicaDetalle h WHERE h.hcdEvolucion = :hcdEvolucion"),
    @NamedQuery(name = "HistoriaClinicaDetalle.findByHcdExamenesComplemetarios", query = "SELECT h FROM HistoriaClinicaDetalle h WHERE h.hcdExamenesComplemetarios = :hcdExamenesComplemetarios"),
    @NamedQuery(name = "HistoriaClinicaDetalle.findByHcdActivo", query = "SELECT h FROM HistoriaClinicaDetalle h WHERE h.hcdActivo = :hcdActivo"),
    @NamedQuery(name = "HistoriaClinicaDetalle.findByHcdFechaRegistro", query = "SELECT h FROM HistoriaClinicaDetalle h WHERE h.hcdFechaRegistro = :hcdFechaRegistro"),
    @NamedQuery(name = "HistoriaClinicaDetalle.findByHcdRecetaMedica", query = "SELECT h FROM HistoriaClinicaDetalle h WHERE h.hcdRecetaMedica = :hcdRecetaMedica"),
    @NamedQuery(name = "HistoriaClinicaDetalle.findByHcdId", query = "SELECT h FROM HistoriaClinicaDetalle h WHERE h.hcdId = :hcdId")})
public class HistoriaClinicaDetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "hcd_exploracion_fisica")
    private String hcdExploracionFisica;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "hcd_diagnostico")
    private String hcdDiagnostico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "hcd_evolucion")
    private String hcdEvolucion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "hcd_examenes_complemetarios")
    private String hcdExamenesComplemetarios;
    @Column(name = "hcd_activo")
    private Boolean hcdActivo;
    @Column(name = "hcd_fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date hcdFechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "hcd_receta_medica")
    private String hcdRecetaMedica;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "hcd_id")
    private Long hcdId;
    @JoinColumn(name = "hcc_id", referencedColumnName = "hcc_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private HistoriaClinicaCabecera hccId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hcdId", fetch = FetchType.LAZY)
    private List<AtencionMedica> atencionMedicaList;

    public HistoriaClinicaDetalle() {
    }

    public HistoriaClinicaDetalle(Long hcdId) {
        this.hcdId = hcdId;
    }

    public HistoriaClinicaDetalle(Long hcdId, String hcdExploracionFisica, String hcdDiagnostico, String hcdEvolucion, String hcdExamenesComplemetarios, String hcdRecetaMedica) {
        this.hcdId = hcdId;
        this.hcdExploracionFisica = hcdExploracionFisica;
        this.hcdDiagnostico = hcdDiagnostico;
        this.hcdEvolucion = hcdEvolucion;
        this.hcdExamenesComplemetarios = hcdExamenesComplemetarios;
        this.hcdRecetaMedica = hcdRecetaMedica;
    }

    public String getHcdExploracionFisica() {
        return hcdExploracionFisica;
    }

    public void setHcdExploracionFisica(String hcdExploracionFisica) {
        this.hcdExploracionFisica = hcdExploracionFisica;
    }

    public String getHcdDiagnostico() {
        return hcdDiagnostico;
    }

    public void setHcdDiagnostico(String hcdDiagnostico) {
        this.hcdDiagnostico = hcdDiagnostico;
    }

    public String getHcdEvolucion() {
        return hcdEvolucion;
    }

    public void setHcdEvolucion(String hcdEvolucion) {
        this.hcdEvolucion = hcdEvolucion;
    }

    public String getHcdExamenesComplemetarios() {
        return hcdExamenesComplemetarios;
    }

    public void setHcdExamenesComplemetarios(String hcdExamenesComplemetarios) {
        this.hcdExamenesComplemetarios = hcdExamenesComplemetarios;
    }

    public Boolean getHcdActivo() {
        return hcdActivo;
    }

    public void setHcdActivo(Boolean hcdActivo) {
        this.hcdActivo = hcdActivo;
    }

    public Date getHcdFechaRegistro() {
        return hcdFechaRegistro;
    }

    public void setHcdFechaRegistro(Date hcdFechaRegistro) {
        this.hcdFechaRegistro = hcdFechaRegistro;
    }

    public String getHcdRecetaMedica() {
        return hcdRecetaMedica;
    }

    public void setHcdRecetaMedica(String hcdRecetaMedica) {
        this.hcdRecetaMedica = hcdRecetaMedica;
    }

    public Long getHcdId() {
        return hcdId;
    }

    public void setHcdId(Long hcdId) {
        this.hcdId = hcdId;
    }

    public HistoriaClinicaCabecera getHccId() {
        return hccId;
    }

    public void setHccId(HistoriaClinicaCabecera hccId) {
        this.hccId = hccId;
    }

    @XmlTransient
    public List<AtencionMedica> getAtencionMedicaList() {
        return atencionMedicaList;
    }

    public void setAtencionMedicaList(List<AtencionMedica> atencionMedicaList) {
        this.atencionMedicaList = atencionMedicaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hcdId != null ? hcdId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoriaClinicaDetalle)) {
            return false;
        }
        HistoriaClinicaDetalle other = (HistoriaClinicaDetalle) object;
        if ((this.hcdId == null && other.hcdId != null) || (this.hcdId != null && !this.hcdId.equals(other.hcdId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gr1.sonrisitas.modelos.HistoriaClinicaDetalle[ hcdId=" + hcdId + " ]";
    }
    
}
