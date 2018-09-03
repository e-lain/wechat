package top.zerotop.wechat.manager;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

import top.zerotop.wechat.util.SendUtil;

public class Menu {

	private static Gson gson = new Gson();
	
	public String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=access_token=ACCESS_TOKEN";
	
	public String param = "{\"button\":[{\"type\":\"click\" ,\"name\":\"今日歌曲\" ,\"key\":\"V1001_TODAY_MUSIC\" },{\"name\":\"菜单\","
			+ "\"sub_button\":[{\"type\":\"view\",\"name\":\"搜索\",\"url:http://www.soso.com/\"},{"
			+ "\"type\":\"miniprogram\",\"name\":\"wxa\",\"url:http://mp.weixin.qq.com\",\"appid:wx286b93c14bbf93aa\","
			+ "\"pagepath\":\"pages/lunar/index\"},{\"type\":\"click\",\"name\":\"赞一下我们\",\"key\":\"V1001_GOOD\"}]"
			+ "}]}";

	

	public static void main(String [] args){
		String uploadUrl = "https://api.weixin.qq.com/cgi-bin/media/upload?"
				+ "access_token=ACCESS_TOKEN"
				+ "&type=image";
		
		String getUrl = "https://api.weixin.qq.com/cgi-bin/media/get?"
				+ "access_token=ACCESS_TOKEN"
				+ "&media_id=MediaId";
		
		String PostUrlMenu = "https://api.weixin.qq.com/cgi-bin/menu/create?"
				+ "access_token= ACCESS_TOKEN";

		String postUrl = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?"
				+ "access_token=ACCESS_TOKEN";
		String param = "{\"button\": [{"
				+ "\"name\": \"扫码\","
				+ "\"sub_button\": [{"
				+ "\"type\": \"scancode_waitmsg\","
				+ "\"name\": \"扫码带提示\","
				+ "\"key\": \"rselfmenu_0_0\","
				+ "\"sub_button\": [ ]"
				+ "}, "
				+ "{"
				+ "\"type\": \"scancode_push\","
				+ "\"name\": \"扫码推事件\", "
				+ "\"key\": \"rselfmenu_0_1\","
				+ "\"sub_button\": [ ]"
				+ "}"
				+ "]"
				+ "}, "
				+ "{"
				+ "\"name\": \"发图\","
				+ "\"sub_button\": ["
				+ "{"
				+ "\"type\": \"pic_sysphoto\","
				+ "\"name\": \"系统拍照发图\", "
				+ "\"key\": \"rselfmenu_1_0\","
				+ " \"sub_button\": [ ]"
				+ "}, "
				+ "{"
				+ "\"type\": \"pic_photo_or_album\", "
				+ "\"name\":\"拍照或者相册发图\","
				+ "\"key\": \"rselfmenu_1_1\","
				+ "\"sub_button\": [ ]"
				+ "}, "
				+ "{"
				+ " \"type\": \"pic_weixin\", "
				+ "\"name\": \"微信相册发图\","
				+ "\"key\": \"rselfmenu_1_2\","
				+ "\"sub_button\": [ ]"
				+ "}"
				+ "]"
				+ "}, "
				+ "{"
				+ "\"name\": \"发送位置\","
				+ "\"type\": \"location_select\","
				+ "\"key\": \"rselfmenu_2_0\""
				+ "},"
				+ "{"
				+ "\"type\": \"media_id\","
				+ "\"name\": \"图片\","
				+ "\"media_id\": \"MEDIA_ID1\""
				+ "}, "
				+ "{"
				+ " \"type\": \"view_limited\","
				+ "\"name\": \"图文消息\","
				+ "\"media_id\": \"MEDIA_ID2\""
				+ "}"
				+ "]}";
		String json = "{"
				+ "\"button\":["
				+ "{"
				+ "\"type\":\"click\","
				+ "\"name\":\"图片皮\","
				+ "\"key\":\"V1001_TODAY_IMAGE\""
				+ "},"
				+ "{"
				+ "\"name\":\"菜单\","
				+ "\"sub_button\":["
				+ "{"
				+ "\"type\":\"view\","
				+ "\"name\":\"网站\","
				+ "\"url\":\"http://www.zerotop.com/\""
				+ "},"

				+ "{"
				+ "\"type\":\"click\","
				+ "\"name\":\"赞一下\","
				+ "\"key\":\"V1001_GOOD\""
				+ "}]"
				+ "},"
				+ "{"
				+ "\"type\":\"view\","
				+ "\"name\":\"我们的小站\","
				+ "\"url\":\"http://www.zerotop.top\""
				+ "}]}";
		String postJson = "{"
				+ "\"type\":\"image\","
				+ "\"offset\":0,"
				+ "\"count\":10"
				+ "}";
		JsonParser parser = new JsonParser();
//		System.out.println(SendUtil.sendPost(PostUrlMenu, json));
//		System.out.println(SendUtil.sendPost(postUrl, postJson));
//		System.out.println(SendUtil.sendGetImage(getUrl, ""));
//		System.out.println(SendUtil.uploadTempMaterial(uploadUrl, "D:me.jpg"));
		System.out.println("----------end-----------------");
	}
}
