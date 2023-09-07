package com.mx.proyecto.RepositoryImplement;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.mx.proyecto.Entity.catEstados;
import com.mx.proyecto.Repository.CatalogosDAO;

@Repository
public class CatalogosDAOImpl extends GenericDAO<catEstados, Long> implements CatalogosDAO{

	@Override
	@Transactional()
	public List<catEstados> obtieneEstados() {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria criteria = session.createCriteria(catEstados.class); // -> SELECT * FROM ESQUEMA.TABLA_ESTADOS
		
		return (List<catEstados>) criteria.list();
	}

}
