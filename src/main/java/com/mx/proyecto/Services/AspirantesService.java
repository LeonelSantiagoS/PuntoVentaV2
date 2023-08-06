package com.mx.proyecto.Services;

import java.util.List;

import com.mx.proyecto.Dto.Aspirantes;
import com.mx.proyecto.Dto.ResponseDto;

public interface AspirantesService {
	ResponseDto getAspirantes(); // ESTE METODO OBTIENE ASPIRANTES
	ResponseDto insertAspirantes(Aspirantes nuevoAspirante);
	ResponseDto updateAspirantes(Aspirantes aspirante);
	ResponseDto deleteAspirantes(Aspirantes aspirante);
	ResponseDto insertAspirantesMasivo(Aspirantes[] aspirantes);
}
