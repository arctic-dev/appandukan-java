package com.arctic.apdu.management.dao;

import java.math.BigInteger;
import java.util.List;

import com.arctic.apdu.management.model.Itrx;

public interface ItrxDAO {

	public void saveOrUpdate(Object obj);
	
	public List<Itrx> getitrxlist();
	
	public Itrx getitrxformlist(BigInteger itrxid);
	
	public List<Itrx> getitrxlistcreatedby(String createdby);
	
	

}
