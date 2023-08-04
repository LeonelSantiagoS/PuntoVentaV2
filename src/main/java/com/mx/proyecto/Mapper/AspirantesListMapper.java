package com.mx.proyecto.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.mx.proyecto.Dto.Aspirantes;

public class AspirantesListMapper<T> implements RowMapper<Aspirantes> {
	
	public Aspirantes mapRow (ResultSet rs, int rowNum) throws SQLException {
		Aspirantes objeto = new Aspirantes(); 
		System.out.println("DB "+rs.getString("NOMBREALUMNO"));
		objeto.setIdAlumno(rs.getBigDecimal("IDALUMNO")); 
		objeto.setNombreAlumno(rs.getString("NOMBREALUMNO")); 
		objeto.setEdad(rs.getInt ("EDAD")); 
		objeto.setFechaInscripcion (rs.getDate("FECHAINSCRIPCION"));
//		objeto.setMaestro(rs.getString("MAESTRO")); 
//		objeto.setCurso(rs.getString("CURSO")); 
		return objeto;
	}
}
