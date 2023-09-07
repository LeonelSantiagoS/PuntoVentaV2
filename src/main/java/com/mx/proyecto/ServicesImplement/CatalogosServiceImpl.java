package com.mx.proyecto.ServicesImplement;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mx.proyecto.Entity.catEstados;
import com.mx.proyecto.Entity.catRoles;
import com.mx.proyecto.Repository.CataRolDAO;
import com.mx.proyecto.Repository.CatalogosDAO;
import com.mx.proyecto.Services.CatalogosService;

@Service
public class CatalogosServiceImpl implements  CatalogosService{
	
	@Autowired
	private CatalogosDAO catalogosDAO;
	
	@Autowired
	private CataRolDAO cataRolDAO;

	@Override
	public List<catRoles> getRoles() {
		List<catRoles> listRoles = cataRolDAO.obtieneRoles();
		
		return listRoles;
	}

	
	@Override
	public List<catEstados> getEstados() {
		List<catEstados> listEstados = catalogosDAO.obtieneEstados();
		
		return listEstados;
	}
	

}
