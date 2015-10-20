package com.arctic.apdu.management.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arctic.apdu.management.dao.ItrxDAO;
import com.arctic.apdu.management.model.Itrx;
import com.arctic.apdu.management.model.UserDetails;
import com.arctic.apdu.management.model.UserFinance;
import com.arctic.apdu.management.model.UserProductAccess;
import com.arctic.apdu.management.service.Itrxservice;
import com.arctic.apdu.management.utils.CoreConstants;

@Service
public class Itrximpl implements Itrxservice {

	private static final Logger logger = Logger.getLogger(Itrximpl.class);

	@Autowired
	private ItrxDAO ItrxDAO;

	@Transactional
	@Override
	public Map<String, Object> createitrxform(String json) throws JSONException {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		JSONObject input = new JSONObject(json);

		Itrx itrxobj = new Itrx();

		String itrpan = input.getString(CoreConstants.ITR_PAN);
		String itrname = input.getString(CoreConstants.ITR_NAME);
		String itrbankstatement = input.getString(CoreConstants.ITR_BANKSTATEMENT);
		String itrform = input.getString(CoreConstants.ITR_FORM);
		String itrtdscertificate = input.getString(CoreConstants.ITR_ADDRPROOF);
		String itraddrproof = input.getString(CoreConstants.ITR_ADDRPROOF);
		String itrprevitr = input.getString(CoreConstants.ITR_PREVITR);
		String itrbankname = input.getString(CoreConstants.ITR_BANKNAME);
		String itrbankacctype = input.getString(CoreConstants.ITR_BANKACCTTYPE);
		String itrbankaccno = input.getString(CoreConstants.ITR_ITRBANKACCTNO);
		String itrbankifsc = input.getString(CoreConstants.ITR_BANKIFSC);

		String itrfyear = input.getString(CoreConstants.ITR_FYEAR);
		String itrmobileno = input.getString(CoreConstants.ITR_MOBILENO);
		String itremail = input.getString(CoreConstants.ITR_EMAIL);

		String itrstatus = input.getString(CoreConstants.ITR_STATUS);
		String itrclientip = input.getString(CoreConstants.ITR_CLIENTIP);

		String itrcreatedby = input.getString(CoreConstants.ITR_CREATEDBY);

		if (!"".equals(itrpan) && !"".equals(itrname) && !"".equals(itrbankstatement) && !"".equals(itraddrproof)
				&& !"".equals(itrmobileno) && !"".equals(itremail)) {

			System.out.println("test1---->" + itrbankaccno);

			itrxobj.setItrpan(itrpan);
			itrxobj.setItrname(itrname);
			itrxobj.setItrbankstatement(itrbankstatement);
			itrxobj.setItrform(itrform);
			itrxobj.setItrtdscertificate(itrtdscertificate);
			itrxobj.setItraddrproof(itraddrproof);
			itrxobj.setItrprevitr(itrprevitr);
			itrxobj.setItrbankname(itrbankname);
			itrxobj.setItrbankacctype(itrbankacctype);

			if (!"".equals(itrbankaccno)) {
				System.out.println("tsset------");
				BigInteger contvtint = new BigInteger(itrbankaccno);
				System.out.println("test rhe gvoauef -->" + contvtint);
				itrxobj.setItrbankaccno(contvtint);
			}

			itrxobj.setItrbankifsc(itrbankifsc);
			itrxobj.setItrfyear(itrfyear);
			itrxobj.setItrmobileno(itrmobileno);
			itrxobj.setItremail(itremail);
			itrxobj.setItrcreatedat(new Date());

			System.out.println("created by-------->" + itrcreatedby);
			itrxobj.setItrcreatedby(itrcreatedby);
			if (!"".equals(itrstatus)) {
				itrxobj.setItrstatus(Long.valueOf(itrstatus));
			}

			itrxobj.setItrclientip(itrclientip);

			ItrxDAO.saveOrUpdate(itrxobj);

			resultMap.put(CoreConstants.STATUS, CoreConstants.SUCCESS);
			resultMap.put(CoreConstants.MESSAGE, "ITRX added successfully");

		} else {

			resultMap.put(CoreConstants.STATUS, CoreConstants.ERROR);
			resultMap.put(CoreConstants.MESSAGE, "Please enter the mandatory fields");

		}

		return resultMap;
	}

