package com.mx.proyecto.Repository;

import java.util.List;

import com.mx.proyecto.Entity.UsuariosAdmin;
//											DAO <T, PK>
public interface UsuariosAdminDAO2 extends DAO<UsuariosAdmin, Long>{

	List<UsuariosAdmin> obtieneUsuarios();

	Long obtenerValorSecuenciaTabla();

}