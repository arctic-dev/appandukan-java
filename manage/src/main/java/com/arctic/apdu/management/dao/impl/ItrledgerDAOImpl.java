package com.arctic.apdu.management.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.arctic.apdu.management.dao.ItrledgerDAO;

@Repository
public class ItrledgerDAOImpl implements ItrledgerDAO {

	private static final Logger logger = Logger.getLogger(ItrledgerDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void saveOrUpdate(Object obj) {

		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(obj);

	}

}
