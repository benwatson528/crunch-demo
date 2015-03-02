package com.example;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.junit.Before;
import org.junit.Test;

public class WordCountTest {
	Configuration conf;
	// An input file residing in /src/test/resources
	String sampleInput = Thread.currentThread().getContextClassLoader()
			.getResource("sample-text.txt").getPath();
	// The output is placed on my local file system
	String outputFolder = "/tmp/crunch-demo/output/";

	@Before
	public void setup() {
		FileUtils.deleteQuietly(new File(outputFolder));
		this.conf = new Configuration();
		// Runs any MapReduce jobs locally
		this.conf.set("mapreduce.framework.name", "local");
		// Uses the local file system instead of the HDFS
		this.conf.set("fs.defaultFS", "file:///");
	}

	@Test
	public void testWordCount() throws Exception {
		String[] args = new String[] { this.sampleInput, this.outputFolder };
		int result = WordCount.Main(args, this.conf);
		assertEquals(0, result);
	}
}
