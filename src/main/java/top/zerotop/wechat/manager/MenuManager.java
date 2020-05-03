package top.zerotop.wechat.manager;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import top.zerotop.domain.menu.Button;
import top.zerotop.domain.menu.Menu;
import top.zerotop.global.constrant.ButtonTypeConstrant;
import top.zerotop.global.constrant.URLConstrant;
import top.zerotop.util.GsonUtils;
import top.zerotop.util.SendUtils;

public class MenuManager {

	public static void main(String args[]){
		MenuManager menumanager = new MenuManager();
		String accessToken = "";
		
//		menumanager.getMenu("ACCESS_TOKEN");
	}

	//创建menu
	public String getMenu(String access_token){
		String url = URLConstrant.URL_MENU_GET + access_token;
		
		System.out.println(SendUtils.sendGet(url));
		return url;
	}

	//删除菜单
	public String deleteMenu(String access_token){
		String url = URLConstrant.URL_MENU_GET + access_token;
		
		System.out.println(SendUtils.sendGet(url));
		
		return url;
	}

	//创建menu
	public String createMenu(String accessToken){
		Button b1 = new Button();
		Button b2 = new Button();
		Button b3 = new Button();
		
		Button sb1 = new Button();
		Button sb2 = new Button();
		
		b2.setName("再点我");
		
		sb1.setKey("sb1");
		sb1.setName("子菜单");
		sb1.setType(ButtonTypeConstrant.TYPE_CLICK);
		
		sb2.setKey("sb2");
		sb2.setName("jssdk");
		sb2.setType(ButtonTypeConstrant.TYPE_VIEW);
		sb2.setUrl("http://www.zerotop.top/wechat/signature");
		List<Button> buttonlist = new ArrayList<>();
		buttonlist.add(sb1);
		buttonlist.add(sb2);
		b2.setSub_button(buttonlist);
		
		b3.setName("网站");
		b3.setType(ButtonTypeConstrant.TYPE_VIEW);
		b3.setKey("mywebsite");
		b3.setUrl("http://www.zerotop.top");
		
		List<Button> menubutton = new ArrayList<>();
		
		menubutton.add(b1);
		menubutton.add(b2);
		menubutton.add(b3);
		
		Menu menu = new Menu();
		menu.setButton(menubutton);
		
		String url = URLConstrant.URL_MENU_CREATE + accessToken;
		
		System.out.println(SendUtils.sendPost(url, GsonUtils.toJson(menu)));
		return url; 
	}

}
