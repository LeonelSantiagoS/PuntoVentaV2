package com.mx.proyecto.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mx.proyecto.Dto.ResponseDto;
import com.mx.proyecto.Services.MaestrosService;

@Controller
@RequestMapping("maestro")
public class MaestrosController {
	@Autowired
	private MaestrosService maestrosService;
	
	// CONSULTA MAESTROS - GET
	@ResponseBody
	@RequestMapping(value = "/getMaestros", method = RequestMethod.GET, produces = "application/json") //
	ResponseEntity<ResponseDto> getMaestros() {
		final HttpHeaders httpHeaders = new HttpHeaders();

		ResponseDto response = new ResponseDto();
		response = maestrosService.getMaestros();

		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<ResponseDto>(response, httpHeaders, HttpStatus.OK);
	}
}