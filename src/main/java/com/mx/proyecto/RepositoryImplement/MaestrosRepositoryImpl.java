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
	public Integer insertMaestros(Maestros nuevoCurso) {
		jdbcTemplate.setDataSource(getDataSource());
		String sqlQuery = "INSERT INTO MAESTROS (NOMBRE_MAESTRO, EDAD, FECHA_NACIMIENTO, NUMERO_CURSOS)"
				+ "VALUES (?,?,?,?)";
		return jdbcTemplate.update(sqlQuery, nuevoCurso.getNombreMaestro(), nuevoCurso.getEdad(), nuevoCurso.getFechaNacimiento(),
				nuevoCurso.getNumeroCursos());
	}

	@Override
	public Integer updateMaestros(Maestros curso) {
		// TODO Esbozo de método generado automáticamente
		return null;
	}

	@Override
	public Integer deleteMaestros(Maestros curso) {
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
