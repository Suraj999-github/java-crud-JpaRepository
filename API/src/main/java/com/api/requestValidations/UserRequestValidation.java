package com.api.requestValidations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.api.requestModels.User;
import com.api.responseModels.Response;
import com.api.responseModels.ValidationResponseComposer;

public class UserRequestValidation {

	public Response ValidateCreateUserRequest(User req) {
		 ValidationResponseComposer _responseComposer =new ValidationResponseComposer();
		Response res= new Response("0", "valid", null, null);
		if(req.getEmail().isBlank() || req.getEmail().isEmpty()) {
			return _responseComposer.Result("1", "Email is required.", null, null);
		}
		if(req.getEmail().length()>100 || req.getEmail().length()<8) {
			return _responseComposer.Result("1", "Maximum length of email is 100 and Min length is 8.", null, null);
		}
		if(isValidEmail(req.getEmail())==false) {
			return _responseComposer.Result("1", "Invalid Email.", null, null);
		}
		
		if(req.getFirstName().isBlank() || req.getFirstName().isEmpty()) {
			return _responseComposer.Result("1", "First Name is required.", null, null);
		}
		if(req.getLastName().isBlank() || req.getLastName().isEmpty()) {
			return _responseComposer.Result("1", "Last Name is required.", null, null);
		}
		if(req.getPassword().isBlank() || req.getPassword().isEmpty()) {
			return _responseComposer.Result("1", "Password is required.", null, null);
		}
		return res;
		
	}
	public Response ValidateUpdateUserRequest(User req) {
		 ValidationResponseComposer _responseComposer =new ValidationResponseComposer();
		Response res= new Response("0", "valid", null, null);
		if(req.getrequestedId().isBlank() || req.getrequestedId().isEmpty()) {
			return _responseComposer.Result("1", "Requested id  is required.", null, null);
		}
		if(Integer.parseInt(req.getrequestedId())< 1) {
			return _responseComposer.Result("1", "Requested id  is required.", null, null);
		}		
		
		if(req.getEmail().isBlank() || req.getEmail().isEmpty()) {
			return _responseComposer.Result("1", "Email is required.", null, null);
		}
		if(req.getEmail().length()>100 || req.getEmail().length()<8) {
			return _responseComposer.Result("1", "Maximum length of email is 100 and Min length is 8.", null, null);
		}
		if(isValidEmail(req.getEmail())==false) {
			return _responseComposer.Result("1", "Invalid Email.", null, null);
		}
		
		if(req.getFirstName().isBlank() || req.getFirstName().isEmpty()) {
			return _responseComposer.Result("1", "First Name is required.", null, null);
		}
		if(req.getLastName().isBlank() || req.getLastName().isEmpty()) {
			return _responseComposer.Result("1", "Last Name is required.", null, null);
		}
		if(req.getPassword().isBlank() || req.getPassword().isEmpty()) {
			return _responseComposer.Result("1", "Password is required.", null, null);
		}
		return res;
		
	}
	 public static boolean isValidEmail(String email) {
	        // Define a regular expression pattern for valid email addresses
	        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
	        Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(email);
	        return matcher.matches();
	    }
}
