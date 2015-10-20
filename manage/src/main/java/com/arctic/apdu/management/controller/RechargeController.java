package com.arctic.apdu.management.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arctic.apdu.management.service.IRechargeMast;
import com.arctic.apdu.management.utils.CoreURIConstants;

@RestController
public class RechargeController {
	
	@Autowired
	private IRechargeMast rechargeMast;
	
	@RequestMapping(value = CoreURIConstants.GET_PROVIDER_LIST, method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getProviders(@PathVariable String prodCode){
		return new ResponseEntity<Map<String, Object>>(rechargeMast.getProviders(prodCode), HttpStatus.OK);
	}
	
	@RequestMapping(value = CoreURIConstants.NEW_RECHARGE, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> makeNewRecharge(@RequestBody String json) {
		return new ResponseEntity<Map<String, Object>>(rechargeMast.makeNewRecharge(json), HttpStatus.OK);
	}
	
	@RequestMapping(value = CoreURIConstants.COMPLETED_RECHARGE, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> completedRecharge(@RequestBody String json) {
		return new ResponseEntity<Map<String, Object>>(rechargeMast.completedRecharge(json), HttpStatus.OK);
	}

}
