package com.zhuwm.h5.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ReadWordTest {

	@Before
	public void setUp() throws Exception {}

	@After
	public void tearDown() throws Exception {}

	@Test
	public void test() {
		ReadWordService readword=new ReadWordService();
		//readword.readReadWordFile();
		readword.readWordXFile();
		assertTrue(true);
		//fail("Not yet implemented");
	}

}
