package com.voidking.util;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class SaveFile {

	public void saveFile(String path, String htmlStr) {

		File file = new File(path + "\\爬取的文件\\index.html");
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}

		// 字节输出流
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			OutputStreamWriter writer = new OutputStreamWriter(fos, "UTF-8");
			writer.write(htmlStr);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void saveCss(String path, ArrayList<Element> cssList) {
		for (int i = 0; i < cssList.size(); i++) {
			if (!cssList.get(i).attr("abs:href").equals(cssList.get(i).attr("href"))) {
				Source source = new Source();
				String css = source.getString(cssList.get(i).attr("abs:href"));

				String paths[] = cssList.get(i).attr("href").split("\\?");
				File file = new File(path + "\\爬取的文件\\" + paths[0]);
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}

				// 字节输出流
				FileOutputStream fos = null;
				try {
					fos = new FileOutputStream(file);
					OutputStreamWriter writer = new OutputStreamWriter(fos, "UTF-8");
					writer.write(css);
					writer.close();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						fos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

	}

	public void saveImg(String path, ArrayList<Element> imgList) {
		for (int i = 0; i < imgList.size(); i++) {
			if (!imgList.get(i).attr("abs:src").equals(imgList.get(i).attr("src"))) {
				String paths[] = imgList.get(i).attr("src").split("\\?");
				File file = new File(path + "\\爬取的文件\\" + paths[0]);
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}
				try {
	                URL uri = new URL(imgList.get(i).attr("abs:src"));  
	                InputStream in = uri.openStream();  
	                FileOutputStream fo = new FileOutputStream(file);  
	                byte[] buf = new byte[1024];  
	                int length = 0;  
	                while ((length = in.read(buf, 0, buf.length)) != -1) {  
	                    fo.write(buf, 0, length);  
	                }  
	                in.close();  
	                fo.close();  
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            }  
				
			}
		}

	}
	
	public void saveJs(String path, ArrayList<Element> jsList){
		for (int i = 0; i < jsList.size(); i++) {
			if (!jsList.get(i).attr("abs:src").equals(jsList.get(i).attr("src"))) {
				Source source = new Source();
				String css = source.getString(jsList.get(i).attr("abs:src"));

				String paths[] = jsList.get(i).attr("src").split("\\?");
				File file = new File(path + "\\爬取的文件\\" + paths[0]);
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}

				// 字节输出流
				FileOutputStream fos = null;
				try {
					fos = new FileOutputStream(file);
					OutputStreamWriter writer = new OutputStreamWriter(fos, "UTF-8");
					writer.write(css);
					writer.close();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						fos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}