	@Override
	@Transactional
	public Map<String, Object> getitrxform() throws JSONException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Itrx> itrxobj = ItrxDAO.getitrxlist();
		Map<String, Object> itrxlistMap = null;
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		for (Itrx itr : itrxobj) {
			itrxlistMap = new HashMap<String, Object>();
			buildITVformlist(itr, itrxlistMap);
			resultList.add(itrxlistMap);
		}
		if (!resultList.isEmpty()) {
			resultMap.put(CoreConstants.IT_UPLOAD, resultList);
			resultMap.put(CoreConstants.STATUS, CoreConstants.SUCCESS);
			resultMap.put(CoreConstants.MESSAGE, "Itrx list");
		} else {
			resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
			resultMap.put(CoreConstants.MESSAGE, "Error");
		}
		return resultMap;
	}

	private Map<String, Object> buildITVformlist(Itrx itr, Map<String, Object> resultMap) {
		resultMap.put(CoreConstants.ITR_ID, itr.getItridpk());
		resultMap.put(CoreConstants.ITR_PAN, itr.getItrpan());
		resultMap.put(CoreConstants.ITR_NAME, itr.getItrname());
		resultMap.put(CoreConstants.ITR_BANKSTATEMENT, itr.getItrbankstatement());
		resultMap.put(CoreConstants.ITR_FORM, itr.getItrform());
		resultMap.put(CoreConstants.ITR_TDCERTIFICATE, itr.getItrtdscertificate());
		resultMap.put(CoreConstants.ITR_ADDRPROOF, itr.getItraddrproof());
		resultMap.put(CoreConstants.ITR_PREVITR, itr.getItrprevitr());
		resultMap.put(CoreConstants.ITR_BANKNAME, itr.getItrbankname());
		resultMap.put(CoreConstants.ITR_BANKACCTTYPE, itr.getItrbankacctype());
		resultMap.put(CoreConstants.ITR_ITRBANKACCTNO, itr.getItrbankaccno());
		resultMap.put(CoreConstants.ITR_BANKIFSC, itr.getItrbankifsc());
		resultMap.put(CoreConstants.ITR_FYEAR, itr.getItrfyear());
		resultMap.put(CoreConstants.ITR_MOBILENO, itr.getItrmobileno());
		resultMap.put(CoreConstants.ITR_EMAIL, itr.getItremail());
		resultMap.put(CoreConstants.ITR_STATUS, itr.getItrstatus());
		resultMap.put(CoreConstants.ITR_CLIENTIP, itr.getItrclientip());
		resultMap.put(CoreConstants.ITR_CREATEDBY, itr.getItrcreatedby());
		resultMap.put(CoreConstants.ITR_CREATEDDATE, itr.getItrcreatedat());
		resultMap.put(CoreConstants.ITR_RECEIPT, itr.getItrreceipt());
		return resultMap;
	}

	@Override
	@Transactional
	public Map<String, Object> updateitrxreceipt(String json) throws JSONException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		JSONObject input = new JSONObject(json);
		String itrxid = input.getString(CoreConstants.ITR_ID);
		String itrxstatus = input.getString(CoreConstants.ITR_STATUS);
		String itrxreceipt = input.getString(CoreConstants.ITR_RECEIPT);

		if (!"".equals(itrxid) && !"".equals(itrxstatus)) {

			if (itrxstatus.equals("2") && !"".equals(itrxreceipt)) {
				Long inrx = Long.valueOf(itrxid);
				Itrx itrxobj = ItrxDAO.getitrxformlist(BigInteger.valueOf(inrx));
				/* itrxobj.setItridpk(BigInteger.valueOf(inrx)); */
				itrxobj.setItrstatus(Long.valueOf(itrxstatus));
				itrxobj.setItrreceipt(itrxreceipt);
				ItrxDAO.saveOrUpdate(itrxobj);
				resultMap.put(CoreConstants.STATUS, CoreConstants.SUCCESS);
				resultMap.put(CoreConstants.MESSAGE, "Itrx list updated successfully");
			}

			else if (itrxstatus.equals("1")) {
				resultMap.put(CoreConstants.STATUS, CoreConstants.SUCCESS);
				resultMap.put(CoreConstants.MESSAGE, "Itrx list updated successfully");
			} else {
				resultMap.put(CoreConstants.STATUS, CoreConstants.ERROR);
				resultMap.put(CoreConstants.MESSAGE, "please upload the Itrx receipt");
			}

		} else {
			resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
			resultMap.put(CoreConstants.MESSAGE, "Please enter the mandatory fields");
		}

		return resultMap;
	}

	@Override
	@Transactional
	public Map<String, Object> getitrcreatedby(String json) throws JSONException {	

		Map<String, Object> resultMap = new HashMap<String, Object>();
		JSONObject input = new JSONObject(json);

		String createdby = input.getString(CoreConstants.ITR_CREATEDBY);

		List<Itrx> itrxobj = ItrxDAO.getitrxlistcreatedby(createdby);

		Map<String, Object> itrxlistMap = null;
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		for (Itrx itr : itrxobj) {
			itrxlistMap = new HashMap<String, Object>();
			buildITVformlist(itr, itrxlistMap);
			resultList.add(itrxlistMap);
		}
		if (!resultList.isEmpty()) {
			resultMap.put(CoreConstants.IT_UPLOAD, resultList);
			resultMap.put(CoreConstants.STATUS, CoreConstants.SUCCESS);
			resultMap.put(CoreConstants.MESSAGE, "Itrx list");
		} else {
			resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
			resultMap.put(CoreConstants.MESSAGE, "Error");
		}

		return resultMap;
	}

}
