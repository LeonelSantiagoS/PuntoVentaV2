package com.mx.proyecto.RepositoryImplement;

import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.mx.proyecto.Entity.MisEmpleados;
import com.mx.proyecto.Repository.MisEmpleadosDAO;

@Repository
public class MisEmpleadosDAOImpl extends GenericDAO<MisEmpleados, Long> implements MisEmpleadosDAO {

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
		final Criteria criteria = session.createCriteria(MisEmpleados.class); // --> select * from tabla

		return (List<MisEmpleados>) criteria.list();
	}

	@Override
	@Transactional
	public boolean existeEmpleadoPorRfc(String rfc) {
		String sqlQuery = "SELECT COUNT(*) FROM MIS_EMPLEADOS WHERE RFC = :RFC";
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery(sqlQuery);
		query.setParameter("RFC", rfc);
		int count = ((BigDecimal) query.uniqueResult()).intValue();
		return count > 0;
	}

	@Override
	@Transactional
	public boolean existeEmpleadoPorCurp(String curp) {
		String sqlQuery = "SELECT COUNT(*) FROM MIS_EMPLEADOS WHERE CURP = :CURP";
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery(sqlQuery);
		query.setParameter("CURP", curp);
		int count = ((BigDecimal) query.uniqueResult()).intValue();
		return count > 0;
	}

	@Override
	@Transactional
	public MisEmpleados getById(Long idEmpleado) {
	    Session session = sessionFactory.getCurrentSession();
	    return (MisEmpleados) session.get(MisEmpleados.class, idEmpleado);
	}

	@Override
	@Transactional
	public List<MisEmpleados> obtieneEmpleadosM() {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria criteria = session.createCriteria(MisEmpleados.class); 
		criteria.add(Restrictions.eq("sexo", "M"));
		return (List<MisEmpleados>) criteria.list();
	}

	@Override
	@Transactional
	public List<MisEmpleados> obtieneEmpleadosF() {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria criteria = session.createCriteria(MisEmpleados.class); 
		criteria.add(Restrictions.eq("sexo", "F"));
		criteria.add(Restrictions.eq("edad", Long.valueOf(35)));
		return (List<MisEmpleados>) criteria.list();
	}
}