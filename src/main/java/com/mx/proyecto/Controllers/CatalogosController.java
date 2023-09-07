package com.mx.proyecto.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mx.proyecto.Entity.catEstados;
import com.mx.proyecto.Entity.catRoles;
import com.mx.proyecto.Services.CatalogosService;


@Controller
@RequestMapping(value="catalogos")
public class CatalogosController {
	@Autowired 
	private CatalogosService catalogosService;

	// Una consulta para obtener todos los roles existentes presentarlos en un
	// comboBox para que el usuario eliga el rol
	@ResponseBody
	@RequestMapping(value = "/getRoles", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<catRoles>> obtenerRoles() {
		final HttpHeaders httpHeaders = new HttpHeaders();

		List<catRoles> respuesta = catalogosService.getRoles();

		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<List<catRoles>>(respuesta, httpHeaders, HttpStatus.OK);
	}

	// Una consulta para obtener todos los roles existentes presentarlos en un
	// comboBox para que el usuario eliga el rol
	@ResponseBody
	@RequestMapping(value = "/getEstados", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<catEstados>> obtenerEstados() {
		final HttpHeaders httpHeaders = new HttpHeaders();

		List<catEstados> respuesta = catalogosService.getEstados();

		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<List<catEstados>>(respuesta, httpHeaders, HttpStatus.OK);
	}

}