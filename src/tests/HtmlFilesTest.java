package com.complet;

import static org.junit.Assert.*;

import java.io.File;
//import java.net.URI;
import java.nio.file.Files;
//import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class HtmlFilesTest {

	@Test
	public void testCheckPath() {
		
		String path="C:\\Users\\ΘΑΝΑΣΗΣ\\Desktop\\Web_Crawler";    
		//File file = new File(path);
		assertNotNull(Files.exists(Paths.get(path)));
		//assertTrue("path exists",file.exists());
	}


	@Test
	public void testDeleteDirectory() {
		File file = new File("C:\\Users\\ΘΑΝΑΣΗΣ\\Desktop\\Web_Crawler");
		assertFalse(HtmlFiles.deleteDirectory(file));
	}

}
