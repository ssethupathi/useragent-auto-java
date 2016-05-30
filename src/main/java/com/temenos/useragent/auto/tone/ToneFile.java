package com.temenos.useragent.auto.tone;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;

public class ToneFile {

	private String key;
	private String url;
	private String method;
	private String statusCode;
	private Map<String, String> reqAttributes = new LinkedHashMap<String, String>();
	private Map<String, String> resAttributes = new LinkedHashMap<String, String>();
	private String reqMessage;
	private String resMessage;

	public ToneFile(Builder builder) {
		this.statusCode = builder.statusCode;
		this.key = builder.key;
		this.url = builder.url;
		this.method = builder.method;
		this.reqAttributes = builder.reqHeaders;
		this.resAttributes = builder.resHeaders;
		this.reqMessage = builder.reqMessage;
		this.resMessage = builder.resMessage;
	}

	public String getStatusCode() {
		return statusCode;
	}
	
	public String getKey() {
		return key;
	}

	public String getUrl() {
		return url;
	}
	
	public String getMethod() {
		return method;
	}
	
	public Map<String, String> getRequestAttributes() {
		return reqAttributes;
	}

	public Map<String, String> getResponseAttributes() {
		return resAttributes;
	}

	public InputStream getRequestMessage() {
		return IOUtils.toInputStream(reqMessage);
	}

	public InputStream getResponseMessage() {
		return IOUtils.toInputStream(resMessage);
	}
	
	public static class Builder {
		private String key;
		private String url;
		private String method;
		private String statusCode;
		private Map<String, String> reqHeaders = new LinkedHashMap<String, String>();
		private Map<String, String> resHeaders = new LinkedHashMap<String, String>();
		private String reqMessage;
		private String resMessage;
		
		public Builder key(String key) {
			this.key = key;
			return this;
		}
		
		public Builder url(String url) {
			this.url = url;
			return this;
		}
		
		public Builder method(String method) {
			this.method = method;
			return this;
		}
		
		public Builder statusCode(String statusCode) {
			this.statusCode = statusCode;
			return this;
		}
		
		public Builder requestHeaders(Map<String, String> requestHeaders) {
			this.reqHeaders = requestHeaders;
			return this;
		}
		
		public Builder responseHeaders(Map<String, String> responseHeaders) {
			this.resHeaders = responseHeaders;
			return this;
		}
		
		public Builder requestMessage(String requestMessage) {
			this.reqMessage = requestMessage;
			return this;
		}
		
		public Builder responseMessage(String responseMessage) {
			this.resMessage = responseMessage;
			return this;
		}
		
		public ToneFile build() {
			return new ToneFile(this);
		}
	}
}


