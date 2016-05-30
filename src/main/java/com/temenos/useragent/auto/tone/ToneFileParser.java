package com.temenos.useragent.auto.tone;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class ToneFileParser {

	private static final String KEY = "KEY";
	private static final String ATTR = "ATTR";
	private static final String START_ATTR = "START_ATTR";
	private static final String END_ATTR = "END_ATTR";
	private static final String REQ_HEADER = "REQUEST_HEADER_";
	private static final String RES_HEADER = "RESPONSE_HEADER_";
	private static final String REQ_URL = "REQUEST_URL";
	private static final String REQ_METHOD = "REQUEST_METHOD";
	private static final String REQ_MSG = "REQUEST_MESSAGE";
	private static final String RES_MSG = "RESPONSE_MESSAGE";
	private static final String RES_STATUS_CODE = "RESPONSE_STATUS_CODE";

	private ToneFile.Builder toneBuilder;
	private Map<String, String> reqHeaders;
	private Map<String, String> resHeaders;

	public ToneFile parse(InputStream toneContentStream) {
		initParser();
		Scanner scanner = new Scanner(toneContentStream);
		while (scanner.hasNextLine()) {
			String firstWord = scanner.next();
			if (firstWord.equals(KEY)) {
				toneBuilder.key(scanner.next());
			} else if (ATTR.equals(firstWord)) {
				parseAttribute(scanner);
			} else if (START_ATTR.equals(firstWord)) {
				parseMessage(scanner);
			}
		}

		return toneBuilder.requestHeaders(reqHeaders)
				.responseHeaders(resHeaders).build();
	}

	private void parseAttribute(Scanner scanner) {
		String attrName = scanner.next();
		if (attrName.startsWith(REQ_HEADER)) {
			reqHeaders.put(attrName.replaceFirst(REQ_HEADER, ""),
					scanner.next());
		} else if (attrName.startsWith(RES_HEADER)) {
			resHeaders.put(attrName.replaceFirst(RES_HEADER, ""),
					scanner.next());
		} else if (attrName.equals(REQ_URL)) {
			toneBuilder.url(scanner.next());
		} else if (attrName.equals(REQ_METHOD)) {
			toneBuilder.method(scanner.next());
		} else if (attrName.equals(RES_STATUS_CODE)) {
			toneBuilder.statusCode(scanner.next());
		} else {
			// TODO log?
		}
	}

	private void parseMessage(Scanner scanner) {
		String attrName = scanner.next();
		scanner.nextLine();
		if (attrName.startsWith(REQ_MSG)) {
			toneBuilder.requestMessage(extractMessage(scanner));
		} else if (attrName.startsWith(RES_MSG)) {
			toneBuilder.responseMessage(extractMessage(scanner));
		} else {
			// TODO log?
		}
	}

	private String extractMessage(Scanner scanner) {
		StringBuffer buffer = new StringBuffer();
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (line.startsWith(END_ATTR)) {
				break;
			}
			buffer.append(line);
		}
		return buffer.toString();
	}

	private void initParser() {
		toneBuilder = new ToneFile.Builder();
		reqHeaders = new LinkedHashMap<String, String>();
		resHeaders = new LinkedHashMap<String, String>();
	}
}
