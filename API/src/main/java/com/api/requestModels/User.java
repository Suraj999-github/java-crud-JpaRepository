package com.api.requestModels;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	    private String requestedId;
		private String email;
	    private String password;
	    private String firstName;
	    private String lastName;
	    @JsonCreator
	    public User(@JsonProperty("requestedId")String requestedId,@JsonProperty("email")String email, @JsonProperty("password")String password,@JsonProperty("firstName")String firstName,@JsonProperty("lastName")String lastName) {
	    	this.requestedId = requestedId;
	    	this.email = email;
	        this.password = password;
	        this.firstName=firstName;
	        this.lastName=lastName;
	    }
	    public String getrequestedId() {
	        return requestedId;
	    }

	    public void setrequestedId(String requestedId) {
	        this.requestedId = requestedId;
	    }
	    
	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public String getFirstName() {
	        return firstName;
	    }

	    public void setFirstName(String firstName) {
	        this.firstName = firstName;
	    }

	    public String getLastName() {
	        return lastName;
	    }

	    public void setLastName(String lastName) {
	        this.lastName = lastName;
	    }
	}
