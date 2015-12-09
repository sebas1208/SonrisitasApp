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
@Table(name = "paciente",  schema = "public")
public class Paciente extends Model implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "pac_cedula")
    private String pacCedula;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "pac_nombres")
    private String pacNombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "pac_apellidos")
    private String pacApellidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "pac_direccion")
    private String pacDireccion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pac_fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date pacFechaNacimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "pac_telefono")
    private String pacTelefono;
    @Size(max = 50)
    @Column(name = "pac_email")
    private String pacEmail;
    @Size(max = 25)
    @Column(name = "pac_seguro_medico")
    private String pacSeguroMedico;
    @Column(name = "pac_activo")
    private Boolean pacActivo;
    @Column(name = "pac_fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date pacFechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "pac_sexo")
    private String pacSexo;
    @Size(max = 20)
    @Column(name = "pac_estado_civil")
    private String pacEstadoCivil;
    @Size(max = 20)
    @Column(name = "pac_lugar_nacimiento")
    private String pacLugarNacimiento;
    @Size(max = 20)
    @Column(name = "pac_ocupacion")
    private String pacOcupacion;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pac_id")
    private Long pacId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pacId", fetch = FetchType.LAZY)
    private List<HistoriaClinicaCabecera> historiaClinicaCabeceraList;
    @JoinColumn(name = "usu_id", referencedColumnName = "usu_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuId;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pacId", fetch = FetchType.LAZY)
    private List<AtencionMedica> atencionMedicaList;

    public static Finder<Long,Paciente> find = new Finder<Long,Paciente>(Long.class, Paciente.class);

    public Paciente() {
    }

    public Paciente(Long pacId) {
        this.pacId = pacId;
    }

    public Paciente(Long pacId, String pacCedula, String pacNombres, String pacApellidos, String pacDireccion, Date pacFechaNacimiento, String pacTelefono, String pacSexo) {
        this.pacId = pacId;
        this.pacCedula = pacCedula;
        this.pacNombres = pacNombres;
        this.pacApellidos = pacApellidos;
        this.pacDireccion = pacDireccion;
        this.pacFechaNacimiento = pacFechaNacimiento;
        this.pacTelefono = pacTelefono;
        this.pacSexo = pacSexo;
    }

    public String getPacCedula() {
        return pacCedula;
    }

    public void setPacCedula(String pacCedula) {
        this.pacCedula = pacCedula;
    }

    public String getPacNombres() {
        return pacNombres;
    }

    public void setPacNombres(String pacNombres) {
        this.pacNombres = pacNombres;
    }

    public String getPacApellidos() {
        return pacApellidos;
    }

    public void setPacApellidos(String pacApellidos) {
        this.pacApellidos = pacApellidos;
    }

    public String getPacDireccion() {
        return pacDireccion;
    }

    public void setPacDireccion(String pacDireccion) {
        this.pacDireccion = pacDireccion;
    }

    public Date getPacFechaNacimiento() {
        return pacFechaNacimiento;
    }

    public void setPacFechaNacimiento(Date pacFechaNacimiento) {
        this.pacFechaNacimiento = pacFechaNacimiento;
    }

    public String getPacTelefono() {
        return pacTelefono;
    }

    public void setPacTelefono(String pacTelefono) {
        this.pacTelefono = pacTelefono;
    }

    public String getPacEmail() {
        return pacEmail;
    }

    public void setPacEmail(String pacEmail) {
        this.pacEmail = pacEmail;
    }

    public String getPacSeguroMedico() {
        return pacSeguroMedico;
    }

    public void setPacSeguroMedico(String pacSeguroMedico) {
        this.pacSeguroMedico = pacSeguroMedico;
    }

    public Boolean getPacActivo() {
        return pacActivo;
    }

    public void setPacActivo(Boolean pacActivo) {
        this.pacActivo = pacActivo;
    }

    public Date getPacFechaRegistro() {
        return pacFechaRegistro;
    }

    public void setPacFechaRegistro(Date pacFechaRegistro) {
        this.pacFechaRegistro = pacFechaRegistro;
    }

    public String getPacSexo() {
        return pacSexo;
    }

    public void setPacSexo(String pacSexo) {
        this.pacSexo = pacSexo;
    }

    public String getPacEstadoCivil() {
        return pacEstadoCivil;
    }

    public void setPacEstadoCivil(String pacEstadoCivil) {
        this.pacEstadoCivil = pacEstadoCivil;
    }

    public String getPacLugarNacimiento() {
        return pacLugarNacimiento;
    }

    public void setPacLugarNacimiento(String pacLugarNacimiento) {
        this.pacLugarNacimiento = pacLugarNacimiento;
    }

    public String getPacOcupacion() {
        return pacOcupacion;
    }

    public void setPacOcupacion(String pacOcupacion) {
        this.pacOcupacion = pacOcupacion;
    }

    public Long getPacId() {
        return pacId;
    }

    public void setPacId(Long pacId) {
        this.pacId = pacId;
    }

    public List<HistoriaClinicaCabecera> getHistoriaClinicaCabeceraList() {
        return historiaClinicaCabeceraList;
    }

    public void setHistoriaClinicaCabeceraList(List<HistoriaClinicaCabecera> historiaClinicaCabeceraList) {
        this.historiaClinicaCabeceraList = historiaClinicaCabeceraList;
    }

    public Usuario getUsuId() {
        return usuId;
    }

    public void setUsuId(Usuario usuId) {
        this.usuId = usuId;
    }


    public List<AtencionMedica> getAtencionMedicaList() {
        return atencionMedicaList;
    }

    public void setAtencionMedicaList(List<AtencionMedica> atencionMedicaList) {
        this.atencionMedicaList = atencionMedicaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pacId != null ? pacId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paciente)) {
            return false;
        }
        Paciente other = (Paciente) object;
        if ((this.pacId == null && other.pacId != null) || (this.pacId != null && !this.pacId.equals(other.pacId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gr1.sonrisitas.modelos.Paciente[ pacId=" + pacId + " ]";
    }

}
