package com.mx.proyecto.RepositoryImplement;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mx.proyecto.Dto.Cursos;
import com.mx.proyecto.Mapper.CursosListMapper;
import com.mx.proyecto.Repository.CursosRepository;

@Repository
public class CursosRepositoryImpl implements CursosRepository{
	@Autowired
	DataSource dataSource;
	
	JdbcTemplate jdbcTemplate = new JdbcTemplate();
	
	@Override
	public List<Cursos> getCursos() {
		jdbcTemplate.setDataSource(getDataSource());
		String sqlQuery = "SELECT * " +
                "FROM CURSOS";
		return jdbcTemplate.query(sqlQuery, new CursosListMapper<Cursos>());
	}

	@Override
	public Integer insertCursos(Cursos nuevoCurso) {
		// TODO Esbozo de método generado automáticamente
		return null;
	}

	@Override
	public Integer updateCursoss(Cursos curso) {
		// TODO Esbozo de método generado automáticamente
		return null;
	}

	@Override
	public Integer deleteCursos(Cursos curso) {
		// TODO Esbozo de método generado automáticamente
		return null;
	}

	@Override
	public int[][] insertCursosMasivo(List<Cursos> cursos) {
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
