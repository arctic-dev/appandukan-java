package com.arctic.apdu.management.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.arctic.apdu.management.dao.ItrxDAO;
import com.arctic.apdu.management.model.Itrx;
import com.arctic.apdu.management.model.UserFinance;
import com.arctic.apdu.management.utils.CoreQueryConstants;

@Repository
public class ItrxDAOImpl implements ItrxDAO {

	private static final Logger logger = Logger.getLogger(ItrxDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/*
	 * @Override public void saveOrUpdate(Itrx itr) {
	 * 
	 * Session session = this.sessionFactory.getCurrentSession();
	 * session.saveOrUpdate(itr);
	 * 
	 * }
	 */

	@Override
	@SuppressWarnings("unchecked")
	public List<Itrx> getitrxlist() {

		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(CoreQueryConstants.GET_ITRX_LIST);
		List<Itrx> upa = (List<Itrx>) query.list();
		logger.info("Itrx Details loaded successfully, Itrx Details=" + upa);

		return upa;
	}

	@Override
	public void saveOrUpdate(Object obj) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(obj);

	}

	@Override
	public Itrx getitrxformlist(BigInteger itrxid) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(CoreQueryConstants.GET_ITRX_FILTER_LIST).setBigInteger("0", itrxid);
		Itrx ufin = (Itrx) query.uniqueResult();
		logger.info("User Details loaded successfully, User Details=" + ufin);
		return ufin;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Itrx> getitrxlistcreatedby(String createdby) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(CoreQueryConstants.GET_ITRX_FILTER_CREATEDBY).setString("0", createdby);
		List<Itrx> upa = (List<Itrx>) query.list();
		logger.info("Itrx Details loaded successfully, Itrx Details=" + upa);

		return upa;
	}

}
