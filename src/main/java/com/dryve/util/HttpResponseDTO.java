package com.dryve.util;

public class HttpResponseDTO {

	int httpCode;
	
	String response;
	
	

	public HttpResponseDTO(int httpCode, String response) {
		super();
		this.httpCode = httpCode;
		this.response = response;
	}

	public int getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "HttpResponseDTO [httpCode=" + httpCode + ", response=" + response + "]";
	}
	
	
	
}