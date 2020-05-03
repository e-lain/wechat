package top.zerotop.wechat.manager;

import com.google.gson.JsonParser;
import top.zerotop.util.SendUtils;

public class Menu {
	public String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=access_token=ACCESS_TOKEN";
	public String param = "";

	//创建菜单
	public String createMenu(String token, String data) {
		String url = "https://api.weixin.qq.com/cgi-bin/menu/create?"
				+ "access_token="+token;

		return SendUtils.sendPost(url, data);
	}

	public static void main(String [] args){
		String uploadUrl = "https://api.weixin.qq.com/cgi-bin/media/upload?"
				+ "access_token=ACCESS_TOKEN"
				+ "&type=image";
		
		String getUrl = "https://api.weixin.qq.com/cgi-bin/media/get?"
				+ "access_token=ACCESS_TOKEN"
				+ "&media_id=MediaId";
		


		String postUrl = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?"
				+ "access_token=ACCESS_TOKEN";

		String postJson = "{"
				+ "\"type\":\"image\","
				+ "\"offset\":0,"
				+ "\"count\":10"
				+ "}";
		JsonParser parser = new JsonParser();
//		System.out.println(SendUtil.sendPost(PostUrlMenu, json));
//		System.out.println(SendUtil.sendGetImage(getUrl, ""));
//		System.out.println(SendUtil.uploadTempMaterial(uploadUrl, "D:me.jpg"));
		System.out.println("----------end-----------------");
	}
}
