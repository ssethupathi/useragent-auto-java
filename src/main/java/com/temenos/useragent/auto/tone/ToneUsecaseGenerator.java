package com.temenos.useragent.auto.tone;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.temenos.useragent.auto.DefaultRequest;
import com.temenos.useragent.auto.DefaultResponse;
import com.temenos.useragent.auto.Request;
import com.temenos.useragent.auto.Response;
import com.temenos.useragent.auto.Usecase;
import com.temenos.useragent.auto.UsecaseGenerator;
import com.temenos.useragent.auto.UsecaseStep;

public class ToneUsecaseGenerator implements UsecaseGenerator {

	private List<ToneFile> toneFiles;
	private String id;

	public ToneUsecaseGenerator(String id, List<ToneFile> toneFiles) {
		this.toneFiles = toneFiles;
		this.id = id;
	}

	public Usecase generate() {
		List<UsecaseStep> steps = new ArrayList<UsecaseStep>();
		for (ToneFile toneFile : toneFiles) {
			steps.add(new UsecaseStep(extractSequenceId(toneFile),
					buildRequest(toneFile), buildResponse(toneFile)));
		}
		return new Usecase(id, steps);
	}

	private Request buildRequest(ToneFile toneFile) {
		DefaultRequest.Builder requestBuilder = new DefaultRequest.Builder();
		requestBuilder.url(toneFile.getUrl());
		requestBuilder.method(toneFile.getMethod());
		Map<String, String> reqAttrs = toneFile.getRequestAttributes();
		for (String reqAttr : reqAttrs.keySet()) {
			requestBuilder.header(reqAttr, reqAttrs.get(reqAttr));
		}
		return requestBuilder.build();
	}

	private Response buildResponse(ToneFile toneFile) {
		DefaultResponse.Builder responseBuilder = new DefaultResponse.Builder();
		responseBuilder.statusCode(toneFile.getStatusCode());
		Map<String, String> resAttrs = toneFile.getResponseAttributes();
		for (String resAttr : resAttrs.keySet()) {
			responseBuilder.header(resAttr, resAttrs.get(resAttr));
		}
		return responseBuilder.build();
	}

	private int extractSequenceId(ToneFile toneFile) {
		String key = toneFile.getKey();
		String[] keyParts = key.split("-");
		return Integer.parseInt(keyParts[2]);
	}
}
