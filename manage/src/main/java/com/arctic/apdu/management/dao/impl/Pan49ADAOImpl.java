package com.arctic.apdu.management.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.arctic.apdu.management.dao.Pan49ADAO;
import com.arctic.apdu.management.model.Pan49A;
import com.arctic.apdu.management.model.PanCoupons;
import com.arctic.apdu.management.utils.CoreQueryConstants;

@Repository
public class Pan49ADAOImpl implements Pan49ADAO {
	
	private static final Logger logger = Logger.getLogger(Pan49ADAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

	@Override
	public Pan49A getPan49AByMobile(String contactNo) {
		Session session = this.sessionFactory.getCurrentSession();      
		Query query = session.createQuery(CoreQueryConstants.PAN_BY_CONTACT_NO).setString("0", contactNo);
		Pan49A pan49 = (Pan49A) query.uniqueResult();
        logger.info("User Details loaded successfully, User Details="+pan49);
        return pan49;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PanCoupons getPanCoupon() {
		Session session = this.sessionFactory.getCurrentSession();
		List<PanCoupons> couponsList = (List<PanCoupons>) session.createQuery("from com.arctic.apdu.management.model.PanCoupons").list();
		return null != couponsList.get(0) ? couponsList.get(0) : null;
	}
	
	@Override
	public void saveOrUpdate(Object obj) {
		Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(obj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pan49A> fetchPan49A(String userId) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Pan49A> pan49AList = session.createQuery(CoreQueryConstants.PAN_BY_USER).setString("0", userId).list();
		return pan49AList;
	}
	
	@Override
	public Pan49A getPanByCoupon(String couponNo) {
		Session session = this.sessionFactory.getCurrentSession();
		Pan49A pan = (Pan49A) session.createQuery(CoreQueryConstants.PAN_BY_COUPON).setString("0", couponNo).uniqueResult();
		return pan;
	}
	
	@Override
	public void deletePanCoupon(PanCoupons coupon) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(coupon);
	}
	


}
