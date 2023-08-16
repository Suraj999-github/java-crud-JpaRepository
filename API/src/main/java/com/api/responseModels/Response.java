package com.api.responseModels;

public class Response {
	public String errorCode;
	public String msg;
	public String id;
	public Object result;
	
	public Response(String errorCode ,String msg,String id ,Object result) {
		this.errorCode=errorCode;
		this.msg=msg;
		this.id=id;
		this.result=result;
	}
}
