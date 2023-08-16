package com.api.requestModels;

public class GetToken {
	 private String email;
	    private String password;

	    public GetToken(String email, String password) {
	        this.email = email;
	        this.password = password;
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
}
