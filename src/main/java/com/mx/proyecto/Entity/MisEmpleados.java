package com.mx.proyecto.Entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MIS_EMPLEADOS", schema = "CURSOLEO")
public class MisEmpleados {
	@Id
	@Column(name = "ID_EMPLEADO", unique = true, nullable = false, precision = 11)
	private Long idEmpleado;

	@Column(name = "NOMBRE_COMPLETO")
	private String nombreCompleto;

	@Column(name = "RFC")
	private String rfc;

	@Column(name = "CURP")
	private String curp;

	@Column(name = "EDAD")
	private Long edad;

	@Column(name = "SEXO")
	private String sexo;

	@Column(name = "DIRECCION")
	private String direccion;

	@Column(name = "NSS")
	private Long nss;

	@Column(name = "TELEFONO")
	private Long telefono;

	@Column(name = "ACTIVO")
	private Long activo;

	public Long getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public Long getEdad() {
		return edad;
	}

	public void setEdad(Long edad) {
		this.edad = edad;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Long getNss() {
		return nss;
	}

	public void setNss(Long nss) {
		this.nss = nss;
	}

	public Long getTelefono() {
		return telefono;
	}

	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}

	public Long getActivo() {
		return activo;
	}

	public void setActivo(Long activo) {
		this.activo = activo;
	}

	
}