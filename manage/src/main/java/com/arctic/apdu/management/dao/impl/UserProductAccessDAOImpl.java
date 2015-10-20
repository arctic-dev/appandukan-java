package com.arctic.apdu.management.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.arctic.apdu.management.dao.UserProductAccessDAO;
import com.arctic.apdu.management.model.UserProductAccess;
import com.arctic.apdu.management.utils.CoreQueryConstants;

@Repository
public class UserProductAccessDAOImpl implements UserProductAccessDAO {
	
	private static final Logger logger = Logger.getLogger(UserProductAccessDAOImpl.class);
	 
	@Autowired
    private SessionFactory sessionFactory;
     
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
    
    @Override
	public void addProductAccess(UserProductAccess upa) {
		Session session = this.sessionFactory.getCurrentSession();
        session.persist(upa);
        logger.info("User Details saved successfully, User Details="+ upa);
	}
    
    @Override
	public UserProductAccess getUserProductAccess(String userId, String prodCode) {
		Session session = this.sessionFactory.getCurrentSession();      
		Query query = session.createQuery(CoreQueryConstants.ACCESS_BY_USER_AND_PROD).setString("0", userId);
		query.setString("1", prodCode);
		UserProductAccess upa = (UserProductAccess) query.uniqueResult();
        logger.info("User Details loaded successfully, User Details="+upa);
        return upa;
	}

	@Override
	public void saveUserProductAccess(UserProductAccess upa) {
		Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(upa);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserProductAccess> getUsrprodaccess(String prodCode) {
		
		Session session = this.sessionFactory.getCurrentSession();      
		Query query = session.createQuery(CoreQueryConstants.GET_USER_AND_PROD).setString("0", prodCode);		
		List<UserProductAccess> upa = (List<UserProductAccess>) query.list();
        logger.info("User Details loaded successfully, User Details="+upa);
	 
		return upa;
	}

}
