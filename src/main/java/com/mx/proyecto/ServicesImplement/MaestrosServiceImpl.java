package com.mx.proyecto.ServicesImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.proyecto.Dto.Maestros;
import com.mx.proyecto.Dto.ResponseDto;
import com.mx.proyecto.Repository.MaestrosRepository;
import com.mx.proyecto.Services.MaestrosService;


@Service
public class MaestrosServiceImpl implements MaestrosService{
	
	@Autowired
	private MaestrosRepository maestrosRepository;
	
	@Override
	public ResponseDto getMaestros() {
		ResponseDto response = new ResponseDto();
		try {
			List<Maestros> lista = maestrosRepository.getMaestros(); 
			if (lista != null && lista.size() > 0) {
				response.setCode(0);
				response.setMessage("Hay " + lista.size() + " registros que mostrar de la tabla Maestros");
				response.setContent(lista);
			} else {
				response.setCode(-1);
				response.setMessage("No hay registros que mostrar de la tabla Maestros");
			}
		} catch (NullPointerException NPE) {
			System.out.println(NPE.getMessage());
			response.setCode(-2);
			response.setMessage("No existen registros en la Tabla Maestros");
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
	public ResponseDto insertMaestros(Maestros nuevoMaestro) {
		// TODO Esbozo de método generado automáticamente
		return null;
	}

	@Override
	public ResponseDto updateMaestros(Maestros maestro) {
		// TODO Esbozo de método generado automáticamente
		return null;
	}

	@Override
	public ResponseDto deleteMaestros(Maestros maestro) {
		// TODO Esbozo de método generado automáticamente
		return null;
	}

	@Override
	public ResponseDto insertMaestrosMasivo(Maestros[] maestros) {
		// TODO Esbozo de método generado automáticamente
		return null;
	}

}
