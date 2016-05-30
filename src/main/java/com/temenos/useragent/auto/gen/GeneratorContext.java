package com.temenos.useragent.auto.gen;

import java.util.Arrays;
import java.util.List;

public class GeneratorContext {

	public static final String javaPackage = "foo";
	public static final String[] javaImports = { "static org.junit.Assert.*",
			"org.junit.Test",
			"com.temenos.useragent.generic.DefaultInteractionSession",
			"com.temenos.useragent.generic.InteractionSession" };
	public static final String javaClass = "Test1";
	
	public String javaPackage() {
		return javaPackage;
	}
	
	public List<String> javaImports() {
		return Arrays.asList(javaImports);
	}
	
	public String javaClass() {
		return javaClass;
	}
}