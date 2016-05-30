package com.temenos.useragent.auto.internal;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.temenos.useragent.auto.tone.ToneFile;
import com.temenos.useragent.auto.tone.ToneFileParser;

public class ToneFileParserTest {

	@Test
	public void testParseSingleTone() {
		ToneFile tone = new ToneFileParser().parse(ToneFileParserTest.class
				.getResourceAsStream("/single/req_res_full.tone"));
		assertEquals(
				"20160519-153810-00020-POST-verAaArrangementActivity_AaNews('AAACT15203KJQ1D4W2')/aapopulate",
				tone.getKey());
		assertEquals("POST", tone.getMethod());
	}
}
