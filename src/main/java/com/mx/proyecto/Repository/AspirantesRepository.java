package com.mx.proyecto.Repository;

import java.util.List;

import com.mx.proyecto.Dto.Aspirantes;

public interface AspirantesRepository {
	List<Aspirantes>getAspirantes();
	Integer insertAspirantes(Aspirantes nuevoAspirante);
	Integer updateAspirantes(Aspirantes aspirante);
	Integer deleteAspirantes(Aspirantes aspirante);
}
