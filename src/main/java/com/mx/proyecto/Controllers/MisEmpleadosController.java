package com.mx.proyecto.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mx.proyecto.Dto.MisEmpleadosDTO;
import com.mx.proyecto.Dto.ResponseDto;
import com.mx.proyecto.Services.MisEmpleadosService;

@Controller
@RequestMapping("misEmpleados")
public class MisEmpleadosController {
	@Autowired
	public MisEmpleadosService misEmpleadosService;

	// Servicio para consultar empleados
	@ResponseBody
	@RequestMapping(value = "/getMisEmpleados", method = RequestMethod.GET, produces = "application/json")
	public ResponseDto getMisEmpleados() {

		return misEmpleadosService.getMisEmpleados();
	}
	

	// Servicio para insertar Empleado
	@ResponseBody
	@RequestMapping(value = "/insertMisEmpleados", method = RequestMethod.POST, produces = "application/json")
	ResponseEntity<ResponseDto> insertMisEmpleados(@RequestBody MisEmpleadosDTO nuevoEmpleado) {
		final HttpHeaders httpHeaders = new HttpHeaders();

		ResponseDto response = misEmpleadosService.insertEmpleado(nuevoEmpleado);
		System.out.println("volvimos al controller");
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<ResponseDto>(response, httpHeaders, HttpStatus.OK);
	}

}