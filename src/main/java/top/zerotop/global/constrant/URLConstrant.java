package top.zerotop.global.constrant;

public class URLConstrant {
	
	/**
	 * 临时素瓷
	 */
	public static final String URL_MEDIA_UPLOAD = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=";
	
	public static final String URL_MEDIA_GET = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=";
	
	/**
	 * 永久素材
	 */
	//添加素材
	public static final String URL_MATERIAL_ADD_MATERIAL =  "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=";
	//添加图文素材
	public static final String URL_MATERIAL_ADD_NEWS = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=";
	//获取素材列表
	public static final String URL_MATERIAL_BATCHGET_MATERIAL = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=";
	//获取素材
	public static final String URL_MATERIAL_GET_MATERIAL = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=";
	//更新图文素材
	public static final String URL_MATERIAL_UPDATE_NEWS = "https://api.weixin.qq.com/cgi-bin/material/update_news?access_token=";
	
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
