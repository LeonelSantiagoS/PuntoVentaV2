package com.mx.proyecto.RepositoryImplement;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mx.proyecto.Dto.Maestros;
import com.mx.proyecto.Mapper.MaestrosListMapper;
import com.mx.proyecto.Repository.MaestrosRepository;

@Repository
public class MaestrosRepositoryImpl implements MaestrosRepository{
	
	@Autowired
	DataSource dataSource;
	
	JdbcTemplate jdbcTemplate = new JdbcTemplate();

	@Override
	public List<Maestros> getMaestros() {
		jdbcTemplate.setDataSource(getDataSource());
		String sqlQuery = "SELECT * FROM MAESTROS";
		return jdbcTemplate.query(sqlQuery, new MaestrosListMapper<Maestros>());
	}

	@Override
	public Integer insertMaestros(Maestros nuevoMaestro) {
		jdbcTemplate.setDataSource(getDataSource());
		String sqlQuery = "INSERT INTO MAESTROS (NOMBRE_MAESTRO, EDAD, FECHA_NACIMIENTO, NUMERO_CURSOS)"
				+ "VALUES (?,?,?,?)";
		return jdbcTemplate.update(sqlQuery, nuevoMaestro.getNombreMaestro(), nuevoMaestro.getEdad(), nuevoMaestro.getFechaNacimiento(),
				nuevoMaestro.getNumeroCursos());
	}

	@Override
	public Integer updateMaestros(Maestros maestro) {
		jdbcTemplate.setDataSource(getDataSource());
		String sqlQuery = "UPDATE MAESTROS SET NOMBRE_MAESTRO = ?, EDAD = ?, FECHA_NACIMIENTO = ?, NUMERO_CURSOS = ? WHERE MAESTROID = ?";
		return jdbcTemplate.update(sqlQuery, new Object[] {  maestro.getNombreMaestro(), maestro.getEdad(), maestro.getFechaNacimiento(),
				maestro.getNumeroCursos(), maestro.getMaestroId() });
	}

	@Override
	public Integer deleteMaestros(Maestros maestro) {
		// TODO Esbozo de método generado automáticamente
		return null;
	}

	@Override
	public int[][] insertMaestrosMasivo(List<Maestros> maestros) {
		// TODO Esbozo de método generado automáticamente
		return null;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
