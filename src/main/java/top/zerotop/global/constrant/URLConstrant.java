package top.zerotop.global.constrant;

public class URLConstrant {
	public static final String BASE_URL = "https://api.weixin.qq.com/cgi-bin";

	/**
	 * 临时素材
	 */
	public static final String URL_MEDIA_UPLOAD = BASE_URL + "/media/upload?access_token={access_token}&type={type}";
	
	public static final String URL_MEDIA_GET = BASE_URL + "/media/get?access_token={access_token}&&media_id={media_id}";
	
	/**
	 * 永久素材
	 */
	//添加素材
	public static final String URL_MATERIAL_ADD_MATERIAL =  BASE_URL + "/material/add_material?access_token={ACCESS_TOKEN}&type={type}";
	//添加图文素材
	public static final String URL_MATERIAL_ADD_NEWS = BASE_URL + "/material/add_news?access_token={ACCESS_TOKEN}";
	//获取素材列表
	public static final String URL_MATERIAL_BATCHGET_MATERIAL = BASE_URL + "/material/batchget_material?access_token={ACCESS_TOKEN}";
	//获取素材
	public static final String URL_MATERIAL_GET_MATERIAL = BASE_URL + "/material/get_material?access_token={access_token}";
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

	public static final String URE_GET_USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token={access_token}&openid={openid}&lang=zh_CN";


	public static final String DATACUBE_URL = "https://api.weixin.qq.com/datacube";
	/**
	 * 用户分析接口
	 */
	public static final String URL_USER_SUMMARY = DATACUBE_URL + "/getusersummary?access_token={ACCESS_TOKEN}";

	public static final String URL_USER_CUMULATE = DATACUBE_URL + "/getusercumulate?access_token=ACCESS_TOKEN";
}
