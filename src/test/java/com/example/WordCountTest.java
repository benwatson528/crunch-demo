package com.example;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.junit.Before;
import org.junit.Test;

public class WordCountTest {
	Configuration conf;
	String sampleInput = Thread.currentThread().getContextClassLoader()
			.getResource("sample-text.txt").getPath();
	String outputFolder = "/tmp/crunch-demo/output/";

	@Test
	public void testWordCount() throws Exception {
		String[] args = new String[] { this.sampleInput, this.outputFolder };
		int result = WordCount.Main(args, this.conf);
		assertEquals(0, result);
	}

	@Before
	public void setup() {
		FileUtils.deleteQuietly(new File(outputFolder));
		this.conf = new Configuration();
		this.conf.set("mapreduce.framework.name", "local");
		this.conf.set("fs.defaultFS", "file:///");
	}
}
