package com.arctic.apdu.management.controller;

import java.util.Map;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arctic.apdu.management.service.Itrxservice;
import com.arctic.apdu.management.utils.CoreURIConstants;

@RestController
public class ItrxController {

	@Autowired

	private Itrxservice itrxservice;

	@RequestMapping(value = CoreURIConstants.ITRX_FORM_REGTN, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> createitrxform(@RequestBody String json) throws JSONException {

		return new ResponseEntity<Map<String, Object>>(itrxservice.createitrxform(json), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = CoreURIConstants.GET_ITRX_LIST, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> getitrxformtable() throws JSONException {

		return new ResponseEntity<Map<String, Object>>(itrxservice.getitrxform(), HttpStatus.OK);
	}
	
	@RequestMapping(value = CoreURIConstants.UPDATE_ITR_RECEIPT, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> getupdateitrx(@RequestBody String json) throws JSONException {

		return new ResponseEntity<Map<String, Object>>(itrxservice.updateitrxreceipt(json), HttpStatus.OK);
	}
	
	@RequestMapping(value = CoreURIConstants.GET_ITRX_LISTFILTER_CREATED, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> getitrxfiltercreadby(@RequestBody String json) throws JSONException {

		return new ResponseEntity<Map<String, Object>>(itrxservice.getitrcreatedby(json), HttpStatus.OK);
	}
	


}
