package com.api.controllers;



import com.api.requestModels.User;
import com.api.requestValidations.UserRequestValidation;
import com.api.responseModels.Response;
import com.api.responseModels.TokenErrorRes;
import com.api.servicesInterfaces.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	private  final IUserService _userService ;
	// private static final Logger logger = LogManager.getLogger("databaseAppender");
	

	@Autowired
	public UserController(IUserService userService) {
		this._userService=userService;
		
	}
	@GetMapping(value = "/list")
	public ResponseEntity<Response>  list(){
		 try {
				
			var response=_userService.list();			  
			if(response.errorCode=="0") {
				 return ResponseEntity.ok(response);
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}          

	        }catch (BadCredentialsException e){
	        	TokenErrorRes errorResponse = new TokenErrorRes (HttpStatus.BAD_REQUEST,"Invalid token");
	        	 Response response=new Response("99", "error",null, errorResponse);          
	        	 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);	        	
	        	 
	        }catch (Exception e){
	        	TokenErrorRes errorResponse = new TokenErrorRes(HttpStatus.BAD_REQUEST, e.getMessage());
	        	 Response response=new Response("1", "error",null, errorResponse);    
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	        }		 
			
	}	
	
	@PostMapping("/create")
	public ResponseEntity<Response>  create(@RequestBody User req){
		 try {
			 UserRequestValidation _userValidation =new UserRequestValidation();
			 var isValid=_userValidation.ValidateCreateUserRequest(req);
			 if(isValid.errorCode !="0") {
				 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(isValid);
			 }
			 var response=_userService.create(req);
			  
			if(response.errorCode=="0") {
				 return ResponseEntity.ok(response);
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}	 

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
	@PutMapping("/update")
	public ResponseEntity<Response>  update(@RequestBody User req){
		 try {
			 UserRequestValidation _userValidation =new UserRequestValidation();
			 var isValid=_userValidation.ValidateUpdateUserRequest(req);
			 if(isValid.errorCode !="0") {
				 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(isValid);
			 }
			 var response=_userService.create(req);
			  
			if(response.errorCode=="0") {
				 return ResponseEntity.ok(response);
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}	 

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
	
	@PostMapping("/getbyid/{id}")
	public ResponseEntity<Response> update(@PathVariable String id){
		 try {
			 if(id==null || id=="" || Integer.parseInt(id)<1) {
				 Response response=new Response("1", "error",id, "Invalid id !"); 
		         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}	 
			
		    var response=_userService.getbyid(id);			  
			if(response.errorCode=="0") {
				 return ResponseEntity.ok(response);
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}	 

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
