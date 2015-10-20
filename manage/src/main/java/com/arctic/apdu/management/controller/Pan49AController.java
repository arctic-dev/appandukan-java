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

import com.arctic.apdu.management.service.IPan49A;
import com.arctic.apdu.management.utils.CoreURIConstants;

@RestController
public class Pan49AController {
	
	@Autowired
	private IPan49A pan49A;
	
	@RequestMapping(value = CoreURIConstants.PAN_49A_CREATE, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> createPan49A(@RequestBody String json){
		return new ResponseEntity<Map<String, Object>>(pan49A.createPan49A(json), HttpStatus.OK);
	}
	
	@RequestMapping(value = CoreURIConstants.PAN_49A_FETCH, method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> fetchPan49A(@PathVariable String userId){
		return new ResponseEntity<Map<String, Object>>(pan49A.fetchPan49A(userId), HttpStatus.OK);
	}

}
