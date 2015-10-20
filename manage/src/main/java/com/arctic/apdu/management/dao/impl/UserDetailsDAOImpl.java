package com.arctic.apdu.management.dao.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.arctic.apdu.management.dao.UserDetailsDAO;
import com.arctic.apdu.management.model.UserDetails;
import com.arctic.apdu.management.utils.CoreQueryConstants;

@Repository
public class UserDetailsDAOImpl implements UserDetailsDAO {

	private static final Logger logger = Logger.getLogger(UserDetailsDAOImpl.class);
	 
	@Autowired
	private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
    
	@Override
	public void addUserDetails(UserDetails ud) {
		Session session = this.sessionFactory.getCurrentSession();
        session.persist(ud);
        logger.info("User Details saved successfully, User Details="+ ud);
	}

	@Override
	public void updateUserDetails(Map<String, Object> inMap) {
//		Session session = this.sessionFactory.getCurrentSession();
//        session.persist(ud);
        logger.info("User Details updated successfully, User Details="+inMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserDetails> getAllSubUsers(BigInteger parentId) {
		Session session = this.sessionFactory.getCurrentSession();
        List<UserDetails> userDetailsList = session.createQuery(CoreQueryConstants.SUB_USER_BY_PARENT).setString("0", parentId.toString()).list();
        return userDetailsList;
	}

	@Override
	public UserDetails userDetailsByIdPk(BigInteger id) {
		Session session = this.sessionFactory.getCurrentSession();      
		UserDetails ud = (UserDetails) session.load(UserDetails.class, id);
        logger.info("User Details loaded successfully, User Details="+ud);
        return ud;
	}
	
	@Override
	public UserDetails userDetailsByUserId(String userId, BigInteger idPk) {
		Session session = this.sessionFactory.getCurrentSession();    
		String queryString = CoreQueryConstants.USER_BY_USER_ID;
		Query query = null;
		if(null != idPk) {
			queryString = queryString.concat(CoreQueryConstants.ADD_ID_PK);
			query = session.createQuery(queryString).setString("0", userId);
			query.setBigInteger("1", idPk);
		} else {
			query = session.createQuery(queryString).setString("0", userId);
		}
		UserDetails ud = (UserDetails) query.uniqueResult();
        logger.info("User Details loaded successfully, User Details="+ud);
        return ud;
	}
	
	@Override
	public UserDetails userDetailsByMobile(String userMobile, BigInteger idPk) {
		Session session = this.sessionFactory.getCurrentSession();  
		String queryString = CoreQueryConstants.USER_BY_MOBILE;
		Query query = null;
		if(null != idPk) {
			queryString = queryString.concat(CoreQueryConstants.ADD_ID_PK);
			query = session.createQuery(queryString).setString("0", userMobile);
			query.setBigInteger("1", idPk);
		} else {
			query = session.createQuery(queryString).setString("0", userMobile);
		}
		UserDetails ud = (UserDetails) query.uniqueResult();
        logger.info("User Details loaded successfully, User Details="+ud);
        return ud;
	}
	
	@Override
	public UserDetails userDetailsByEmail(String userEmail, BigInteger idPk) {
		Session session = this.sessionFactory.getCurrentSession(); 
		String queryString = CoreQueryConstants.USER_BY_EMAIL;
		Query query = null;
		if(null != idPk) {
			queryString = queryString.concat(CoreQueryConstants.ADD_ID_PK);
			query = session.createQuery(queryString).setString("0", userEmail);
			query.setBigInteger("1", idPk);
		} else {
			query = session.createQuery(queryString).setString("0", userEmail);
		}
		UserDetails ud = (UserDetails) query.uniqueResult();
        logger.info("User Details loaded successfully, User Details="+ud);
        return ud;
	}
	
	@Override
	public void saveOrUpdate(Object obj) {
		Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(obj);
	}

}
