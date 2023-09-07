package com.mx.proyecto.Repository;

import java.util.List;

import com.mx.proyecto.Entity.catEstados;

public interface CatalogosDAO extends DAO<catEstados, Long> {

	List<catEstados> obtieneEstados();

}