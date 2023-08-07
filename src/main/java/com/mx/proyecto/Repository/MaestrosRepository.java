package com.mx.proyecto.Repository;

import java.util.List;

import com.mx.proyecto.Dto.Maestros;

public interface MaestrosRepository {
	List<Maestros> getMaestros();
	Integer insertMaestros(Maestros nuevoCurso);
	Integer updateMaestros(Maestros curso);
	Integer deleteMaestros(Maestros curso);
	int[][]  insertMaestrosMasivo(List<Maestros> maestros);
}