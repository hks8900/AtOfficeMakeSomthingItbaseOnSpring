package com.example.demo.Helper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import kong.unirest.Unirest;

@Service
public class HttpRequester {

	public static Document makeDoc(String url) {
		String source = Unirest.get(url).asString().getBody();
		Document doc = Jsoup.parse(source);
		return doc;
	}

}