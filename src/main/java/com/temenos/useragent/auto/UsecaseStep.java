package com.temenos.useragent.auto;

public class UsecaseStep {

	private int sequenceId;
	private Request request;
	private Response response;

	public UsecaseStep(int id, Request request, Response response) {
		this.sequenceId = id;
		this.request = request;
		this.response = response;
	}

	public int sequenceId() {
		return sequenceId;
	}

	public Request request() {
		return request;
	}

	public Response response() {
		return response;
	}
}
