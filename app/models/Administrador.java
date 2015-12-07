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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sebastian
 */
@Entity
@Table(name = "administrador", catalog = "d5p5cglukdp5dk", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Administrador.findAll", query = "SELECT a FROM Administrador a"),
    @NamedQuery(name = "Administrador.findByAdmId", query = "SELECT a FROM Administrador a WHERE a.admId = :admId"),
    @NamedQuery(name = "Administrador.findByAdmNombre", query = "SELECT a FROM Administrador a WHERE a.admNombre = :admNombre"),
    @NamedQuery(name = "Administrador.findByAdmApellido", query = "SELECT a FROM Administrador a WHERE a.admApellido = :admApellido"),
    @NamedQuery(name = "Administrador.findByAdmDireccion", query = "SELECT a FROM Administrador a WHERE a.admDireccion = :admDireccion"),
    @NamedQuery(name = "Administrador.findByAdmTelefono", query = "SELECT a FROM Administrador a WHERE a.admTelefono = :admTelefono"),
    @NamedQuery(name = "Administrador.findByAdmEmail", query = "SELECT a FROM Administrador a WHERE a.admEmail = :admEmail"),
    @NamedQuery(name = "Administrador.findByAdmActivo", query = "SELECT a FROM Administrador a WHERE a.admActivo = :admActivo"),
    @NamedQuery(name = "Administrador.findByAdmFechaRegistro", query = "SELECT a FROM Administrador a WHERE a.admFechaRegistro = :admFechaRegistro")})
public class Administrador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "adm_id")
    private Long admId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "adm_nombre")
    private String admNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "adm_apellido")
    private String admApellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "adm_direccion")
    private String admDireccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "adm_telefono")
    private String admTelefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "adm_email")
    private String admEmail;
    @Column(name = "adm_activo")
    private Boolean admActivo;
    @Column(name = "adm_fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date admFechaRegistro;
    @JoinColumn(name = "usu_id", referencedColumnName = "usu_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuId;

    public Administrador() {
    }

    public Administrador(Long admId) {
        this.admId = admId;
    }

    public Administrador(Long admId, String admNombre, String admApellido, String admDireccion, String admTelefono, String admEmail) {
        this.admId = admId;
        this.admNombre = admNombre;
        this.admApellido = admApellido;
        this.admDireccion = admDireccion;
        this.admTelefono = admTelefono;
        this.admEmail = admEmail;
    }

    public Long getAdmId() {
        return admId;
    }

    public void setAdmId(Long admId) {
        this.admId = admId;
    }

    public String getAdmNombre() {
        return admNombre;
    }

    public void setAdmNombre(String admNombre) {
        this.admNombre = admNombre;
    }

    public String getAdmApellido() {
        return admApellido;
    }

    public void setAdmApellido(String admApellido) {
        this.admApellido = admApellido;
    }

    public String getAdmDireccion() {
        return admDireccion;
    }

    public void setAdmDireccion(String admDireccion) {
        this.admDireccion = admDireccion;
    }

    public String getAdmTelefono() {
        return admTelefono;
    }

    public void setAdmTelefono(String admTelefono) {
        this.admTelefono = admTelefono;
    }

    public String getAdmEmail() {
        return admEmail;
    }

    public void setAdmEmail(String admEmail) {
        this.admEmail = admEmail;
    }

    public Boolean getAdmActivo() {
        return admActivo;
    }

    public void setAdmActivo(Boolean admActivo) {
        this.admActivo = admActivo;
    }

    public Date getAdmFechaRegistro() {
        return admFechaRegistro;
    }

    public void setAdmFechaRegistro(Date admFechaRegistro) {
        this.admFechaRegistro = admFechaRegistro;
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
        hash += (admId != null ? admId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Administrador)) {
            return false;
        }
        Administrador other = (Administrador) object;
        if ((this.admId == null && other.admId != null) || (this.admId != null && !this.admId.equals(other.admId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gr1.sonrisitas.modelos.Administrador[ admId=" + admId + " ]";
    }
    
}
