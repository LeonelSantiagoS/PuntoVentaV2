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

	// 1. Servicio para insertar Empleado
	@ResponseBody
	@RequestMapping(value = "/insertMisEmpleados", method = RequestMethod.POST, produces = "application/json")
	ResponseEntity<ResponseDto> insertMisEmpleados(@RequestBody MisEmpleadosDTO nuevoEmpleado) {
		final HttpHeaders httpHeaders = new HttpHeaders();

		ResponseDto response = misEmpleadosService.insertEmpleado(nuevoEmpleado);
		System.out.println("volvimos al controller");
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<ResponseDto>(response, httpHeaders, HttpStatus.OK);
	}

	// 2. Eliminar registro - solo Empleados Inactivos
	@ResponseBody
	@RequestMapping(value = "/eliminarMisEmpleados", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<ResponseDto> eliminarUsuario(@RequestBody MisEmpleadosDTO idUser) {
		final HttpHeaders httpHeaders = new HttpHeaders();

		ResponseDto respuesta = misEmpleadosService.eliminarUsuario(idUser);

		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<ResponseDto>(respuesta, httpHeaders, HttpStatus.OK);
	}

	// 3. Actualizar registro - Solo Empleados Activos
	@ResponseBody
	@RequestMapping(value = "/actualizarMisEmpleados", method = RequestMethod.POST, produces = "application/json")
	ResponseEntity<ResponseDto> actualizarDatosUsuario(@RequestBody MisEmpleadosDTO datos) {
		final HttpHeaders httpHeaders = new HttpHeaders();

		ResponseDto respuesta = misEmpleadosService.actualizarUsuario(datos);

		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<ResponseDto>(respuesta, httpHeaders, HttpStatus.OK);
	}
	
	//4.- Servicio para consultar todos los usuarios de sexo Masculino
	@ResponseBody
	@RequestMapping(value = "/getMisEmpleadosM", method = RequestMethod.GET, produces = "application/json")
	public ResponseDto getMisEmpleadosM() {
		return misEmpleadosService.getMisEmpleadosM();
	}
	

	//5.- Servicio para consultar todos los usuarios de sexo Femenino de 35 años
	@ResponseBody
	@RequestMapping(value = "/getMisEmpleadosF", method = RequestMethod.GET, produces = "application/json")
	public ResponseDto getMisEmpleadosF() {
		return misEmpleadosService.getMisEmpleadosF();
	}

	//6.- Servicio para buscar el usuario por RFC
	@ResponseBody
	@RequestMapping(value = "/getMisEmpleadosPorRFC", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<ResponseDto> getMisEmpleadosPorRFC(@RequestBody MisEmpleadosDTO rfc) {
		final HttpHeaders httpHeaders = new HttpHeaders();
		ResponseDto respuesta = misEmpleadosService.getMisEmpleadosPorRFC(rfc);
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<ResponseDto>(respuesta, httpHeaders, HttpStatus.OK);
	}
}