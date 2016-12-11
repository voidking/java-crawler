package com.voidking.crawler3;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.voidking.util.Page;
import com.voidking.util.Regex;
import com.voidking.util.SaveFile;
import com.voidking.util.UrlList;

public class MainFrame extends JFrame{
	
	public static void main(String[] args){
		MainFrame frame = new MainFrame();
		
	}
	
	public MainFrame(){
		// 创建监听对象
		Listener myListener = new Listener();
		
		// Frame设置
		this.setTitle("网页爬虫");
		this.setSize(500,600);
		this.setLocationRelativeTo(null);
		//this.setLocation(100,100);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// TabbedPane设置
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		// SplitPane设置
		JSplitPane vSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		vSplitPane.setDividerLocation(120);
		vSplitPane.setDividerSize(8);
		
		// Panel设置
		JPanel topPanel = new JPanel();
		JPanel middlePanel = new JPanel();
		vSplitPane.setTopComponent(topPanel);
		vSplitPane.setBottomComponent(middlePanel);
		
		// url标签
		JLabel label = new JLabel("页面url：");
		label.setFont(new Font("", 1, 15));
		topPanel.add(label);
		
		// url输入框
		JTextField text = new JTextField(25);
		topPanel.add(text);
		
		// url按钮
		JButton urlBtn = new JButton("确定");
		urlBtn.setFont(new Font("", 1, 15));
		topPanel.add(urlBtn);
		//button.setActionCommand("clickUrlBtn");
		//button.addActionListener(myListener);
		
		// 获取链接按钮
		JButton getABtn = new JButton("获取链接");
		getABtn.setFont(new Font("", 1, 15));
		topPanel.add(getABtn);
		
		// 获取图片按钮
		JButton getImgBtn = new JButton("获取图片");
		getImgBtn.setFont(new Font("", 1, 15));
		topPanel.add(getImgBtn);

		// 获取css按钮
		JButton getCssBtn = new JButton("获取css");
		getCssBtn.setFont(new Font("", 1, 15));
		topPanel.add(getCssBtn);
		
		// 获取js按钮
		JButton getJSBtn = new JButton("获取js");
		getJSBtn.setFont(new Font("", 1, 15));
		topPanel.add(getJSBtn);
		
		// 文件路径标签
		JLabel fileLabel = new JLabel("页面保存路径：");
		fileLabel.setFont(new Font("", 1, 15));
		topPanel.add(fileLabel);
		
		// 文件路径输入框
		JTextField fileText = new JTextField(20);
		//fileText.setEditable(false);
		topPanel.add(fileText);
		
		// 选择文件按钮
		JButton fileBtn = new JButton("选择");
		fileBtn.setFont(new Font("", 1, 15));
		topPanel.add(fileBtn);
		
		
		// 文本区
		JTextArea infoArea = new JTextArea(25,40);
		infoArea.setLineWrap(true);
		
		// scroll
		JScrollPane scroll = new JScrollPane(infoArea);
		middlePanel.add(scroll);
		
		// 添加面板到tab
		tabbedPane.addTab("页面抓取", vSplitPane);
		
		// 第二个tab页面
		JPanel introPanel = new JPanel();
		String intro = "1、首先输入页面url（例：http://mp.weixin.qq.com/wiki/home/）\r\n"+
				"2、点击“确定”获取页面html，显示到界面\r\n"+
				"3、点击“获取链接”，获取页面中的url链接\r\n"+
				"4、点击“获取图片”，获取页面中图片链接\r\n"+
				"5、点击“获取css”，获取页面中css链接\r\n"+
				"6、点击“获取js”，获取页面中的js链接\r\n"+
				"4、选择“路径”，保存html以及相关css和图片到指定路径";
		JTextArea introArea = new JTextArea(intro);
		introArea.setFont(new Font("", 1, 15));
		introArea.setEditable(false);
		
		introPanel.add(introArea);
		tabbedPane.addTab("使用说明", introPanel);
		
		
		/*-------事件处理-------*/
		// tab切换
		tabbedPane.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				int selectedIndex = tabbedPane.getSelectedIndex();
				String title = tabbedPane.getTitleAt(selectedIndex);
				System.out.println(title);
			}
		});
		
		// url按钮
		urlBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String url = text.getText();
				boolean flag = Regex.checkUrl(url);
				if(!flag){
					infoArea.setText("请输入正确的网址！");
				}else{
					Page page = new Page(url);
					Document html = page.getDoc();
					String htmlStr = page.getHtml();
					//System.out.println(html);
					infoArea.setText(htmlStr);
				}
			}
		});
		
		getABtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String url = text.getText();
				boolean flag = Regex.checkUrl(url);
				if(!flag){
					infoArea.setText("请输入正确的网址！");
				}else{
					UrlList urlList = new UrlList(url);
					ArrayList<Element> list = urlList.getAList();
					String linkStr = "";
					for(Element link : list){
						linkStr = linkStr + link.attr("abs:href") + "\n";
					}
					infoArea.setText(linkStr);
				}	
			}
		});
		
		getImgBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String url = text.getText();
				boolean flag = Regex.checkUrl(url);
				if(!flag){
					infoArea.setText("请输入正确的网址！");
				}else{
					UrlList urlList = new UrlList(url);
					ArrayList<Element> list = urlList.getImgList();
					String linkStr = "";
					for(Element link : list){
						linkStr = linkStr + link.attr("abs:src") + "\n";
					}
					infoArea.setText(linkStr);
				}
			}
		});
		
		getCssBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String url = text.getText();
				boolean flag = Regex.checkUrl(url);
				if(!flag){
					infoArea.setText("请输入正确的网址！");
				}else{
					UrlList urlList = new UrlList(url);
					ArrayList<Element> list = urlList.getCssList();
					String linkStr = "";
					for(Element link : list){
						linkStr = linkStr + link.attr("abs:href") + "\n";
					}
					infoArea.setText(linkStr);
				}
			}
		});
		
		getJSBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String url = text.getText();
				boolean flag = Regex.checkUrl(url);
				if(!flag){
					infoArea.setText("请输入正确的网址！");
				}else{
					UrlList urlList = new UrlList(url);
					ArrayList<Element> list = urlList.getJsList();
					String linkStr = "";
					for(Element link : list){
						linkStr = linkStr + link.attr("abs:src") + "\n";
					}
					infoArea.setText(linkStr);
				}
			}
		});
		
		fileBtn.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// 文件路径选择对话框
				JFileChooser jFileChooser = new JFileChooser();
				jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				jFileChooser.showOpenDialog(MainFrame.this);
				File file = jFileChooser.getSelectedFile();
				String path = file.getPath();
				//System.out.println(path);
				fileText.setText(path);
				
				String url = text.getText();
				boolean flag = Regex.checkUrl(url);
				if(!flag){
					infoArea.setText("请输入正确的网址！");
				}else{
					Page page = new Page(url);
					String htmlStr = page.getHtml();
					SaveFile saveFile = new SaveFile();
					saveFile.saveFile(path, htmlStr);
					UrlList urlList = new UrlList(url);
					ArrayList<Element> cssList = urlList.getCssList();
					saveFile.saveCss(path, cssList);
					ArrayList<Element> imgList = urlList.getImgList();
					saveFile.saveImg(path, imgList);
					ArrayList<Element> jsList = urlList.getJsList();
					saveFile.saveJs(path, jsList);
					JOptionPane.showMessageDialog(getContentPane(),"保存成功，请到指定路径下查看","保存结果",JOptionPane.INFORMATION_MESSAGE); 
				}
				
			}
		});
		
	}
}
