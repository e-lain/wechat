package top.zerotop.global.constrant;

public class URLConstrant {

	public static final String BASE_URL = "https://api.weixin.qq.com/cgi-bin";

	/**
	 * 临时素材
	 */
	public static final String URL_MEDIA_UPLOAD = BASE_URL + "/media/upload?access_token={ACCESS_TOKEN}&type={TYPE}";
	
	public static final String URL_MEDIA_GET = BASE_URL + "/media/get?access_token={ACCESS_TOKEN}&&media_id={MEDIA_ID}";
	
	/**
	 * 永久素材
	 */
	//添加素材
	public static final String URL_MATERIAL_ADD_MATERIAL =  BASE_URL + "/material/add_material?access_token={ACCESS_TOKEN}";
	//添加图文素材
	public static final String URL_MATERIAL_ADD_NEWS = BASE_URL + "/material/add_news?access_token={ACCESS_TOKEN}";
	//获取素材列表
	public static final String URL_MATERIAL_BATCHGET_MATERIAL = BASE_URL + "/material/batchget_material?access_token={ACCESS_TOKEN}";
	//获取素材
	public static final String URL_MATERIAL_GET_MATERIAL = BASE_URL + "/material/get_material?access_token={ACCESS_TOKEN}";
	//更新图文素材
	public static final String URL_MATERIAL_UPDATE_NEWS = BASE_URL + "/material/update_news?access_token={ACCESS_TOKEN}";
	
	/**
	 * menu
	 */
	public static final String URL_MENU_CREATE = BASE_URL + "/menu/create?access_token={ACCESS_TOKEN}";
	
	public static final String URL_MENU_GET = BASE_URL + "/menu/get?access_token={ACCESS_TOKEN}";

	public static final String URL_MENU_DELETE = BASE_URL + "/menu/delete?access_token={ACCESS_TOKEN}";

	/**
	 * follower
	 */
	public static final String URL_FOLLOWER_GET = BASE_URL + "/user/get?access_token={ACCESS_TOKEN}";
	
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
