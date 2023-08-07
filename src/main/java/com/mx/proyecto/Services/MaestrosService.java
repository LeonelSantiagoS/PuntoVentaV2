package com.mx.proyecto.Services;

import com.mx.proyecto.Dto.Maestros;
import com.mx.proyecto.Dto.ResponseDto;

public interface MaestrosService {
	ResponseDto getMaestros(); 
	ResponseDto insertMaestros(Maestros nuevoMaestro);
	ResponseDto updateMaestros(Maestros maestro);
	ResponseDto deleteMaestros(Maestros maestro);
	ResponseDto insertMaestrosMasivo(Maestros[] maestros);
}
