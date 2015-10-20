package com.arctic.apdu.management.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.CyberPlat.IPriv;
import org.CyberPlat.IPrivKey;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arctic.apdu.management.dao.ItrledgerDAO;
import com.arctic.apdu.management.dao.RechargeDetailsDAO;
import com.arctic.apdu.management.dao.RechargeMastDAO;
import com.arctic.apdu.management.dao.UserDetailsDAO;
import com.arctic.apdu.management.dao.UserFinanceDAO;
import com.arctic.apdu.management.model.Itrledger;
import com.arctic.apdu.management.model.RechargeDetails;
import com.arctic.apdu.management.model.RechargeMast;
import com.arctic.apdu.management.model.UserDetails;
import com.arctic.apdu.management.model.UserFinance;
import com.arctic.apdu.management.service.IRechargeMast;
import com.arctic.apdu.management.service.IUserFinance;
import com.arctic.apdu.management.utils.CoreConstants;

@Service
public class RechargeMastImpl implements IRechargeMast {

	private static final Logger logger = Logger.getLogger(RechargeMastImpl.class);

	@Autowired
	private RechargeMastDAO rechargeMastDAO;

	@Autowired
	private RechargeDetailsDAO rechargeDetailsDAO;

	@Autowired
	private IUserFinance userFinance;

	@Autowired
	private UserFinanceDAO userFinanceDAO;

	@Autowired
	private UserDetailsDAO userDetailsDAO;

	@Autowired
	private ItrledgerDAO itrledgerDAO;

	private static final String ENC = "windows-1251";
	private static final String SD = "1003829";
	private static final String AP = "1003851";
	private static final String OP = "1003852";
	private static final String KEYS = "/home/arcservi3/apdu/";
	// private static final String KEYS = "";
	private static final String PASS = "3333333333";
	private static int BANK_KEY_SERIAL = 64182;

	private IPrivKey sec = null;

	private IPrivKey pub = null;

