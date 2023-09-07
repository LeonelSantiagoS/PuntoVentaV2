package com.mx.proyecto.Repository;

import java.util.List;
import com.mx.proyecto.Entity.catRoles;

public interface CataRolDAO extends DAO<catRoles, Long> {

	List<catRoles> obtieneRoles();

}
