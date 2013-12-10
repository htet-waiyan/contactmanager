package com.contactmanager.util.login;

public class LoginFailedException extends RuntimeException {
	
	protected int errorCode;
	protected String errorMsg;
	
	public LoginFailedException(){}
	
	public LoginFailedException(int errorCode, String errorMsg){
		this.errorCode=errorCode;
		this.errorMsg=errorMsg;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	
}
