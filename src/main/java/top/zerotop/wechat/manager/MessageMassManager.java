package top.zerotop.wechat.manager;

import top.zerotop.wechat.util.SendUtil;

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
		
//		String url = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token="+accessToken;
		
//		String data = "{\"touser\":["
//				+ "\"OPENID1\","
//				+ "\"OPENID1\"],"
//				+"\"mpnews\":{"
//				+"\"media_id\":\"123dsdajkasd231jhksad\""
//				+"},"
//				+"\"msgtype\":\"mpnews\"，"
//				+"\"send_ignore_reprint\":0}";
		
		String url = "https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token="+accessToken;
		
		String data = "{"
				   +"\"touser\":\"OPENID1\"," 
				   +"\"mpnews\":{"
				   + "\"media_id\":\"mediaid\""               
				   + "},"
				   +"\"msgtype\":\"mpnews\"" 
				   +"}";
			String res = SendUtil.sendPost(url, data);
			System.out.println(res);
		
			return"";

	}
}
