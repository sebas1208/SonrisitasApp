package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "ODONTOLOGO")
public class Odontologo implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ODO_ID")
	private String odoId;

	@Column(name = "ODO_CEDULA")
	@NotNull
	@Size(min = 1, max = 20)
	private String odoCedula;

	@Column(name = "ODO_NOMBRES")
	@NotNull
	@Size(min = 1, max = 50)
	private String odoNombres;

	@Column(name = "ODO_APELLIDOS")
	@NotNull
	@Size(min = 1, max = 50)
	private String odoApellidos;

	@Column(name = "ODO_DIRECCION")
	@Size(min = 1, max = 100)
	private String odoDireccion;

	@Column(name = "ODO_TELEFONO")
	@NotNull
	@Size(min = 1, max = 20)
	private String odoTelefono;

	@Column(name = "ODO_EMAIL")
	@NotNull
	@Size(min = 1, max = 50)
	private String odoEmail;

	/*@JoinColumn(name = "USU_ID" , referencedColumnName = "USU_ID")
	private Usuario usuId;*/
}