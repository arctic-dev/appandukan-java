package com.arctic.apdu.management.controller;

import java.math.BigInteger;
import java.util.Map;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arctic.apdu.management.service.IUserDetails;
import com.arctic.apdu.management.utils.CoreURIConstants;
 
@RestController
public class UserDetailsController {
	
	@Autowired
	private IUserDetails userDetails;
	
	@RequestMapping(value = CoreURIConstants.CHECK_UNIQUE_ID, method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> checkUniqueUserId(@PathVariable("userId") String userId){
		return new ResponseEntity<Map<String, Object>>(userDetails.checkUniqueId(userId), HttpStatus.OK);
	}
	
	@RequestMapping(value = CoreURIConstants.CHECK_UNIQUE_EMAIL, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> checkUniqueUserEmail(@RequestBody String json){
		return new ResponseEntity<Map<String, Object>>(userDetails.checkUniqueEmail(json), HttpStatus.OK);
	}
	
	@RequestMapping(value = CoreURIConstants.CHECK_UNIQUE_MOBILE, method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> checkUniqueUserMobile(@PathVariable("userMobile") String userMobile){
		return new ResponseEntity<Map<String, Object>>(userDetails.checkUniqueMobile(userMobile), HttpStatus.OK);
	}
	
	@RequestMapping(value= CoreURIConstants.CREATE_USER, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> addUserDetails(@RequestBody String json) {
		return new ResponseEntity<Map<String, Object>>(this.userDetails.addUserDetails(json), HttpStatus.OK);
	}

	@RequestMapping(value = CoreURIConstants.GET_SUB_USERS, method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getAllSubUsers(@PathVariable("idPk") BigInteger id) {
		Map<String, Object> userDetailsList = this.userDetails.getAllSubUsers(id);
	    return new ResponseEntity<Map<String, Object>>(userDetailsList, HttpStatus.OK);
	}
	
	@RequestMapping(value= CoreURIConstants.LOGIN_USER, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> userLogin(@RequestBody String json) {
        return new ResponseEntity<Map<String, Object>>(this.userDetails.userLogin(json), HttpStatus.OK);
	}
	
	@RequestMapping(value= CoreURIConstants.UPDATE_USER, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> updateUserDetails(@RequestBody String json) {
		return new ResponseEntity<Map<String, Object>>(this.userDetails.updateUserDetails(json), HttpStatus.OK);
	}
	
	@RequestMapping(value = CoreURIConstants.GET_USER_INFO, method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getUserDetailsById(@PathVariable("idPk") BigInteger id){
		Map<String, Object> ud =  this.userDetails.userDetailsByIdPk(id);
	    return new ResponseEntity<Map<String, Object>>(ud, HttpStatus.OK);
	}
	
	@RequestMapping(value = CoreURIConstants.USER_ADD_BAL, method = RequestMethod.POST) 
	public ResponseEntity<Map<String, Object>> addUserBal(@RequestBody String json) {
        return new ResponseEntity<Map<String, Object>>(this.userDetails.addUserBal(json), HttpStatus.OK);
	}
	
	@RequestMapping(value = CoreURIConstants.USER_DEDUCT_BAL, method = RequestMethod.POST) 
	public ResponseEntity<Map<String, Object>> deductUserBal(@RequestBody String json) {
        return new ResponseEntity<Map<String, Object>>(this.userDetails.deductUserBal(json), HttpStatus.OK);
	}
	
	@RequestMapping(value = CoreURIConstants.SUB_USER_FINANCE, method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getSubUserFinance(@PathVariable("idPk") BigInteger id) {
		Map<String, Object> userFinanceList = this.userDetails.getSubUserFinance(id);
	    return new ResponseEntity<Map<String, Object>>(userFinanceList, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = CoreURIConstants.ITRX_REGTN, method = RequestMethod.POST) 
	public ResponseEntity<Map<String, Object>> additrxregter(@RequestBody String json) throws JSONException {	
        return new ResponseEntity<Map<String, Object>>(this.userDetails.additrxreg(json), HttpStatus.OK);
	}
	
	
	
	
	
	@RequestMapping(value = CoreURIConstants.GET_USER_PRD_ACCESS, method = RequestMethod.POST) 
	public ResponseEntity<Map<String, Object>> getuserprdaccs(@RequestBody String json) throws JSONException {	
		
		System.out.println("test the vlaue--->");
		
        return new ResponseEntity<Map<String, Object>>(this.userDetails.getuserprdaccess(json), HttpStatus.OK);
	}
	
     
       
}




 

 

 


