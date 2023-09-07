package com.mx.proyecto.Services;

import java.util.List;
import com.mx.proyecto.Entity.catEstados;
import com.mx.proyecto.Entity.catRoles;

public interface CatalogosService {
	List<catRoles> getRoles();
	List<catEstados> getEstados();
}