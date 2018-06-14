package top.zerotop.wechat.manager;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

import top.zerotop.domain.menu.Button;
import top.zerotop.domain.menu.Menu;
import top.zerotop.wechat.constrant.ButtonTypeConstrant;
import top.zerotop.wechat.constrant.URLConstrant;
import top.zerotop.wechat.util.SendUtil;

public class MenuManager {
	
	private static Gson gson = new Gson();
	
	public static void main(String args[]){
		
		MenuManager menumanager = new MenuManager();
		
		String accessToken = "10_mBemYZ50z5JMbZ3PXUq493z2cgtiGOPcGrIRHpt9_8cGRFnmLC409j0yXNadM4YtaO0s2iPQD4CaOcOmwiX9_EiKlGdPh_GMHGWQ037AWj2EXL1JsTOGMYUbDhxhkB6kn1ZoLrt2QiYEc3axQLHjAEAZUQ";
		
//		menumanager.getMenu("ACCESS_TOKEN");
		
		menumanager.tunnelMenu(accessToken);
	}
	
	public String getMenu(String access_token){
		
		String url = URLConstrant.URL_MENU_GET + access_token;
		
		System.out.println(SendUtil.sendGet(url, null));
		
		return url;
	}
	
	public String delete(String access_token){
		
		String url = URLConstrant.URL_MENU_GET + access_token;
		
		System.out.println(SendUtil.sendGet(url, null));
		
		return url;
	}
	
	public String tunnelMenu(String accessToken){
		
		Button b1 = new Button();
		Button b2 = new Button();
		Button b3 = new Button();
		
		Button sb11 = new Button();
		Button sb12 = new Button();
		Button sb21 = new Button();
		Button sb22 = new Button();
		
		sb11.setKey("catalog");
		sb11.setName("所有章节");
		sb11.setType(ButtonTypeConstrant.TYPE_VIEW);
		sb11.setUrl("http://www.zerotop.top/wechat/tunnel/catalog?isImportant=0");
		sb12.setKey("catalogimport");
		sb12.setName("重点章节");
		sb12.setType(ButtonTypeConstrant.TYPE_VIEW);
		sb12.setUrl("http://www.zerotop.top/wechat/tunnel/catalog?isImportant=1");
		List<Button> b1list = new ArrayList<Button>();
		b1list.add(sb11);
		b1list.add(sb12);
		b1.setSub_button(b1list);
		b1.setName("章节管理");
		
		
//		sb21.setKey("catalog");
//		sb21.setName("所有章节");
//		sb21.setType(ButtonTypeConstrant.TYPE_VIEW);
//		sb21.setUrl("http://www.zerotop.top/wechat/tunnel/catalog?isImportant=0");
//		List<Button> b2list = new ArrayList<Button>();
//		b1list.add(sb21);
		sb21.setKey("press");
		sb21.setName("围岩压力");
		sb21.setType(ButtonTypeConstrant.TYPE_VIEW);
		sb21.setUrl("http://www.zerotop.top/wechat/tunnel/calculation/1");
//		sb22.setKey("catalogimport");
//		sb22.setName("重点章节");
//		sb22.setType(ButtonTypeConstrant.TYPE_VIEW);
//		sb22.setUrl("http://localhost:8088/wechat/tunnel/calculation/1");
		List<Button> b2list = new ArrayList<Button>();
		b2list.add(sb21);
//		b2list.add(sb22);
		b2.setSub_button(b2list);
		b2.setName("计算题");
		
		
//		b2.setName("计算题");
//		b2.setType(ButtonTypeConstrant.TYPE_VIEW);
//		b2.setKey("calu");
//		b2.setUrl("http://www.zerotop.top/wechat/tunnel/calculation");
		
		
		b3.setName("网站");
		b3.setType(ButtonTypeConstrant.TYPE_VIEW);
		b3.setKey("mywebsite");
		b3.setUrl("http://www.zerotop.top");
		
		List<Button> menubutton = new ArrayList<Button>();
		
		menubutton.add(b1);
		menubutton.add(b2);
		menubutton.add(b3);
		
		Menu menu = new Menu();
		menu.setButton(menubutton);
		
		String url = URLConstrant.URL_MENU_CREATE + accessToken;
		
		
//		System.out.println(JSON.toJSON(menu));
		System.out.println(SendUtil.sendPost(url, JSON.toJSON(menu).toString()));
//		
		return url; 
		
		
	}
	
	public String createMenu(String accessToken){
		
		Button b1 = new Button();
		Button b2 = new Button();
		Button b3 = new Button();
		
		Button sb1 = new Button();
		Button sb2 = new Button();
		
//		b1.setType("view_limited");
//		b1.setName("素材");
//		b1.setType(ButtonTypeConstrant.TYPE_CLICK);
//		b1.setKey("clickme");
//		b1.setMedia_id("5ZaqY5prx25mSlK3XM-yBhQturTtvmBmvB-cNjPS2xM");


		
		b2.setName("再点我");
//		b2.setType(ButtonTypeConstrant.TYPE_CLICK);
//		b2.setKey("clickmeagain");
		
		sb1.setKey("sb1");
		sb1.setName("子菜单");
		sb1.setType(ButtonTypeConstrant.TYPE_CLICK);
		
		sb2.setKey("sb2");
		sb2.setName("jssdk");
		sb2.setType(ButtonTypeConstrant.TYPE_VIEW);
		sb2.setUrl("http://www.zerotop.top/wechat/signature");
		List<Button> buttonlist = new ArrayList<Button>();
		buttonlist.add(sb1);
		buttonlist.add(sb2);
		b2.setSub_button(buttonlist);
		
		b3.setName("网站");
		b3.setType(ButtonTypeConstrant.TYPE_VIEW);
		b3.setKey("mywebsite");
		b3.setUrl("http://www.zerotop.top");
		
		List<Button> menubutton = new ArrayList<Button>();
		
		menubutton.add(b1);
		menubutton.add(b2);
		menubutton.add(b3);
		
		Menu menu = new Menu();
		menu.setButton(menubutton);
		
		String url = URLConstrant.URL_MENU_CREATE + accessToken;
		
//		System.out.println( gson.toJson(menu));
		System.out.println(SendUtil.sendPost(url, gson.toJson(menu)));
		
		return url; 
	}

}
