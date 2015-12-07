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
@Table(name = "usuario", catalog = "d5p5cglukdp5dk", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByUsuUser", query = "SELECT u FROM Usuario u WHERE u.usuUser = :usuUser"),
    @NamedQuery(name = "Usuario.findByUsuPassword", query = "SELECT u FROM Usuario u WHERE u.usuPassword = :usuPassword"),
    @NamedQuery(name = "Usuario.findByUsuPreguntaRecuperacion", query = "SELECT u FROM Usuario u WHERE u.usuPreguntaRecuperacion = :usuPreguntaRecuperacion"),
    @NamedQuery(name = "Usuario.findByUsuRespuestaRecuperacion", query = "SELECT u FROM Usuario u WHERE u.usuRespuestaRecuperacion = :usuRespuestaRecuperacion"),
    @NamedQuery(name = "Usuario.findByUsuEmail", query = "SELECT u FROM Usuario u WHERE u.usuEmail = :usuEmail"),
    @NamedQuery(name = "Usuario.findByUsuActivo", query = "SELECT u FROM Usuario u WHERE u.usuActivo = :usuActivo"),
    @NamedQuery(name = "Usuario.findByUsuFechaRegistro", query = "SELECT u FROM Usuario u WHERE u.usuFechaRegistro = :usuFechaRegistro"),
    @NamedQuery(name = "Usuario.findByUsuId", query = "SELECT u FROM Usuario u WHERE u.usuId = :usuId")})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "usu_user")
    private String usuUser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "usu_password")
    private String usuPassword;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "usu_pregunta_recuperacion")
    private String usuPreguntaRecuperacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "usu_respuesta_recuperacion")
    private String usuRespuestaRecuperacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "usu_email")
    private String usuEmail;
    @Column(name = "usu_activo")
    private Boolean usuActivo;
    @Column(name = "usu_fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date usuFechaRegistro;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "usu_id")
    private Long usuId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuId", fetch = FetchType.LAZY)
    private List<Administrador> administradorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuId", fetch = FetchType.LAZY)
    private List<Paciente> pacienteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuId", fetch = FetchType.LAZY)
    private List<Odontologo> odontologoList;

    public Usuario() {
    }

    public Usuario(Long usuId) {
        this.usuId = usuId;
    }

    public Usuario(Long usuId, String usuUser, String usuPassword, String usuPreguntaRecuperacion, String usuRespuestaRecuperacion, String usuEmail) {
        this.usuId = usuId;
        this.usuUser = usuUser;
        this.usuPassword = usuPassword;
        this.usuPreguntaRecuperacion = usuPreguntaRecuperacion;
        this.usuRespuestaRecuperacion = usuRespuestaRecuperacion;
        this.usuEmail = usuEmail;
    }

    public String getUsuUser() {
        return usuUser;
    }

    public void setUsuUser(String usuUser) {
        this.usuUser = usuUser;
    }

    public String getUsuPassword() {
        return usuPassword;
    }

    public void setUsuPassword(String usuPassword) {
        this.usuPassword = usuPassword;
    }

    public String getUsuPreguntaRecuperacion() {
        return usuPreguntaRecuperacion;
    }

    public void setUsuPreguntaRecuperacion(String usuPreguntaRecuperacion) {
        this.usuPreguntaRecuperacion = usuPreguntaRecuperacion;
    }

    public String getUsuRespuestaRecuperacion() {
        return usuRespuestaRecuperacion;
    }

    public void setUsuRespuestaRecuperacion(String usuRespuestaRecuperacion) {
        this.usuRespuestaRecuperacion = usuRespuestaRecuperacion;
    }

    public String getUsuEmail() {
        return usuEmail;
    }

    public void setUsuEmail(String usuEmail) {
        this.usuEmail = usuEmail;
    }

    public Boolean getUsuActivo() {
        return usuActivo;
    }

    public void setUsuActivo(Boolean usuActivo) {
        this.usuActivo = usuActivo;
    }

    public Date getUsuFechaRegistro() {
        return usuFechaRegistro;
    }

    public void setUsuFechaRegistro(Date usuFechaRegistro) {
        this.usuFechaRegistro = usuFechaRegistro;
    }

    public Long getUsuId() {
        return usuId;
    }

    public void setUsuId(Long usuId) {
        this.usuId = usuId;
    }

    @XmlTransient
    public List<Administrador> getAdministradorList() {
        return administradorList;
    }

    public void setAdministradorList(List<Administrador> administradorList) {
        this.administradorList = administradorList;
    }

    @XmlTransient
    public List<Paciente> getPacienteList() {
        return pacienteList;
    }

    public void setPacienteList(List<Paciente> pacienteList) {
        this.pacienteList = pacienteList;
    }

    @XmlTransient
    public List<Odontologo> getOdontologoList() {
        return odontologoList;
    }

    public void setOdontologoList(List<Odontologo> odontologoList) {
        this.odontologoList = odontologoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuId != null ? usuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.usuId == null && other.usuId != null) || (this.usuId != null && !this.usuId.equals(other.usuId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gr1.sonrisitas.modelos.Usuario[ usuId=" + usuId + " ]";
    }
    
}
