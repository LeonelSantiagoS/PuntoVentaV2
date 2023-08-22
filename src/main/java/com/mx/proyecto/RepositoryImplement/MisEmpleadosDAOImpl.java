package com.mx.proyecto.RepositoryImplement;

import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.mx.proyecto.Entity.MisEmpleados;
import com.mx.proyecto.Repository.MisEmpleadosDAO;

@Repository
public class MisEmpleadosDAOImpl extends GenericDAO<MisEmpleados, Integer> implements MisEmpleadosDAO{

	@Override
	@Transactional
	public Long obtenerValorSecuencia() {
		String sqlSequence = "SELECT SEC_ID_MIS_EMPLEADOS.NEXTVAL AS SECUENCIAUSER FROM DUAL";
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery(sqlSequence);
		List result = query.list();
		return ((BigDecimal) result.get(0)).longValue();
	}

	@Override
	@Transactional
	public List<MisEmpleados> obtieneEmpleados() {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria criteria = session.createCriteria(MisEmpleados.class); // --> select *  from tabla
		
		return (List<MisEmpleados>) criteria.list();
	}
}