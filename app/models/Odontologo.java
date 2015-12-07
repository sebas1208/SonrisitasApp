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
@Table(name = "odontologo", catalog = "d5p5cglukdp5dk", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Odontologo.findAll", query = "SELECT o FROM Odontologo o"),
    @NamedQuery(name = "Odontologo.findByOdoId", query = "SELECT o FROM Odontologo o WHERE o.odoId = :odoId"),
    @NamedQuery(name = "Odontologo.findByOdoNombres", query = "SELECT o FROM Odontologo o WHERE o.odoNombres = :odoNombres"),
    @NamedQuery(name = "Odontologo.findByOdoApellidos", query = "SELECT o FROM Odontologo o WHERE o.odoApellidos = :odoApellidos"),
    @NamedQuery(name = "Odontologo.findByOdoDireccion", query = "SELECT o FROM Odontologo o WHERE o.odoDireccion = :odoDireccion"),
    @NamedQuery(name = "Odontologo.findByOdoTelefono", query = "SELECT o FROM Odontologo o WHERE o.odoTelefono = :odoTelefono"),
    @NamedQuery(name = "Odontologo.findByOdoEmail", query = "SELECT o FROM Odontologo o WHERE o.odoEmail = :odoEmail"),
    @NamedQuery(name = "Odontologo.findByOdoCedula", query = "SELECT o FROM Odontologo o WHERE o.odoCedula = :odoCedula"),
    @NamedQuery(name = "Odontologo.findByOdoActivo", query = "SELECT o FROM Odontologo o WHERE o.odoActivo = :odoActivo"),
    @NamedQuery(name = "Odontologo.findByOdoFechaRegistro", query = "SELECT o FROM Odontologo o WHERE o.odoFechaRegistro = :odoFechaRegistro")})
public class Odontologo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "odo_id")
    private Long odoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "odo_nombres")
    private String odoNombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "odo_apellidos")
    private String odoApellidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "odo_direccion")
    private String odoDireccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "odo_telefono")
    private String odoTelefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "odo_email")
    private String odoEmail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "odo_cedula")
    private String odoCedula;
    @Column(name = "odo_activo")
    private Boolean odoActivo;
    @Column(name = "odo_fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date odoFechaRegistro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "odoId", fetch = FetchType.LAZY)
    private List<AgendaOdontologo> agendaOdontologoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "odoId", fetch = FetchType.LAZY)
    private List<AtencionMedica> atencionMedicaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "odoId", fetch = FetchType.LAZY)
    private List<OdontologoEspecialidad> odontologoEspecialidadList;
    @JoinColumn(name = "usu_id", referencedColumnName = "usu_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuId;

    public Odontologo() {
    }

    public Odontologo(Long odoId) {
        this.odoId = odoId;
    }

    public Odontologo(Long odoId, String odoNombres, String odoApellidos, String odoDireccion, String odoTelefono, String odoEmail, String odoCedula) {
        this.odoId = odoId;
        this.odoNombres = odoNombres;
        this.odoApellidos = odoApellidos;
        this.odoDireccion = odoDireccion;
        this.odoTelefono = odoTelefono;
        this.odoEmail = odoEmail;
        this.odoCedula = odoCedula;
    }

    public Long getOdoId() {
        return odoId;
    }

    public void setOdoId(Long odoId) {
        this.odoId = odoId;
    }

    public String getOdoNombres() {
        return odoNombres;
    }

    public void setOdoNombres(String odoNombres) {
        this.odoNombres = odoNombres;
    }

    public String getOdoApellidos() {
        return odoApellidos;
    }

    public void setOdoApellidos(String odoApellidos) {
        this.odoApellidos = odoApellidos;
    }

    public String getOdoDireccion() {
        return odoDireccion;
    }

    public void setOdoDireccion(String odoDireccion) {
        this.odoDireccion = odoDireccion;
    }

    public String getOdoTelefono() {
        return odoTelefono;
    }

    public void setOdoTelefono(String odoTelefono) {
        this.odoTelefono = odoTelefono;
    }

    public String getOdoEmail() {
        return odoEmail;
    }

    public void setOdoEmail(String odoEmail) {
        this.odoEmail = odoEmail;
    }

    public String getOdoCedula() {
        return odoCedula;
    }

    public void setOdoCedula(String odoCedula) {
        this.odoCedula = odoCedula;
    }

    public Boolean getOdoActivo() {
        return odoActivo;
    }

    public void setOdoActivo(Boolean odoActivo) {
        this.odoActivo = odoActivo;
    }

    public Date getOdoFechaRegistro() {
        return odoFechaRegistro;
    }

    public void setOdoFechaRegistro(Date odoFechaRegistro) {
        this.odoFechaRegistro = odoFechaRegistro;
    }

    @XmlTransient
    public List<AgendaOdontologo> getAgendaOdontologoList() {
        return agendaOdontologoList;
    }

    public void setAgendaOdontologoList(List<AgendaOdontologo> agendaOdontologoList) {
        this.agendaOdontologoList = agendaOdontologoList;
    }

    @XmlTransient
    public List<AtencionMedica> getAtencionMedicaList() {
        return atencionMedicaList;
    }

    public void setAtencionMedicaList(List<AtencionMedica> atencionMedicaList) {
        this.atencionMedicaList = atencionMedicaList;
    }

    @XmlTransient
    public List<OdontologoEspecialidad> getOdontologoEspecialidadList() {
        return odontologoEspecialidadList;
    }

    public void setOdontologoEspecialidadList(List<OdontologoEspecialidad> odontologoEspecialidadList) {
        this.odontologoEspecialidadList = odontologoEspecialidadList;
    }

    public Usuario getUsuId() {
        return usuId;
    }

    public void setUsuId(Usuario usuId) {
        this.usuId = usuId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (odoId != null ? odoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Odontologo)) {
            return false;
        }
        Odontologo other = (Odontologo) object;
        if ((this.odoId == null && other.odoId != null) || (this.odoId != null && !this.odoId.equals(other.odoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gr1.sonrisitas.modelos.Odontologo[ odoId=" + odoId + " ]";
    }
    
}
