package top.zerotop.wechat.manager;

import com.google.gson.Gson;

import top.zerotop.domain.Follower;
import top.zerotop.wechat.constrant.URLConstrant;
import top.zerotop.wechat.util.SendUtil;

public class FollowerManager {
	
	public static Gson gson = new Gson();

	public static void main(String args[]){
				
		FollowerManager followermanager = new FollowerManager();
		
		
		followermanager.UserInfo("ACCESS_TOKEN","openid");
		
	}
	
	public String ListFollower(String access_token){		
		String url = URLConstrant.URL_FOLLOWER_GET + access_token;
		
		String param = SendUtil.sendGet(url, null);
		
		Follower follow = gson.fromJson(param, Follower.class);
		
		System.out.println(follow);
		
		return url;
	}
	
	public String UserInfo(String access_token, String openid){
		
		String url = "https://api.weixin.qq.com/cgi-bin/user/info?"
				+ "access_token="+access_token
				+ "&openid="+openid
				+ "&lang=zh_CN";
		
		String param = SendUtil.sendGet(url, null);
		
		System.out.println(param);
		
		return url;
		
	}
}
