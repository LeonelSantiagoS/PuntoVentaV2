package com.mx.proyecto.ServicesImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.proyecto.Dto.Cursos;
import com.mx.proyecto.Dto.ResponseDto;
import com.mx.proyecto.Repository.CursosRepository;
import com.mx.proyecto.Services.CursosService;

@Service
public class CursosServiceImpl implements CursosService{
	
	@Autowired
	private CursosRepository cursoRepository;

	@Override
	public ResponseDto getCursos() {
		ResponseDto response = new ResponseDto();
		try {

			List<Cursos> lista = cursoRepository.getCursos();

			if (lista != null && lista.size() > 0) {
				response.setCode(0);
				response.setMessage("Hay " + lista.size() + " registros que mostrar de la tabla Cursos");
				response.setContent(lista);
			} else {
				response.setCode(-1);
				response.setMessage("No hay registros que mostrar de la tabla Curso");
			}
		} catch (NullPointerException NPE) {
			System.out.println(NPE.getMessage());
			response.setCode(-2);
			response.setMessage("No existen registros en la Tabla Cursos");
		} catch (IndexOutOfBoundsException Eindex) {
			response.setCode(-3);
			response.setMessage("La lista esta vacia o el indice no existe.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response.setCode(-4);
			response.setMessage("Sucedio un error, Verifique los datos: " + e.getMessage());
		}

		return response;
	}

	@Override
	public ResponseDto insertCursos(Cursos nuevoCurso) {
		// TODO Esbozo de método generado automáticamente
		return null;
	}

	@Override
	public ResponseDto updateCursos(Cursos curso) {
		// TODO Esbozo de método generado automáticamente
		return null;
	}

	@Override
	public ResponseDto deleteCursos(Cursos curso) {
		// TODO Esbozo de método generado automáticamente
		return null;
	}

	@Override
	public ResponseDto insertCursosMasivo(Cursos[] cursos) {
		// TODO Esbozo de método generado automáticamente
		return null;
	}

}
