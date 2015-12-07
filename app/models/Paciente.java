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
@Table(name = "paciente", catalog = "d5p5cglukdp5dk", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paciente.findAll", query = "SELECT p FROM Paciente p"),
    @NamedQuery(name = "Paciente.findByPacCedula", query = "SELECT p FROM Paciente p WHERE p.pacCedula = :pacCedula"),
    @NamedQuery(name = "Paciente.findByPacNombres", query = "SELECT p FROM Paciente p WHERE p.pacNombres = :pacNombres"),
    @NamedQuery(name = "Paciente.findByPacApellidos", query = "SELECT p FROM Paciente p WHERE p.pacApellidos = :pacApellidos"),
    @NamedQuery(name = "Paciente.findByPacDireccion", query = "SELECT p FROM Paciente p WHERE p.pacDireccion = :pacDireccion"),
    @NamedQuery(name = "Paciente.findByPacFechaNacimiento", query = "SELECT p FROM Paciente p WHERE p.pacFechaNacimiento = :pacFechaNacimiento"),
    @NamedQuery(name = "Paciente.findByPacTelefono", query = "SELECT p FROM Paciente p WHERE p.pacTelefono = :pacTelefono"),
    @NamedQuery(name = "Paciente.findByPacEmail", query = "SELECT p FROM Paciente p WHERE p.pacEmail = :pacEmail"),
    @NamedQuery(name = "Paciente.findByPacSeguroMedico", query = "SELECT p FROM Paciente p WHERE p.pacSeguroMedico = :pacSeguroMedico"),
    @NamedQuery(name = "Paciente.findByPacActivo", query = "SELECT p FROM Paciente p WHERE p.pacActivo = :pacActivo"),
    @NamedQuery(name = "Paciente.findByPacFechaRegistro", query = "SELECT p FROM Paciente p WHERE p.pacFechaRegistro = :pacFechaRegistro"),
    @NamedQuery(name = "Paciente.findByPacSexo", query = "SELECT p FROM Paciente p WHERE p.pacSexo = :pacSexo"),
    @NamedQuery(name = "Paciente.findByPacEstadoCivil", query = "SELECT p FROM Paciente p WHERE p.pacEstadoCivil = :pacEstadoCivil"),
    @NamedQuery(name = "Paciente.findByPacLugarNacimiento", query = "SELECT p FROM Paciente p WHERE p.pacLugarNacimiento = :pacLugarNacimiento"),
    @NamedQuery(name = "Paciente.findByPacOcupacion", query = "SELECT p FROM Paciente p WHERE p.pacOcupacion = :pacOcupacion"),
    @NamedQuery(name = "Paciente.findByPacId", query = "SELECT p FROM Paciente p WHERE p.pacId = :pacId")})
public class Paciente implements Serializable {
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pacId", fetch = FetchType.LAZY)
    private List<AtencionMedica> atencionMedicaList;

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

    @XmlTransient
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
