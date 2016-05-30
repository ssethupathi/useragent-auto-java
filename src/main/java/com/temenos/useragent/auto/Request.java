package com.temenos.useragent.auto;

import java.io.InputStream;
import java.util.Set;

public interface Request {

	String url();
	
	String method();
	
	Set<String> headerNames();

	String headerValue(String name);

	InputStream content();
}
