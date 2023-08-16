package com.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.authorization.JwtUtil;
import com.api.requestModels.GetToken;
import com.api.responseModels.Response;
import com.api.responseModels.TokenErrorRes;
import com.api.responseModels.TokenResponse;
import com.api.responseModels.UserToken;
import com.api.servicesInterfaces.IUserService;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	   private final AuthenticationManager _authenticationManager;
	   private  final IUserService _userService ;
	    private JwtUtil _jwtUtil;
	    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil,IUserService userService ) {
	        this._authenticationManager = authenticationManager;
	        this._jwtUtil = jwtUtil;
	        this._userService=userService;

	    }	    
	   
		@PostMapping("/token")
		public ResponseEntity<Response>  token(@RequestBody GetToken req){		
				 
				 if(req.getEmail()==null || req.getEmail()=="") {
					 Response response=new Response("1", "Email is required",null, null);  
					 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
				 }
				 if(req.getPassword()==null || req.getPassword()=="") {
					 Response response=new Response("1", "Password is required",null, null);  
					 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
				 }
				  try {
					  
					  var doesUserExists=_userService.getUserByEmail(req.getEmail(), req.getPassword());
					  if(doesUserExists.errorCode!="0") {
						  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(doesUserExists);
					  }
			          //  Authentication authentication =
			                 //   _authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
			           // String email = authentication.getName();
			            UserToken user = new UserToken(req.getEmail(),"");
			            String token = _jwtUtil.createToken(user);
			            TokenResponse loginRes = new TokenResponse(req.getEmail(),token);
			            Response response=new Response("0", "success",null, loginRes);		            
			            return ResponseEntity.ok(response);	            

			        }catch (BadCredentialsException e){
			        	TokenErrorRes errorResponse = new TokenErrorRes (HttpStatus.BAD_REQUEST,"Invalid username or password");
			            
			        	 Response response=new Response("3", "error",null, errorResponse);            
			        	 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);	        	
			        	 
			        }catch (Exception e){
			        	TokenErrorRes errorResponse = new TokenErrorRes(HttpStatus.BAD_REQUEST, e.getMessage());
			        	 Response response=new Response("1", "error",null, errorResponse); 
			            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			        }
				
				  
				
		}
}
