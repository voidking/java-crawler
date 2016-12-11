package com.voidking.util;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Page {
	Document doc = null;
	public Page(String url) {
		// TODO Auto-generated constructor stub
		try {
			doc = Jsoup.connect(url).timeout(5000).userAgent("Mozilla").get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getHtml(){
		return doc.html();
	}
	
	public Document getDoc(){
		return doc;
	}
}
