package com.api.controllers;
import java.io.BufferedInputStream;
import java.io.BufferedReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.api.requestModels.User;

import com.api.responseModels.Response;
import com.api.responseModels.TokenErrorRes;

import com.api.utils.RSAUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/v1/signature")
public class SignatureController {	

	@Autowired
	private HttpServletRequest request;
	ObjectMapper objectMapper = new ObjectMapper();
	RSAUtil _rsa =new RSAUtil();
	public SignatureController() {
		//this._rsa=rsa;
	}
	
	
	

	@PostMapping("/create")
	public ResponseEntity<Response>  create(@RequestBody User req){
		
		 try {
			 
//			 
//			  InputStream inputStream = new BufferedInputStream(request.getInputStream());
//			  
//			  BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
//			  BufferedInputStream binstream=new BufferedInputStream(inputStream);
//			  StringBuilder sb = new StringBuilder();
//			  var x=reader.readLine();
//			  var y=reader.lines();
//			  var z=reader.toString();
//		         String line;
//		         int c=0;
//		         while ((line = reader.readLine()) != null) {
//		         	if(c==0) {
//		         		c=1;
//		         		sb.append(line);
//		         	}else {
//		         		sb.append("\n");
//		         		sb.append(line);
//		         	}          
//		         }
//		         String requestBody = sb.toString();  			  
		 
			  String payload= objectMapper.writeValueAsString(req);			 
			  //RSAUtil _rsa =new RSAUtil();
			// String requestedBody=_rsa.getBody(request);	       
			 String encryptedtext=_rsa.encrypt(payload);
			 Response response=new Response("0", "encrypt success",null, encryptedtext);  
			 return ResponseEntity.ok(response); 
		 }catch (BadCredentialsException e){
	        	TokenErrorRes errorResponse = new TokenErrorRes (HttpStatus.BAD_REQUEST,"Invalid token");
	        	 Response response=new Response("99", "error",null, errorResponse);          
	        	 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);	        	
	        	 
	        }
	     catch (Exception e){
	        	TokenErrorRes errorResponse = new TokenErrorRes(HttpStatus.BAD_REQUEST, e.getMessage());
	        	 Response response=new Response("1", "error",null, errorResponse);    
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	    }	
	}

	@PostMapping("/compare")
	public ResponseEntity<Response>  compare(@RequestBody User req){
		 try {
			 String signature = request.getHeader("signature");
			 String payload= objectMapper.writeValueAsString(req);	 		 
			 //String encryptedtext=_rsa.encrypt(payload);
			 String isVerified="notverified";		 			     
			 String decryptedText=_rsa.decrypt(signature);
			 
			 User model = objectMapper.readValue(decryptedText, User.class);	
			 
			 if(payload.equals(decryptedText))
				 isVerified="verified";
			        
			 Response response=new Response("0", isVerified,null, decryptedText);  
			 return ResponseEntity.ok(response); 
			 

		 }catch (BadCredentialsException e){
	        	TokenErrorRes errorResponse = new TokenErrorRes (HttpStatus.BAD_REQUEST,"Invalid token");
	        	 Response response=new Response("99", "error",null, errorResponse);          
	        	 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);	        	
	        	 
	        }
	     catch (Exception e){
	        	TokenErrorRes errorResponse = new TokenErrorRes(HttpStatus.BAD_REQUEST, e.getMessage());
	        	 Response response=new Response("1", "error",null, errorResponse);    
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	    }	
	}
	@PostMapping("/decrypt")
	public ResponseEntity<Response>  decrypt(@RequestBody String  req){
		 try {
			 return ResponseEntity.ok(null); 

		 }catch (BadCredentialsException e){
	        	TokenErrorRes errorResponse = new TokenErrorRes (HttpStatus.BAD_REQUEST,"Invalid token");
	        	 Response response=new Response("99", "error",null, errorResponse);          
	        	 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);	        	
	        	 
	        }
	     catch (Exception e){
	        	TokenErrorRes errorResponse = new TokenErrorRes(HttpStatus.BAD_REQUEST, e.getMessage());
	        	 Response response=new Response("1", "error",null, errorResponse);    
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	    }	
	}
}
