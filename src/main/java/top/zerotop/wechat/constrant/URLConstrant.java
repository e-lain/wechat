package top.zerotop.wechat.constrant;

public class URLConstrant {
	
	/**
	 * 素瓷
	 */
	public static final String URL_MEDIA_TEMP_UPLOAD = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=";
	
	public static final String URL_MEDIA_UPLOAD =  "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=";
	
	public static final String URL_MEDIA_GET = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=";
	
	/**
	 * 
	 */
	public static final String URL_MATERIAL = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=";
	
	/**
	 * menu
	 */
	public static final String URL_MENU_CREATE = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";
	
	public static final String URL_MENU_GET = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=";

	/**
	 * follower
	 */
	public static final String URL_FOLLOWER_GET = "https://api.weixin.qq.com/cgi-bin/user/get?access_token="; 
	
	//https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID1
	
	
	/**
	 * examples
	 */
	String uploadUrl = "https://api.weixin.qq.com/cgi-bin/media/upload?"
			+ "access_token=ACCESS_TOKEN"
			+ "&type=image";
	
	String getUrl = "https://api.weixin.qq.com/cgi-bin/media/get?"
			+ "access_token=ACCESS_TOKEN"
			+ "&media_id=MediaId";
	
	String PostUrlMenu = "https://api.weixin.qq.com/cgi-bin/menu/create?"
			+ "access_token=ACCESS_TOKEN";

	String postUrl = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?"
			+ "access_token=ACCESS_TOKEN";

}
