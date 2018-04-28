package top.zerotop.domain;


public class Media{

	/**
	 * MediaId
	 */
	private String MediaId;
	
	private String media_id;
	
	public Media(){}
	
	public Media(String mediaId){
	 	this.MediaId = mediaId;
	}
	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getMedia_id() {
		return media_id;
	}
	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}
}