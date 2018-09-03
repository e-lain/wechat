package top.zerotop.domain;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class ImageMessage extends BaseMessage {

	
	/**
	 * 图片消息
	 */
	@XStreamImplicit(itemFieldName="Image")
	private String MediaId;
	
	/**
	 * 图标消息 --- xml解析是标签为<Image>
	 */
	private Media Image;
	
	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public Media getImage() {
		return Image;
	}

	public void setImage(Media image) {
		Image = image;
	}

}