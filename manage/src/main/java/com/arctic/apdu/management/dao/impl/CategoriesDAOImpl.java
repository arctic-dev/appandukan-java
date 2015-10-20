package com.arctic.apdu.management.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.arctic.apdu.management.dao.CategoriesDAO;
import com.arctic.apdu.management.model.Categories;
import com.arctic.apdu.management.utils.CoreQueryConstants;

@Repository
public class CategoriesDAOImpl implements CategoriesDAO {
	
	private static final Logger logger = Logger.getLogger(CategoriesDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    @SuppressWarnings("unchecked")
    @Override
	public List<Categories> getAvailableCategories(List<String> catgCodeSet) {
    	logger.info("METHOD START");
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(CoreQueryConstants.GET_CATG_NAME).setParameterList("0", catgCodeSet);
		List<Categories> availCategories = query.list();
        return availCategories;
	}

}
