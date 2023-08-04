package com.mx.proyecto.Dto;

import java.util.Date;

public class Cursos {
	private Integer cursoId; 
	private String nombreCurso; 
	private Integer duracionMeses;
	private Date fechaInicio; 
	private Integer curso;
	
	public Integer getCursoId() {
		return cursoId;
	}
	public void setCursoId(Integer cursoId) {
		this.cursoId = cursoId;
	}
	public String getNombreCurso() {
		return nombreCurso;
	}
	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}
	public Integer getDuracionMeses() {
		return duracionMeses;
	}
	public void setDuracionMeses(Integer duracionMeses) {
		this.duracionMeses = duracionMeses;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Integer getCurso() {
		return curso;
	}
	public void setCurso(Integer curso) {
		this.curso = curso;
	}
	
	
	
}
