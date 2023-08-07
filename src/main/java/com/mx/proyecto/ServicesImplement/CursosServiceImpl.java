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
		ResponseDto response = new ResponseDto();
		StringBuilder mensajeError = new StringBuilder();
		Integer respuesta = 0;
		try {
			
			// validamos que campo nombrealumno no sea nullo o vacio
			if(nuevoCurso.getNombreCurso() == null  || nuevoCurso.getNombreCurso().equals("")) {
				mensajeError.append("El campo NombreCurso no puede ser NULL o Vacio. ");
			}

			// validamos que campo sea valido o igual a un mes
			if (!(nuevoCurso.getDuracionMeses() >= 0 && nuevoCurso.getDuracionMeses() <= 12)) {
				mensajeError.append("El campo DuracionMeses no puede contener un valor mayor a 3 cifras. ");
			}
			
			// validamos que campo fechaInscripcion no sea nullo o vacio
			if (nuevoCurso.getFechaInicio() == null || nuevoCurso.getFechaInicio().equals("")) {
				mensajeError.append("El campo FechaInicio no puede ser NULL o Vacio. ");
			}

			// Verificar si hay errores en las validaciones
			if (mensajeError.length() > 0) {
				// Si hay errores, establecer el mensaje de error en el response
				response.setMessage(mensajeError.toString());
			}else {
				respuesta = cursoRepository.insertCursos(nuevoCurso);
			}
			

			if (respuesta == 1) {
				response.setCode(0);
				response.setMessage("Se inserto correctamente");
			}else {
				response.setCode(-1);
			}
				
		}
		catch (IndexOutOfBoundsException Eindex) {
			response.setCode(-3);
			response.setMessage("La lista esta vacia o el indice no existe.");
        }
		catch(Exception e) {
			response.setCode(-4);
			response.setMessage("Sucedio un error, Verifique los datos: "+e.getMessage());
		}
		return response;
	}

	@Override
	public ResponseDto updateCursos(Cursos curso) {
		ResponseDto response = new ResponseDto();
		Integer respuesta = 0;

		try {
			respuesta = cursoRepository.updateCursoss(curso);
			if (respuesta == 1) {
				response.setCode(0);
				response.setMessage("Se actualizo correctamente");
			} else {
				response.setCode(-1);
				response.setMessage("Error: No se actualizo correctamente");
			}

		} catch (NullPointerException NPE) {
			System.out.println(NPE.getMessage());
			response.setCode(-2);
			response.setMessage("No existen registros en la Tabla Aspirantes");
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
