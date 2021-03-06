package top.zerotop.domain.message;

import com.thoughtworks.xstream.annotations.XStreamImplicit;
import top.zerotop.domain.Media;
import top.zerotop.global.constrant.MessageTypeConstrant;

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

	public ImageMessage(String fromUserName, String toUserName) {
		super(MessageTypeConstrant.MESSAGE_IMAGE, fromUserName, toUserName);
	}
	
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