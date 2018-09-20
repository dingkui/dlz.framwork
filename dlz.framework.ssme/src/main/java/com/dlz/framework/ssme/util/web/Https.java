package com.dlz.framework.ssme.util.web;

import org.apache.commons.io.Charsets;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class Https {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}

	private static HttpHeaders headers = new HttpHeaders();
	private static MediaType mediaType = new MediaType("text", "html", Charsets.UTF_8);
	
	static {
		headers.setContentType(mediaType);
	}

	public static <T> ResponseEntity<T> toResponseEntity(T obj) {
		return new ResponseEntity<T>(obj, headers, HttpStatus.OK);
	}

}
