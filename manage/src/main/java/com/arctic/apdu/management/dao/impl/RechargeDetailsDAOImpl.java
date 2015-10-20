package com.arctic.apdu.management.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.arctic.apdu.management.dao.RechargeDetailsDAO;
import com.arctic.apdu.management.model.RechargeDetails;
import com.arctic.apdu.management.utils.CoreConstants;
import com.arctic.apdu.management.utils.CoreQueryConstants;

@Repository
public class RechargeDetailsDAOImpl implements RechargeDetailsDAO {
	
private static final Logger logger = Logger.getLogger(RechargeDetailsDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

	@Override
	public void saveOrUpdate(Object obj) {
		logger.info("Object Saved");
		Session session = this.sessionFactory.getCurrentSession();
        session.persist(obj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RechargeDetails> completedRecharge(JSONObject input) throws JSONException {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(CoreQueryConstants.RECHARGE_BY_USER);
		query.setString("0", input.getString(CoreConstants.CURRENT_USER_ID));
		query.setString("1", input.getString(CoreConstants.PROD_CODE));
		if(null != input.getString(CoreConstants.LIMIT)) {
			query.setMaxResults(input.getInt(CoreConstants.LIMIT));
		}
		List<RechargeDetails> rechargeList = query.list();
		return rechargeList;
	}
	


}
