package com.mx.proyecto.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CAT_ESTADOS", schema = "CURSOLEO")
public class catEstados {
	@Id
	@Column(name = "ID_ESTADO")
	private Long idEstado;
	@Column(name = "NOMBRE")
	private String nombre;
	@Column(name = "ABREVIATURA")
	private String abreviatura;
	public Long getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getAbreviatura() {
		return abreviatura;
	}
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

}