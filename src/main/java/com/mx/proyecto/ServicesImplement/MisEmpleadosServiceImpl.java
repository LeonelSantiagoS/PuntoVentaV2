package com.mx.proyecto.ServicesImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mx.proyecto.Dto.MisEmpleadosDTO;
import com.mx.proyecto.Dto.ResponseDto;
import com.mx.proyecto.Entity.MisEmpleados;
import com.mx.proyecto.Repository.MisEmpleadosDAO;
import com.mx.proyecto.Services.MisEmpleadosService;

@Service
public class MisEmpleadosServiceImpl implements MisEmpleadosService{
	
	@Autowired
	private MisEmpleadosDAO misEmpleadosDAO;

	@Override
	public ResponseDto insertEmpleado(MisEmpleadosDTO nuevoEmpleado) {
		System.out.println("llegamos a la implementacion del servicio, venimos del controller");
		ResponseDto response = new ResponseDto();
		
		try {
			MisEmpleados empleado = new MisEmpleados();
			empleado.setIdEmpleado(misEmpleadosDAO.obtenerValorSecuencia());
			System.out.println(empleado.getIdEmpleado());
			empleado.setNombreCompleto(nuevoEmpleado.getNombreCompleto());
			empleado.setRfc(nuevoEmpleado.getRfc());
			empleado.setCurp(nuevoEmpleado.getCurp());
			empleado.setEdad(nuevoEmpleado.getEdad());
			empleado.setSexo(nuevoEmpleado.getSexo());
			empleado.setDireccion(nuevoEmpleado.getDireccion());
			empleado.setNss(nuevoEmpleado.getNss());
			empleado.setTelefono(nuevoEmpleado.getTelefono());
			empleado.setActivo(nuevoEmpleado.getActivo());
			misEmpleadosDAO.create(empleado);
			response.setCode(200);
			response.setMessage("Los datos se guardaron correctamente");
			
		} catch (Exception e) {
			response.setCode(500);
			System.out.println(e.getMessage());
			response.setMessage("Ocurrio un error en el metodo insertEmpleado: "+e.getMessage());
		}
		
		return response;
	}

	@Override
	public ResponseDto getMisEmpleados() {
		ResponseDto response = new ResponseDto();
		try {
			List<MisEmpleados> listaEmpleados = misEmpleadosDAO.obtieneEmpleados();
			System.out.println(listaEmpleados.toString());
			//if(listaUsuarios != null) {
			if (listaEmpleados.isEmpty()) {
				response.setCode(200);
				response.setMessage("No existen registros");
			} else {
				response.setCode(200); // 200 -> OK
				response.setMessage("Lista de Empleados");
				response.setContent(listaEmpleados);
			}
			
		} catch (Exception e) {
			response.setCode(500);
			response.setMessage("Ocurrio un error en el metodo getUsuarios2() "+e.getMessage());
		}
		return response;
	}
}