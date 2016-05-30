package com.temenos.useragent.auto.internal;

import java.util.List;

import static org.hamcrest.junit.MatcherAssert.*;
import static org.hamcrest.core.IsEqual.*;
import org.junit.Test;

import com.temenos.useragent.auto.UsecaseGenerator;
import com.temenos.useragent.auto.gen.TestCodeGenerator;
import com.temenos.useragent.auto.gen.VelocityGenerator;
import com.temenos.useragent.auto.tone.ToneFile;
import com.temenos.useragent.auto.tone.ToneFileReader;
import com.temenos.useragent.auto.tone.ToneUsecaseGenerator;

public class TestAll {

	@Test
	public void testGenerationWithMultiTone() {
		String toneDirPath = TestAll.class.getResource(
				"/multi/PersonEntityCreationITCase").getPath();
		List<ToneFile> toneFiles = new ToneFileReader().read(toneDirPath);
		UsecaseGenerator usecaseGenerator = new ToneUsecaseGenerator(
				"PersonEntityCreationITCase", toneFiles);
		TestCodeGenerator codeGenerator = new VelocityGenerator(
				usecaseGenerator.generate(),
				"D:/source/git/open/uagent/useragent-auto-java/target/generated-sources");
		codeGenerator.generate();
		assertThat("foo", equalTo("foo"));
	}
}
