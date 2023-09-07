package com.mx.proyecto.RepositoryImplement;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mx.proyecto.Entity.catRoles;
import com.mx.proyecto.Repository.CataRolDAO;

@Repository
public class CataRolDAOImpl extends GenericDAO<catRoles, Long> implements CataRolDAO {
	
	@Override
	@Transactional()
	public List<catRoles> obtieneRoles() {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria criteria = session.createCriteria(catRoles.class); // -> SELECT * FROM ESQUEMA.TABLA_ESTADOS
		
		return (List<catRoles>) criteria.list();
	}

}
