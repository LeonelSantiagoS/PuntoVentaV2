package com.mx.proyecto.Repository;

import java.util.List;
import com.mx.proyecto.Entity.MisEmpleados;

public interface MisEmpleadosDAO extends DAO<MisEmpleados, Integer> {

	Long obtenerValorSecuencia();
	List<MisEmpleados> obtieneEmpleados();

}