package com.complet;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.net.MalformedURLException;
import java.net.URL;


import com.complet.MediaCheck;

import java.lang.InterruptedException;

public class MediaCheckTest {


	@Rule

	public ExpectedException excepion = ExpectedException.none();


	@Test (timeout=10000)
	public void testMedia()  throws MalformedURLException, InterruptedException{

		URL url = new URL("https://tctechcrunch2011.files.wordpress.com/2013/05/house-of-cards-kevin-spacey.jpg");
		assertFalse(MediaCheck.media(url));


	}

}
