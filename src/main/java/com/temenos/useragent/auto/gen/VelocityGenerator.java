package com.temenos.useragent.auto.gen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import com.temenos.useragent.auto.Usecase;

public class VelocityGenerator implements TestCodeGenerator {

	private String targetPath;
	private Usecase usecase;

	public VelocityGenerator(Usecase usecase, String targetPath) {
		this.usecase = usecase;
		this.targetPath = targetPath;
	}

	public void generate() {
		VelocityContext context = buildContextBase();
		context.put("usecase", usecase);
		StringWriter writer = new StringWriter();
		getTemplate().merge(context, writer);
		String sourceCode = writer.toString();
		if (targetPath != null && !targetPath.isEmpty()) {
			try {
				IOUtils.write(sourceCode, new FileOutputStream(new File(
						targetPath + "/" + usecase.getName() + ".java")));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		} else {
			System.out.println(sourceCode);
		}
	}

	private Template getTemplate() {
		VelocityEngine engine = new VelocityEngine();
		engine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		engine.setProperty("classpath.resource.loader.class",
				ClasspathResourceLoader.class.getName());
		engine.init();
		return engine.getTemplate("templates/TestCase.vm");
	}

	private VelocityContext buildContextBase() {
		GeneratorContext generatorContext = new GeneratorContext();
		VelocityContext velocityContext = new VelocityContext();
		velocityContext.put("language", this);
		velocityContext.put("JAVA_PACKAGE", generatorContext.javaPackage());
		velocityContext.put("JAVA_IMPORTS", generatorContext.javaImports());
		velocityContext.put("JAVA_CLASS", generatorContext.javaClass());
		return velocityContext;
	}
}
