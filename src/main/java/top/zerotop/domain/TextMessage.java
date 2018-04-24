package top.zerotop.domain;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamImplicit;


public class TextMessage extends BaseMessage {
	
	/**
	 * 消息内容
	 */
	private String Content;
	
	/**
	 * media列表
	 */
	private List<Media> medialist;
	
	public String getContent() {
		return Content;
	}
	
	public void setContent(String content) {
		Content = content;
	}
	
	public List<Media> getMedialist() {
		return medialist;
	}
	public void setMedialist(List<Media> medialist) {
		this.medialist = medialist;
	}

}
