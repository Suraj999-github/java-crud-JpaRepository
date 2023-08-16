package com.api.repositories;

import org.springframework.stereotype.Repository;

import com.api.responseModels.UserToken;
@Repository
public class JwtAuthorizationRepository {
	 public UserToken findUserByEmail(String email,String password){
		 UserToken user = new UserToken(email,password);
	        user.setFirstName("FirstName");
	        user.setLastName("LastName");
	        return user;
	    }
}
