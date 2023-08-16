package com.api.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.api.repositories.JwtAuthorizationRepository;

import com.api.responseModels.UserToken;
@Service
public class JwtAuthorizationService implements UserDetailsService {
	    private final JwtAuthorizationRepository _jwtAuth;
	    
	    @Autowired
	    public JwtAuthorizationService(JwtAuthorizationRepository jwtAuth) {
	        this._jwtAuth = jwtAuth;
	    }
	    
		public UserDetails loadUserByUsername(String email,String password) throws UsernameNotFoundException {
			UserToken user = _jwtAuth.findUserByEmail(email,password);
	        List<String> roles = new ArrayList<>();
	        roles.add("USER");
	        UserDetails userDetails =
	                 org.springframework.security.core.userdetails.User.builder()
			        .username(user.getEmail())
			        .password(user.getPassword())
			        .roles(roles.toArray(new String[0]))
			        .build();
	        return userDetails;
		}

		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			// TODO Auto-generated method stub
			return null;
		}
}
