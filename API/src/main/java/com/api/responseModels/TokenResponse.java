package com.api.responseModels;

public class TokenResponse {
	   private String email;
	    private String token;

	    public TokenResponse(String email, String token) {
	        this.email = email;
	        this.token = token;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getToken() {
	        return token;
	    }

	    public void setToken(String token) {
	        this.token = token;
	    }
}
