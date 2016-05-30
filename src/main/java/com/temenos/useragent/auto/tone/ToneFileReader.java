package com.temenos.useragent.auto.tone;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ToneFileReader {

	private static final String TONE_DIR_PATH = "TONE_DIR_PATH";
	private static final String TONE_EXT = ".tone";

	public List<ToneFile> read() {
		return read(System.getProperty(TONE_DIR_PATH));
	}

	public List<ToneFile> read(String toneDirPath) {
		File toneDir = new File(toneDirPath);
		if (!toneDir.isDirectory()) {
			throw new RuntimeException("Invalid tone file directory '"
					+ toneDirPath + "'");
		}

		File[] toneFiles = toneDir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String filename) {
				return filename.endsWith(TONE_EXT);
			}
		});

		return readAndBuildToneFiles(toneFiles);
	}

	private List<ToneFile> readAndBuildToneFiles(File[] toneFiles) {
		List<ToneFile> tones = new ArrayList<ToneFile>();
		for (final File file : toneFiles) {
			try {
				tones.add(new ToneFileParser().parse(new FileInputStream(file)));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return tones;
	}
}
