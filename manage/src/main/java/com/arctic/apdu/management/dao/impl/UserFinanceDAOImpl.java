package com.arctic.apdu.management.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.arctic.apdu.management.dao.UserFinanceDAO;
import com.arctic.apdu.management.model.UserFinance;
import com.arctic.apdu.management.utils.CoreQueryConstants;

@Repository
public class UserFinanceDAOImpl implements UserFinanceDAO {

	private static final Logger logger = Logger.getLogger(Pan49ADAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
    
    @Override
	public UserFinance getUserFinance(String userId) {
		Session session = this.sessionFactory.getCurrentSession();      
		Query query = session.createQuery(CoreQueryConstants.USER_FIN_BY_USER_ID).setString("0", userId);
		UserFinance ufin = (UserFinance) query.uniqueResult();
        logger.info("User Details loaded successfully, User Details="+ufin);
        return ufin;
	}
    
    @Override
	public void saveOrUpdate(Object obj) {
		Session session = this.sessionFactory.getCurrentSession();
        session.persist(obj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserFinance> getSubUserFinance(BigInteger id) {
		Session session = this.sessionFactory.getCurrentSession();
        List<UserFinance> userFinanceList = session.createQuery(CoreQueryConstants.SUB_USER_FINANCE).setBigInteger("0", id).list();
        return userFinanceList;
	}
    
}
