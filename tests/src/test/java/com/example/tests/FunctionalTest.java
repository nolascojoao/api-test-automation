package com.example.tests;

import org.junit.jupiter.api.BeforeAll;

import io.restassured.RestAssured;

public class FunctionalTest {

	private static final int DEFAULT_PORT = 8080;
	private static final String DEFAULT_BASE_PATH = "/api";
	private static final String DEFAULT_BASE_URI = "http://localhost";

	@BeforeAll
	public static void setup() {
		String port = System.getProperty("server.port");
		String path = System.getProperty("server.base");
		String host = System.getProperty("server.host");

		RestAssured.port = (port == null) ? DEFAULT_PORT : Integer.valueOf(port);
		RestAssured.basePath = (path == null) ? DEFAULT_BASE_PATH : path;
		RestAssured.baseURI = (host == null) ? DEFAULT_BASE_URI : host;
	}

}
