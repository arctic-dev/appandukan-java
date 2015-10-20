package com.arctic.apdu.management.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.arctic.apdu.management.dao.RechargeMastDAO;
import com.arctic.apdu.management.model.RechargeMast;
import com.arctic.apdu.management.utils.CoreQueryConstants;

@Repository
public class RechargeMastDAOImpl implements RechargeMastDAO {
	
private static final Logger logger = Logger.getLogger(RechargeMastDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getProviders(String prodCode) {
		logger.info("START METHOD");
		Session session = this.sessionFactory.getCurrentSession();
		List<String> providers = session.createQuery(CoreQueryConstants.PROVIDERS_BY_PROD).setString("0", prodCode).list();
		return providers;
	}

	@Override
	public RechargeMast getRechargePlan(String provider) {
		logger.info("START METHOD");
		Session session = this.sessionFactory.getCurrentSession();
		RechargeMast rm = (RechargeMast) session.createQuery(CoreQueryConstants.PROVIDERS_BY_NAME).setString("0", provider).uniqueResult();
		return rm;
	}
	


}
