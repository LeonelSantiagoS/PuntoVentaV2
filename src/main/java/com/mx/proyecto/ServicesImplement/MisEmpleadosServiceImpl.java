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
			
			// Validar el formato de la CURP
	        if (!nuevoEmpleado.getCurp().matches("^[A-Z]{4}\\d{6}[HM]{1}[A-Z]{5}[0-9A-Z]{2}$")) {
	            response.setCode(400);
	            response.setMessage("La curp no cuenta con la estrutura adecuada");
	            return response;
	        }
			
			// Verificar si el empleado ya existe por RFC o CURP
	        if (misEmpleadosDAO.existeEmpleadoPorRfc(nuevoEmpleado.getRfc()) || misEmpleadosDAO.existeEmpleadoPorCurp(nuevoEmpleado.getCurp())) {
	            response.setCode(400);
	            response.setMessage("El empleado ya existe en la base de datos");
	            return response;
	        }
	        
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

	@Override
	public ResponseDto eliminarUsuario(MisEmpleadosDTO idUser) {
		ResponseDto response = new ResponseDto();
		try {
			if (idUser.getIdEmpleado() != 0) {
				
				MisEmpleados empleado = misEmpleadosDAO.getById(idUser.getIdEmpleado());

				if (empleado != null) {
					if (empleado.getActivo() == 0) {
						misEmpleadosDAO.delete(idUser.getIdEmpleado());
						response.setCode(200);
						response.setMessage("Empleado eliminado correctamente");
					} else {
						response.setCode(400);
						response.setMessage("Imposible eliminar Empleado, Sigue Laborando (Activo)...!");
					}
				} else {
					response.setCode(404);
					response.setMessage("El registro no existe");
				}
			} else {
				response.setCode(400);
				response.setMessage("El PK no puede ser 0");
			}
		} catch (Exception e) {
			response.setCode(500);
			response.setMessage("Ocurrio un error en el metodo eliminarUsuario: " + e.getMessage());
		}
		return response;
	}

	@Override
	public ResponseDto actualizarUsuario(MisEmpleadosDTO datos) {
		ResponseDto response = new ResponseDto();
		try {
			if (datos.getIdEmpleado() != 0) {
				MisEmpleados empleado = misEmpleadosDAO.getById(datos.getIdEmpleado());
				if (empleado != null) {
					if (empleado.getActivo() == 1) {
						MisEmpleados datosEmpleado = new MisEmpleados();
						datosEmpleado.setIdEmpleado(datos.getIdEmpleado());
						datosEmpleado.setNombreCompleto(datos.getNombreCompleto());
						datosEmpleado.setRfc(datos.getRfc());
						datosEmpleado.setCurp(datos.getCurp());
						datosEmpleado.setEdad(datos.getEdad());
						datosEmpleado.setSexo(datos.getSexo());
						datosEmpleado.setDireccion(datos.getDireccion());
						datosEmpleado.setNss(datos.getNss());
						datosEmpleado.setTelefono(datos.getTelefono());
						datosEmpleado.setActivo(datos.getActivo());
						misEmpleadosDAO.update(datosEmpleado);
						response.setCode(200);
						response.setMessage("El registro " + datos.getNombreCompleto() + " se actualizó correctamente");
					} else {
						response.setCode(400);
						response.setMessage("El empleado esta dado de baja, No puede actualizar su informacion.! ");
					}
				} else {
					response.setCode(404);
					response.setMessage("No se encuentra ningun registro con ID "+datos.getIdEmpleado());
				}
			} else {
				response.setCode(400);
				response.setMessage("El PK no puede ser 0");
			}
		} catch (Exception e) {
			response.setCode(500);
			response.setMessage("Ocurrio un error en el metodo actualizarUsuario: " + e.getMessage());
		}
		return response;
	}

	@Override
	public ResponseDto getMisEmpleadosM() {
		ResponseDto response = new ResponseDto();
		try {
			List<MisEmpleados> listaEmpleados = misEmpleadosDAO.obtieneEmpleadosM();
			System.out.println(listaEmpleados.toString());
			// if(listaUsuarios != null) {
			if (listaEmpleados.isEmpty()) {
				response.setMessage("No existen registros");
			} else {
				response.setCode(200); // 200 -> OK
				response.setMessage("Lista de Empleados");
				response.setContent(listaEmpleados);
			}

		} catch (Exception e) {
			response.setCode(500);
			response.setMessage("Ocurrio un error en el metodo getMisEmpleadosM() " + e.getMessage());
		}
		return response;
	}

	@Override
	public ResponseDto getMisEmpleadosF() {
		ResponseDto response = new ResponseDto();
		try {
			List<MisEmpleados> listaEmpleados = misEmpleadosDAO.obtieneEmpleadosF();
			System.out.println(listaEmpleados.toString());
			// if(listaUsuarios != null) {
			if (listaEmpleados.isEmpty()) {
				response.setMessage("No existen registros");
			} else {
				response.setCode(200); // 200 -> OK
				response.setMessage("Lista de Empleados");
				response.setContent(listaEmpleados);
			}

		} catch (Exception e) {
			response.setCode(500);
			response.setMessage("Ocurrio un error en el metodo getMisEmpleadosF() " + e.getMessage());
		}
		return response;
	}

	@Override
	public ResponseDto getMisEmpleadosPorRFC(MisEmpleadosDTO rfc) {
		ResponseDto response = new ResponseDto();
		try {
			if (rfc.getRfc() != null) {

				MisEmpleados empleado = misEmpleadosDAO.getByRFC(rfc.getRfc());

				if (empleado != null) {
					response.setCode(200);
					response.setMessage("RFC encontrado !!");
					response.setContent(empleado);
				} else {
					response.setCode(404);
					response.setMessage("RFC no encontrado !!!");
				}
			} else {
				response.setCode(400);
				response.setMessage("El campo RFC no puede ser null");
			}
		} catch (Exception e) {
			response.setCode(500);
			response.setMessage("Ocurrio un error en el metodo getMisEmpleadosPorRFC: " + e.getMessage());
		}
		return response;
	}
}