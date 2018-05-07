package top.zerotop.wechat.manager;

import java.util.ArrayList;
import java.util.List;

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
		
		String accessToken = "9_Jwxri3VSAHSCmyUyc7YBHH1AJKVjpaa1TLJORikY84VaWMuYRO-PSg_UFQv9JJ3pUPVCzdJf1QjIiiU_wBcPAsgQZMYqsZOgxgEyobTXP4sPRdxPqZR6KNuEORqHdH3SnbBQXIakwchfYYC_REGcAEAVIK";
		
//		menumanager.getMenu("ACCESS_TOKEN");
		
		menumanager.createMenu(accessToken);
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
	
	public String createMenu(String accessToken){
		
		Button b1 = new Button();
		Button b2 = new Button();
		Button b3 = new Button();
		
		Button sb1 = new Button();
		Button sb2 = new Button();
		
		b1.setType("view_limited");
		b1.setName("素材");
		b1.setType(ButtonTypeConstrant.TYPE_CLICK);
		b1.setKey("clickme");
		b1.setMedia_id("5ZaqY5prx25mSlK3XM-yBhQturTtvmBmvB-cNjPS2xM");
		
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
