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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 *
 * @author sebastian
 */
@Entity
@Table(name = "usuario",  schema = "public")
@SequenceGenerator(name="seq", initialValue=1, allocationSize=100)
public class Usuario extends Model implements Serializable {
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
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    private Long usuId;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuId", fetch = FetchType.LAZY)
    private List<Administrador> administradorList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuId", fetch = FetchType.LAZY)
    private List<Paciente> pacienteList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuId", fetch = FetchType.LAZY)
    private List<Odontologo> odontologoList;

    public static Finder<Long,Usuario> find = new Finder<Long,Usuario>(Long.class, Usuario.class);

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

    public List<Administrador> getAdministradorList() {
        return administradorList;
    }

    public void setAdministradorList(List<Administrador> administradorList) {
        this.administradorList = administradorList;
    }

    public List<Paciente> getPacienteList() {
        return pacienteList;
    }

    public void setPacienteList(List<Paciente> pacienteList) {
        this.pacienteList = pacienteList;
    }

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
