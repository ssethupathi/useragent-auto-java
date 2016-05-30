package com.temenos.useragent.auto;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Usecase {

	private String baseUrl;
	private Map<String, String> headers = new LinkedHashMap<String, String>();
	private String name;
	private List<UsecaseStep> steps;

	public Usecase(String name, List<UsecaseStep> steps) {
		this.name = name;
		this.steps = steps;
	}

	public String baseUrl() {
		return baseUrl;
	}

	public Map<String, String> headers() {
		return headers;
	}

	public String getName() {
		return name;
	}

	public List<UsecaseStep> getSteps() {
		return steps;
	}
}
