package com.arctic.apdu.management.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.arctic.apdu.management.model.Categories;
import com.arctic.apdu.management.model.Products;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CoreUtils {
	
	private static final Logger logger = Logger.getLogger(CoreUtils.class);

	public static Map<String, Object> jsonToMap(String input) throws JsonParseException, JsonMappingException, IOException {
		Map<String, Object> inputMap = null;
		ObjectMapper objectMapper = new ObjectMapper();
		inputMap = objectMapper.readValue(input, new TypeReference<HashMap<String, Object>>() {});
		return inputMap;
	}
	
	public static String getSaltedHash(String userKey, String saltString) {
		String hashedKey = null;
		SecureRandom random = new SecureRandom();
		if(null == saltString) {
			byte[] salt = new byte[32];
			random.nextBytes(salt);
			saltString = getHexValues(salt);
		}
		logger.info("SALT: " + saltString);
        
		String toHash = saltString.concat(userKey); 
		System.out.println("TO HASH: " + toHash);
        MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(toHash.getBytes("UTF-8"));
			logger.info("BYTE HASH: " + hash.toString());
			logger.info(getHexValues(hash));
			hashedKey = saltString.concat(":").concat(getHexValues(hash));
			logger.info("HASHED KEY: " + hashedKey);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hashedKey;
	}
	
	private static String getHexValues(byte[] byteData) {
		StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
	}
	
	public static Map<String, Object> incorrectResponseMap() {
		Map<String, Object> messageMap = new HashMap<String, Object>();
		messageMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
		messageMap.put(CoreConstants.MESSAGE, "Incorrect json input");
		return messageMap;
	}
	
	public static List<Map<String, Object>> processProdResult(List<Products> prodList, List<String> catgCodes) {
		List<Map<String, Object>> prodResult = new ArrayList<Map<String, Object>>();
		Map<String, Object> prodMap = null;
		for(Products prod : prodList) {
			prodMap = new HashMap<String, Object>();
			prodMap.put(CoreConstants.PROD_SHORT_NAME, prod.getShortName());
			prodMap.put(CoreConstants.PROD_FULL_NAME, prod.getFullName());
			prodMap.put(CoreConstants.PROD_CODE, prod.getCode());
			prodMap.put(CoreConstants.PROD_CATG_CODE, prod.getCatgCode());
			prodMap.put(CoreConstants.PROD_STATUS, "1");
			catgCodes.add(prod.getCatgCode());
			prodResult.add(prodMap);
		}
		return prodResult;
	}
	
	public static List<Map<String, Object>> processCatgResult(List<Categories> catgList) {
		List<Map<String, Object>> catgResult = new ArrayList<Map<String, Object>>();
		Map<String, Object> catgMap = null;
		for(Categories catg : catgList) {
			catgMap = new HashMap<String, Object>();
			catgMap.put(CoreConstants.CATG_FULL_NAME, catg.getFullName());
			catgMap.put(CoreConstants.CATG_CODE, catg.getCode());
			catgResult.add(catgMap);
		}
		return catgResult;
	}
	
}