	@Override
	@Transactional
	public Map<String, Object> getProviders(String prodCode) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<String> providers = rechargeMastDAO.getProviders(prodCode);
		if (null != providers && !providers.isEmpty()) {
			resultMap.put(CoreConstants.PROVIDERS, providers);
			resultMap.put(CoreConstants.STATUS, CoreConstants.SUCCESS);
			resultMap.put(CoreConstants.MESSAGE, "Service Providers returned successfully");
		} else {
			resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
			resultMap.put(CoreConstants.MESSAGE, "Service Providers not found");
		}
		return resultMap;
	}

	@Override
	@Transactional
	public Map<String, Object> makeNewRecharge(String json) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			JSONObject input = new JSONObject(json);
			resultMap = checkRechargeEligibility(input);
			if ("0".equals(resultMap.get(CoreConstants.FLAG))) {
				return resultMap;
			}
			IPriv.setCodePage(ENC);
			sec = IPriv.openSecretKey(KEYS + "secret.key", PASS);
			pub = IPriv.openPublicKey(KEYS + "pubkeys.key", BANK_KEY_SERIAL);
			RechargeMast rm = rechargeMastDAO.getRechargePlan(input.getString(CoreConstants.PROVIDER));
			UserDetails ud = userDetailsDAO.userDetailsByUserId(input.getString(CoreConstants.CURRENT_USER_ID), null);
			String number = input.getString(CoreConstants.NUMBER);
			Double amount = Double.valueOf(input.getString(CoreConstants.AMOUNT));
			String url = "http://ru-demo.cyberplat.com" + rm.getCyberplatPa();
			String session = genSession();
			String response = sendRequest(url, number, amount, session);
			logger.info("RECHARGE RESPONSE : " + response);
			String[] responseElements = response.split("\r\n");
			for (String object : responseElements) {
				String[] splitElements = object.split("=");
				if (splitElements.length == 2) {
					resultMap.put(splitElements[0].toLowerCase(), splitElements[1]);
				} else {
					resultMap.put(splitElements[0].toLowerCase(), "");
				}
			}

			RechargeDetails rd = new RechargeDetails();
			rd.setProdCode(rm.getProdCode());
			rd.setServiceProvider(rm.getName());
			rd.setNumber(BigInteger.valueOf(Long.valueOf(number)));
			rd.setAmount(amount);
			DateFormat df = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
			rd.setCreatedAt(df.parse(resultMap.get(CoreConstants.DATE).toString()));
			rd.setCreatedBy(ud.getUserId());
			rd.setCreatedType(ud.getUserType());
			rd.setTransId(resultMap.get(CoreConstants.TRANS_ID).toString());
			rd.setTransId(resultMap.get(CoreConstants.RESULT).toString());
			rd.setClientIp(input.getString(CoreConstants.CLIENT_IP));
			rd.setProvider(rm.getProvider());
			if (!resultMap.get(CoreConstants.ERROR).equals("0")) {
				rd.setResult("Failed");
				rechargeDetailsDAO.saveOrUpdate(rd);
				resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
				resultMap.put(CoreConstants.MESSAGE, rd.getResult());
				return resultMap;
			}
			rd.setResult("Pending");
			rechargeDetailsDAO.saveOrUpdate(rd);

			/* Ledger */
			Itrledger itrldgr = new Itrledger();
			itrldgr.setLrdate(new Date());
			itrldgr.setLrtranstype("DB");
			itrldgr.setLrcomment("Recharge");
			itrldgr.setLrdebitamount(amount);
			UserFinance userbalance = userFinanceDAO.getUserFinance(ud.getUserId());
			Double mainbalance = userbalance.getMainBal();
			Double currentbalance = mainbalance - amount;
			itrldgr.setLrpostbalance(currentbalance);
			itrldgr.setLrcreatedby(ud.getUserId());
			itrldgr.setLrprodcode(rm.getProdCode());
			itrledgerDAO.saveOrUpdate(itrldgr);
			
			
			url = "http://ru-demo.cyberplat.com" + rm.getCyberplatPr();
			response = sendRequest(url, number, amount, session);
			logger.info("RECHARGE RESPONSE : " + response);
			responseElements = response.split("\r\n");
			for (String object : responseElements) {
				String[] splitElements = object.split("=");
				if (splitElements.length == 2) {
					resultMap.put(splitElements[0].toLowerCase(), splitElements[1]);
				} else {
					resultMap.put(splitElements[0].toLowerCase(), "");
				}
			}
			if (!resultMap.get(CoreConstants.ERROR).equals("0")) {
				rd.setResult("Failed");
				rechargeDetailsDAO.saveOrUpdate(rd);
				resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
				resultMap.put(CoreConstants.MESSAGE, rd.getResult());
				return resultMap;
			}
			rd.setAuthCode(null != resultMap.get(CoreConstants.AUTH_CODE)
					? resultMap.get(CoreConstants.AUTH_CODE).toString() : null);
			rechargeDetailsDAO.saveOrUpdate(rd);

			url = "http://ru-demo.cyberplat.com" + rm.getCyberplatPs();
			response = sendRequest(url, number, amount, session);
			logger.info("RECHARGE RESPONSE : " + response);
			responseElements = response.split("\r\n");
			for (String object : responseElements) {
				String[] splitElements = object.split("=");
				if (splitElements.length == 2) {
					resultMap.put(splitElements[0].toLowerCase(), splitElements[1]);
				} else {
					resultMap.put(splitElements[0].toLowerCase(), "");
				}
			}
			if (resultMap.get(CoreConstants.RESULT).equals("1")) {
				rd.setResult("Failed");
				rechargeDetailsDAO.saveOrUpdate(rd);
				resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
				resultMap.put(CoreConstants.MESSAGE, rd.getResult());
				return resultMap;
			} else {
				userFinance.updateUserFinance(input, resultMap);

				if (resultMap.get(CoreConstants.RESULT).equals("7")) {
					rd.setResult("Success");
					rechargeDetailsDAO.saveOrUpdate(rd);
				} else {
					rd.setResult("Pending");
					rechargeDetailsDAO.saveOrUpdate(rd);
				}
			}
			done();
			resultMap.put(CoreConstants.STATUS, CoreConstants.SUCCESS);
			resultMap.put(CoreConstants.MESSAGE, "Mobile Recharge submitted successfully");

		} catch (Exception e) {
			logger.error(e.getMessage());
			resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
			resultMap.put(CoreConstants.MESSAGE, e.toString());
		}
		return resultMap;
	}

	@Transactional
	private Map<String, Object> checkRechargeEligibility(JSONObject input) throws JSONException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put(CoreConstants.FLAG, "1");
		UserFinance ufin = this.userFinanceDAO.getUserFinance(input.getString(CoreConstants.CURRENT_USER_ID));
		Double mainBalance = ufin.getMainBal();
		if (mainBalance < input.getDouble(CoreConstants.AMOUNT)) {
			resultMap.put(CoreConstants.FLAG, "0");
			resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
			resultMap.put(CoreConstants.MESSAGE, "Insufficient Balance");
			return resultMap;
		}
		return resultMap;
	}

	private String genSession() {
		String rc = new String();
		rc += "JAVA" + Calendar.getInstance().getTimeInMillis() / 1000;
		return rc;
	}

	String sendRequest(String url, String number, double amount, String session)
			throws MalformedURLException, IOException, Exception {
		String req = "SD=" + SD + "\r\n" + "AP=" + AP + "\r\n" + "OP=" + OP + "\r\n" + "SESSION=" + session + "\r\n"
				+ "NUMBER=" + number + "\r\n" + "AMOUNT=" + amount + "\r\n";

		req = "inputmessage=" + URLEncoder.encode(sec.signText(req));

		URL u = new URL(url);
		URLConnection con = u.openConnection();
		con.setDoOutput(true);

		con.getOutputStream().write(req.getBytes());
		con.getOutputStream().close();

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), ENC));
		char[] raw_resp = new char[1024];
		int raw_resp_len = in.read(raw_resp);
		StringBuffer s = new StringBuffer();
		s.append(raw_resp, 0, raw_resp_len);
		String resp = s.toString();

		resp = pub.verifyText(resp);
		return resp;
	}

	private void done() {
		if (sec != null)
			sec.closeKey();
		if (pub != null)
			pub.closeKey();
	}

	@Override
	@Transactional
	public Map<String, Object> completedRecharge(String json) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			JSONObject input = new JSONObject(json);
			List<RechargeDetails> rechargeList = rechargeDetailsDAO.completedRecharge(input);
			if (null != rechargeList && !rechargeList.isEmpty()) {
				resultMap.put(CoreConstants.SUBMIT_RECHARGE, rechargeList);
				resultMap.put(CoreConstants.STATUS, CoreConstants.SUCCESS);
				resultMap.put(CoreConstants.MESSAGE, "Submitted recharges returned successfully");
			} else {
				resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
				resultMap.put(CoreConstants.MESSAGE, "Recharge requests not found");
			}
		} catch (JSONException e) {
			logger.error(e.getMessage());
			resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
			resultMap.put(CoreConstants.MESSAGE, e.toString());
		}
		return resultMap;
	}
}
