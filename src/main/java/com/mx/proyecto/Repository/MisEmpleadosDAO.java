package com.mx.proyecto.Repository;

import java.util.List;
import com.mx.proyecto.Entity.MisEmpleados;

public interface MisEmpleadosDAO extends DAO<MisEmpleados, Long> {

	Long obtenerValorSecuencia();
	List<MisEmpleados> obtieneEmpleados();
	boolean existeEmpleadoPorRfc(String rfc);
	boolean existeEmpleadoPorCurp(String curp);
	MisEmpleados getById(Long idEmpleado);
}