package com.arctic.apdu.management.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.arctic.apdu.management.dao.OtpLogDAO;

@Repository
public class OtpLogDAOImpl implements OtpLogDAO {

	@Autowired
	private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
}
