package com.cyberdesign.rewards.controller;

import static org.junit.Assert.*;

import java.util.UUID;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestProductController {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddProduct() {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-mm-dd");
		DateTime start = formatter.parseDateTime("2015-01-01");
		DateTime end = formatter.parseDateTime("9999-01-01");
		System.out.println("uuid:"+UUID.randomUUID().toString());
		assertNotEquals(start, end);
/*		Product p = new Product("Test Product1", 5, start, end);
		ProductController prodController = new ProductCont*/
	}
}
