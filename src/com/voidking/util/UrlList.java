package com.voidking.util;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;  
  
/** 
 * Example program to list links from a URL. 
 */  
public class UrlList {
	public Document doc = null;
	public UrlList(String url) {
		// TODO Auto-generated constructor stub
		try {
			doc = Jsoup.connect(url).timeout(5000).userAgent("Mozilla").get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Element> getLinkList(){
		Elements links = doc.select("link[href]");
		ArrayList<Element> resultList = new ArrayList<Element>();
		for(Element link : links){
			resultList.add(link);
		}
		return resultList;
	}
	
	public ArrayList<Element> getCssList(){
		Elements links = doc.select("link[href]");
		ArrayList<Element> resultList = new ArrayList<Element>();
		for(Element link : links){
			if("stylesheet".equals(link.attr("rel"))){
				resultList.add(link);				
			}
		}
		return resultList;
	}
	
	public ArrayList<Element> getAList(){
		Elements links = doc.select("a[href]");
		ArrayList<Element> resultList = new ArrayList<Element>();
		for(Element link : links){
			resultList.add(link);
		}
		return resultList;
	}
	
	public ArrayList<Element> getImgList(){
		Elements links = doc.select("img[src]");
		ArrayList<Element> resultList = new ArrayList<Element>();
		for(Element link : links){
			resultList.add(link);
		}
		return resultList;
	}
	
	public ArrayList<Element> getJsList(){
		Elements links = doc.select("script[src]");
		ArrayList<Element> resultList = new ArrayList<Element>();
		for(Element link : links){
			resultList.add(link);
		}
		return resultList;
	}
}  
