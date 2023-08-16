package com.api.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.api.dbModels.Tbl_User;
import com.api.logging.Log4jLogger;
import com.api.repositoriesInterfaces.IUserRepository;
import com.api.requestModels.User;
import com.api.responseModels.Response;
import com.api.servicesInterfaces.IUserService;

@Service
public class UserService implements IUserService{	
	
	
	Log4jLogger logger=new Log4jLogger();

	private final IUserRepository _userRepository;	
	@Autowired
	public UserService(IUserRepository userRepository) {
		this._userRepository=userRepository;
	}
	 @Override
	public Response create(User req) {
		
		try {		
			
			 Tbl_User model =new Tbl_User();
			 model.setFirstName(req.getFirstName());
			 model.setLastName(req.getLastName());
			 model.setemail(req.getEmail());
			 model.setPassword(req.getPassword());
			 model.setIsActive('n');
			 model.setisLocked('n');
			 model.setisVerified('n');
			 model.setCreatedDate(LocalDateTime.now());
			 model.setUpdatedDate(null);
			 model.setUserId(UUID.randomUUID().toString()); 	 
			 var result=_userRepository.save(model);		 			 
			 Response response=new Response("0", "success",null, result);
			 return response;  
			}catch (Exception e){		        	
			 Response response=new Response("2", "error",null, e.getMessage()); 
			 return response;  
			 }			
		
	}
	 @Override
		public Response update(User req) {
			
			try {		
				long requestedId=Integer.parseInt(req.getrequestedId());
				Optional<Tbl_User> optionalUser = _userRepository.findById(requestedId);
				if (optionalUser.isPresent()) {
				    // Modify the fields of the entity object
					Tbl_User user = optionalUser.get();
					user.setFirstName(req.getFirstName());
					user.setLastName(req.getLastName());
					user.setUpdatedDate(LocalDateTime.now());				    
				    // Save the entity
					 var result=_userRepository.save(user);
					 Response response=new Response("0", "success",null, result);
					 return response;  
				}else {
					Response response=new Response("1", "error",null, "User not exists.");
					 return response;
				}			 
				
				}catch (Exception e){		        	
				 Response response=new Response("2", "error",null, e.getMessage()); 
				 return response;  
		    }			
			
		}
	@Override
	public Response getbyid(String req) {
		try {		
			long requestedId=Integer.parseInt(req);
			Optional<Tbl_User> optionalUser = _userRepository.findById(requestedId);
			if (optionalUser.isPresent()) {			   
				Tbl_User user = optionalUser.get();				
				 Response response=new Response("0", "success",null, user);
				 return response;  
			}else {
				Response response=new Response("1", "error",null, "User not exists.");
				 return response;
			}			 
			
			}catch (Exception e){		        	
			 Response response=new Response("2", "error",null, e.getMessage()); 
			 return response;  
	    }			
	}
	@Override
	public Response list() {
		try {				 
			
		logger.info("test log started");
		logger.error("This is an error message");		
		logger.debug("test success This is an info message");
		logger.warn("This is an error message");	
		logger.fatal("test success This is an info message");
		logger.info("log success end");	
	    List<Tbl_User> result=_userRepository.findAll();
	    if(result.size()>0) {
	    	 Response response=new Response("0", "success",null, result);
			 return response;
	    }else {
	    	Response response=new Response("1", "error",null, "User not exists.");
			 return response;
	    }	
	    }catch (Exception e){		        	
			 Response response=new Response("2", "error",null, e.getMessage()); 
			 return response;  
	    }		
	}	
	@Override
	public Response getUserByEmail(String email,String password) {
		try {	
			
			
			Optional<Tbl_User> optionalUser = _userRepository.findByEmailAndPassword(email,password);
			if (optionalUser.isPresent()) {			   
				Tbl_User user = optionalUser.get();				
				 Response response=new Response("0", "success",null, user);
				 return response;  
			}else {
				Response response=new Response("1", "error",null, "Invalid email or password.");
				 return response;
			}	 
			}catch (Exception e){		        	
			 Response response=new Response("2", "error",null, e.getMessage()); 
			 return response;  
	    }			
	}

}
