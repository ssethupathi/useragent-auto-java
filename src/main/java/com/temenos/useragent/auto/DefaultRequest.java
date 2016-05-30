package com.temenos.useragent.auto;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class DefaultRequest implements Request {

	private String url = "";
	private String method = "";
	private Map<String, String> headers = new LinkedHashMap<String, String>();
	private InputStream contentStream;
	
	private DefaultRequest(Builder builder) {
		this.url = builder.url;
		this.method = builder.method;
		this.headers = builder.headers;
		this.contentStream = builder.contentStream;
	}

	public String url() {
		return url;
	}
	
	public String method() {
		return method;
	}
	
	public String headerValue(String name) {
		return headers.containsKey(name) ? headers.get(name) : "";
	}

	public Set<String> headerNames() {
		return headers.keySet();
	}

	public InputStream content() {
		return contentStream;
	}

	public static class Builder {
		private String url = "";
		private String method = "";
		private Map<String, String> headers = new LinkedHashMap<String, String>();
		private InputStream contentStream;
		
	public Builder url(String url) {
		this.url = url;
		return this;
	}
	
	public Builder method(String method) {
		this.method = method;
		return this;
	}
	
	public Builder header(String name, String value) {
		headers.put(name, value);
		return this;
	}

	public Builder content(InputStream contentStream) {
		this.contentStream = contentStream;
		return this;
	}
	
	public Request build() {
		return new DefaultRequest(this);
	}
	
	}
}
