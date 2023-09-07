package com.mx.proyecto.ServicesImplement;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mx.proyecto.Dto.ResponseDto;
import com.mx.proyecto.Dto.UsuariosAdminDTO;
import com.mx.proyecto.Entity.UsuariosAdmin;
import com.mx.proyecto.Repository.UsuariosAdminDAO2;
import com.mx.proyecto.Services.UsuariosAdminService2;

@Service
public class UsuariosAdminServiceImpl2 implements UsuariosAdminService2 {

	@Autowired
	private UsuariosAdminDAO2 usuariosAdminDAO2;

	// Regla 01: Necesito una lista de usuarios
	// Regla 02: Necesito que cuando no existan usuarios el sistema me arroje un mensaje -> "No existen registros"
	@Override
	public ResponseDto getUsuarios2() {
		ResponseDto response = new ResponseDto();
		try {
			List<UsuariosAdmin> listaUsuarios = usuariosAdminDAO2.obtieneUsuarios();
			//if(listaUsuarios != null) {
			if (listaUsuarios.isEmpty()) {
				response.setCode(200);
				response.setMessage("No existen registros");
			} else {
				response.setCode(200); // 200 -> OK
				response.setMessage("Lista de usuarios");
				response.setContent(listaUsuarios);
			}
			
		} catch (Exception e) {
			response.setCode(500);
			response.setMessage("Ocurrio un error en el metodo getUsuarios2() "+e.getMessage());
		}
		
		return response;
	}

	// 1. Todos los datos deben ser obligatorios
	// 1.1 - CAMPOS OBLIGATORIOS: -> NombreCompleto, edad, direccion.
	// 2. Los campos no vengan en NULLL -> Que contengan informacion
	@Override
	public ResponseDto insertUsuariosAdmin(UsuariosAdminDTO nuevoUsuario) {
		ResponseDto response = new ResponseDto();
		
		try {
			// OPERADORES
			// && => AND (y) debe cumplir todas las condiciones
			// || => OR (o) debe cumplir al menos 1 de las condiciones
			if (nuevoUsuario != null) { // validacion general, si es diferente a null -> (que no viene vacio)
				if (nuevoUsuario.getNombreCompleto() != null && !nuevoUsuario.getNombreCompleto().isEmpty() &&
						nuevoUsuario.getEdad() != 0 &&
						nuevoUsuario.getDireccion() != null && !nuevoUsuario.getDireccion().isEmpty()) {
					
					UsuariosAdmin datos = new UsuariosAdmin();
					datos.setIdUser(usuariosAdminDAO2.obtenerValorSecuenciaTabla()); // consultar el id autoincrementable
//					datos.setIdUser(nuevoUsuario.getIdUser()); Esta forma es manual
					
					datos.setNombreCompleto(nuevoUsuario.getNombreCompleto());
					datos.setDireccion(nuevoUsuario.getDireccion());
					datos.setEdad(nuevoUsuario.getEdad());
					datos.setEstado(nuevoUsuario.getEstado());
					datos.setRol(nuevoUsuario.getRol());
					
					usuariosAdminDAO2.create(datos); // Desde aqui se hace el INSERT --identificar que retorna la insercion
					response.setCode(200);
					response.setMessage("Los datos se guardaron correctamente");
				} else {
					response.setCode(300);
					response.setMessage("Los datos obligatorios vienen vacios - (NombreCompleto, edad, direccion)");
				}
				
			} else {
				response.setCode(400);
				response.setMessage("Los datos vienen vacíos");
			}
			
		} catch (Exception e) {
			response.setCode(500);
			response.setMessage("Ocurrio un error en el metodo insertUsuariosAdmin: "+e.getMessage());
		}
		return response ;
	}

	// validar que el id no venga vacio
	// validar que el id sea numerico -- QUE NO SEA TEXTO
	@Override
	public ResponseDto eliminarUsuario(UsuariosAdminDTO idUser) {
		ResponseDto response = new ResponseDto();
		try {
			if (idUser.getIdUser() != 0) {
				usuariosAdminDAO2.delete(idUser.getIdUser());
				response.setCode(200);
				response.setMessage("El registro se eliminó correctamente");
			} else {
				response.setCode(400);
				response.setMessage("El PK no pueder 0");
				
			}
			
		} catch (Exception e) {
			response.setCode(500);
			response.setMessage("Ocurrio un error en el metodo eliminarUsuario: "+e.getMessage());
		}
		return response;
	}

	@Override
	public ResponseDto actualizarUsuario(UsuariosAdminDTO datos) {
		ResponseDto response = new ResponseDto();
		try {
			UsuariosAdmin datosUsuario = new UsuariosAdmin();
			datosUsuario.setIdUser(datos.getIdUser());
			datosUsuario.setNombreCompleto(datos.getNombreCompleto());
			datosUsuario.setDireccion(datos.getDireccion());
			datosUsuario.setEdad(datos.getEdad());
			datosUsuario.setEstado(datos.getEstado());
			datosUsuario.setRol(datos.getRol());
			
			usuariosAdminDAO2.update(datosUsuario);
			response.setCode(200);
			response.setMessage("El registro se actualizó correctamente");
			
		} catch (Exception e) {
			response.setCode(500);
			response.setMessage("Ocurrio un error en el metodo actualizarUsuario: "+e.getMessage());
		}
		return response;
	}

	@Override
	public ResponseDto getUsuariosPorId(UsuariosAdminDTO dato) {
		ResponseDto response = new ResponseDto();
		try {
			// select * from tabla where id=? and rol=? and edad =?
			
			UsuariosAdmin result = usuariosAdminDAO2.read(dato.getIdUser()); // select * from tabla where id = ?
			if (result != null) {
				response.setCode(200);
				response.setMessage("Usuario encontrado");
				response.setContent(result);
				
			} else {
				response.setCode(400);
				response.setMessage("Usuario no encontrado");
			}
			
		} catch (Exception e) {
			response.setCode(500);
			response.setMessage("Ocurrio un error en el metodo getUsuariosPorId: "+e.getMessage());
		}
		return response;
	}

} // Fin de clase
