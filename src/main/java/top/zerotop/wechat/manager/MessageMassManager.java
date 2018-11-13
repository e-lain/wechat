package top.zerotop.wechat.manager;

import com.alibaba.fastjson.JSON;
import top.zerotop.global.constrant.URLConstrant;
import top.zerotop.util.SendUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 群发
 * 
 * @author 作者: zerotop
 * @createDate 创建时间: 2018年5月2日上午1:13:31
 */
public class MessageMassManager {

	public static void main(String args[]) {
		String accessToken = "accessToken";
		String mediaid = "";
		MessageMassManager.send(accessToken, mediaid);
	}

	public static String send(String accessToken, String mediaid){
		
//		String url = URLConstrant.BASE_URL + "/message/mass/send?access_token="+accessToken;
		
//		String data = "{\"touser\":["
//				+ "\"OPENID1\","
//				+ "\"OPENID1\"],"
//				+"\"mpnews\":{"
//				+"\"media_id\":\"123dsdajkasd231jhksad\""
//				+"},"
//				+"\"msgtype\":\"mpnews\"，"
//				+"\"send_ignore_reprint\":0}";
		
		String url = URLConstrant.BASE_URL + "/message/mass/preview?access_token="+accessToken;

		Map<String, String> map = new HashMap<>();
		map.put("touser","OPENID1");
		map.put("msgtype","mpnews");
		map.put("mpnews","'media_id':'mediaid'" );
		String res = SendUtils.sendPost(url, JSON.toJSONString(map));
		System.out.println(res);
		
		return"";
	}
}
