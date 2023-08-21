package com.mx.proyecto.RepositoryImplement;

import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.mx.proyecto.Entity.UsuariosAdmin;
import com.mx.proyecto.Repository.UsuariosAdminDAO2;

@Repository
public class UsuariosAdminDAOImpl2 extends GenericDAO<UsuariosAdmin, Long> implements UsuariosAdminDAO2 {

	
	@Override
	@Transactional
	public List<UsuariosAdmin> obtieneUsuarios() {
		
		final Session session = sessionFactory.getCurrentSession();
		final Criteria criteria = session.createCriteria(UsuariosAdmin.class); // --> select *  from tabla
		
		return (List<UsuariosAdmin>) criteria.list(); // --> retorna una lista de usuarios
	}

	@Override
	@Transactional
	public Long obtenerValorSecuenciaTabla() {
		String sqlSequence = "SELECT SEQ_USUARIOS_ADMIN.NEXTVAL AS SECUENCIAUSER FROM DUAL";
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery(sqlSequence);
		List result = query.list();
		return ((BigDecimal) result.get(0)).longValue();
	}


}