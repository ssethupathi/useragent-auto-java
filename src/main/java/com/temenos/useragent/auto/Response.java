package com.temenos.useragent.auto;

import java.io.InputStream;
import java.util.Map;

public interface Response {
	
	String statusCode();

	Map<String, String> headers();
	
	InputStream content();
}
