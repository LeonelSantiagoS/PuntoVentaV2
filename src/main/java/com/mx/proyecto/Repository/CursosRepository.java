package com.mx.proyecto.Repository;

import java.util.List;

import com.mx.proyecto.Dto.Cursos;

public interface CursosRepository {
	List<Cursos> getCursos();
	Integer insertCursos(Cursos nuevoCurso);
	Integer updateCursoss(Cursos curso);
	Integer deleteCursos(Cursos curso);
	int[][]  insertCursosMasivo(List<Cursos> cursos);
}