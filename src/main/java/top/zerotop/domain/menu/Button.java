package top.zerotop.domain.menu;

import java.util.List;

public class Button {
	
	/**
	 * 菜单相应动作类型，view表示网页类型，click表示点击类型，miniprogram表示小程序类型
	 */
	private String type;
	
	/**
	 * 菜单标题
	 */
	private String name;
	
	/**
	 * 菜单KEY，值用于消息接口推送，不超过128字节
     *！！click等点击类型必须！！
	 */
	private String key;
	
	/**
	 * 网页链接，用户点击菜单可打开链接
	 * 	view、miniprogram类型必须
	 */
	private String url;
	
	/**
	 * 调用新增永久素材接口返回的合法media_id
	 * media_id类型和view_limited类型必须
	 */
	private String media_id;
	
	private List<Button> sub_button;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMedia_id() {
		return media_id;
	}
	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}
	public List<Button> getSub_button() {
		return sub_button;
	}
	public void setSub_button(List<Button> sub_button) {
		this.sub_button = sub_button;
	}

	
}
