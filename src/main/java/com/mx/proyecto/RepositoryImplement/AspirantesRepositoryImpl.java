package com.mx.proyecto.RepositoryImplement;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import com.mx.proyecto.Dto.Aspirantes;
import com.mx.proyecto.Mapper.AspirantesListMapper;
import com.mx.proyecto.Repository.AspirantesRepository;

@Repository
public class AspirantesRepositoryImpl implements AspirantesRepository{
	
	@Autowired
	DataSource dataSource;
	
	JdbcTemplate jdbcTemplate = new JdbcTemplate();

	@Override
	public List<Aspirantes> getAspirantes() {
//		System.out.println("AspirantesRepositoryImpl");

		jdbcTemplate.setDataSource(getDataSource());
		return jdbcTemplate.query("SELECT * FROM ASPIRANTES", new AspirantesListMapper<Aspirantes>());
	}
	
	@Override
	public Integer insertAspirantes(Aspirantes nuevoAspirante) {
		jdbcTemplate.setDataSource(getDataSource());
		return jdbcTemplate.update("INSERT INTO ASPIRANTES (nombreAlumno, edad, fechaInscripcion, cursoId, maestroId) "
		+ "VALUES (?,?,?,?,?)",nuevoAspirante.getNombreAlumno() ,nuevoAspirante.getEdad(),
		nuevoAspirante.getFechaInscripcion(),nuevoAspirante.getCursoId(), nuevoAspirante.getMaestroId());
	}
	
	@Override
	public Integer updateAspirantes(Aspirantes aspirante) {
		jdbcTemplate.setDataSource(getDataSource());
		return jdbcTemplate.update("UPDATE ASPIRANTES SET NOMBREALUMNO = ?, EDAD = ?, FECHAINSCRIPCION = ?, CURSO = ?,  MAESTRO = ? WHERE IDALUMNO = ?",
				new Object[] {aspirante.getNombreAlumno(), aspirante.getEdad(), aspirante.getFechaInscripcion(), aspirante.getCurso(),
						aspirante.getMaestro(), aspirante.getIdAlumno()});
	}
	
	@Override
	public Integer deleteAspirantes(Aspirantes aspirante) {
		jdbcTemplate.setDataSource(getDataSource());
		return jdbcTemplate.update("DELETE FROM ASPIRANTES WHERE IDALUMNO = ? ", aspirante.getIdAlumno());
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