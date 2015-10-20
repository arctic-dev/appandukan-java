package com.arctic.apdu.management.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.arctic.apdu.management.dao.ProductsDAO;
import com.arctic.apdu.management.model.Products;
import com.arctic.apdu.management.utils.CoreQueryConstants;

@Repository
public class ProductsDAOImpl implements ProductsDAO {
	
	private static final Logger logger = Logger.getLogger(ProductsDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    @SuppressWarnings("unchecked")
	@Override
	public List<Products> getAvailableProducts(String userId) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(CoreQueryConstants.GET_AVAIL_PROD).setString("0", userId);
		List<Products> availProductsList = query.list();
        for(Products prod : availProductsList) {
            logger.info("UserDetails List::"+prod);
        }
        return availProductsList;
	}
    
	@Override
	public void updateProductDetails(Products prod) {
		// TODO Auto-generated method stub
		
	}


}
