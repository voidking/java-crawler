package com.voidking.crawler1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

public class GetBaiduPage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			// 网络资源
			URL url = new URL("http://www.baidu.com");
			// 获取网络流
//			InputStream is = url.openStream();
//			byte[] flush = new byte[1024];
//			int len = 0;
//			while(-1 != (len = is.read(flush))){
//				System.out.println(new String(flush,0,len));
//			}
//			is.close();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("baidu.html"),"utf-8"));
			String msg = null;
			while((msg = br.readLine()) != null){
				System.out.println(msg);
				bw.append(msg);
				bw.newLine();
			}
			bw.flush();
			bw.close();
			br.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
