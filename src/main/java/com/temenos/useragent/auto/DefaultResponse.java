package com.temenos.useragent.auto;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class DefaultResponse implements Response {

	private String statusCode;
	private Map<String, String> headers = new LinkedHashMap<String, String>();
	private InputStream contentStream;

	private DefaultResponse(Builder builder) {
		this.statusCode = builder.statusCode;
		this.headers = builder.headers;
		this.contentStream = builder.contentStream;
	}

	public String statusCode() {
		return statusCode;
	}
	
	public Map<String, String> headers() {
		return headers;
	}

	public Set<String> headerNames() {
		return headers.keySet();
	}

	public InputStream content() {
		return contentStream;
	}

	public static class Builder {
		private String statusCode;
		private Map<String, String> headers = new LinkedHashMap<String, String>();
		private InputStream contentStream;

		public Builder statusCode(String code) {
			this.statusCode = code;
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

		public Response build() {
			return new DefaultResponse(this);
		}
	}
}
