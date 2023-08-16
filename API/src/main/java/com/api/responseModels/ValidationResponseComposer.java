package com.api.responseModels;

public class ValidationResponseComposer {
	 public Response Result(String errorCode,String msg,String id,Object response) {
    	 Response res= new Response(errorCode, msg, id, response);
    	 return res;
	}
}
