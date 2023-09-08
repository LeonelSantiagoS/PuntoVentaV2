package com.mx.proyecto.Services;

import com.mx.proyecto.Dto.MisEmpleadosDTO;
import com.mx.proyecto.Dto.ResponseDto;

public interface MisEmpleadosService {

	ResponseDto insertEmpleado(MisEmpleadosDTO nuevoEmpleado);
	ResponseDto getMisEmpleados();
	ResponseDto eliminarUsuario(MisEmpleadosDTO idUser);
	ResponseDto actualizarUsuario(MisEmpleadosDTO datos);
	ResponseDto getMisEmpleadosM();
	ResponseDto getMisEmpleadosF();
	ResponseDto getMisEmpleadosPorRFC(MisEmpleadosDTO rfc);
	ResponseDto getMisEmpleadosPorID(MisEmpleadosDTO idEmpleado);
}