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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 *
 * @author sebastian
 */
@Entity
@Table(name = "tipo_atencion_medica",  schema = "public")
public class TipoAtencionMedica extends Model implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "tam_id")
    private Long tamId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tam_nombre")
    private String tamNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tam_duracion_horas")
    private int tamDuracionHoras;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tam_duracion_minutos")
    private int tamDuracionMinutos;
    @Column(name = "tam_activo")
    private Boolean tamActivo;
    @Column(name = "tam_fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date tamFechaRegistro;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tamId", fetch = FetchType.LAZY)
    private List<AtencionMedica> atencionMedicaList;

    public static Finder<Long,TipoAtencionMedica> find = new Finder<Long,TipoAtencionMedica>(Long.class, TipoAtencionMedica.class);

    public TipoAtencionMedica() {
    }

    public TipoAtencionMedica(Long tamId) {
        this.tamId = tamId;
    }

    public TipoAtencionMedica(Long tamId, String tamNombre, int tamDuracionHoras, int tamDuracionMinutos) {
        this.tamId = tamId;
        this.tamNombre = tamNombre;
        this.tamDuracionHoras = tamDuracionHoras;
        this.tamDuracionMinutos = tamDuracionMinutos;
    }

    public Long getTamId() {
        return tamId;
    }

    public void setTamId(Long tamId) {
        this.tamId = tamId;
    }

    public String getTamNombre() {
        return tamNombre;
    }

    public void setTamNombre(String tamNombre) {
        this.tamNombre = tamNombre;
    }

    public int getTamDuracionHoras() {
        return tamDuracionHoras;
    }

    public void setTamDuracionHoras(int tamDuracionHoras) {
        this.tamDuracionHoras = tamDuracionHoras;
    }

    public int getTamDuracionMinutos() {
        return tamDuracionMinutos;
    }

    public void setTamDuracionMinutos(int tamDuracionMinutos) {
        this.tamDuracionMinutos = tamDuracionMinutos;
    }

    public Boolean getTamActivo() {
        return tamActivo;
    }

    public void setTamActivo(Boolean tamActivo) {
        this.tamActivo = tamActivo;
    }

    public Date getTamFechaRegistro() {
        return tamFechaRegistro;
    }

    public void setTamFechaRegistro(Date tamFechaRegistro) {
        this.tamFechaRegistro = tamFechaRegistro;
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
        hash += (tamId != null ? tamId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoAtencionMedica)) {
            return false;
        }
        TipoAtencionMedica other = (TipoAtencionMedica) object;
        if ((this.tamId == null && other.tamId != null) || (this.tamId != null && !this.tamId.equals(other.tamId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.gr1.sonrisitas.modelos.TipoAtencionMedica[ tamId=" + tamId + " ]";
    }

}
