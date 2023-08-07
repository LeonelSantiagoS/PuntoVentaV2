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
		jdbcTemplate.setDataSource(getDataSource());
		String sqlQuery = "INSERT INTO CURSOS (NOMBRE_CURSO, DURACION_MESES, FECHA_INICIO, CANTIDAD_ALUMNOS) "
				+ "VALUES (?,?,?,?)";
		return jdbcTemplate.update(sqlQuery, nuevoCurso.getNombreCurso(), nuevoCurso.getDuracionMeses(), nuevoCurso.getFechaInicio(),
				nuevoCurso.getCantidadAlumnos());
	}

	@Override
	public Integer updateCursoss(Cursos curso) {
		jdbcTemplate.setDataSource(getDataSource());
		String sqlQuery = "UPDATE CURSOS SET NOMBRE_CURSO = ?, DURACION_MESES = ?, FECHA_INICIO = ?, CANTIDAD_ALUMNOS = ? WHERE CURSOID = ?";
		return jdbcTemplate.update(sqlQuery,
			new Object[] {curso.getNombreCurso(), curso.getDuracionMeses(), curso.getFechaInicio(), curso.getCantidadAlumnos(), curso.getCursoId()});
	}

	@Override
	public Integer deleteCursos(Cursos curso) {
		jdbcTemplate.setDataSource(getDataSource());
		return jdbcTemplate.update("DELETE FROM CURSOS WHERE CURSOID = ? ", curso.getCursoId());
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
