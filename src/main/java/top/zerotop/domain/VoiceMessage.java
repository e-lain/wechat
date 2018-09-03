package top.zerotop.domain;

public class VoiceMessage extends BaseMessage {
	
	private String MediaId;
	
	private String Format;
	
	/**
	 * 转xml时 标签为<Voice>
	 */
	private Media Voice;
	
	private String Recognition;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getFormat() {
		return Format;
	}

	public void setFormat(String format) {
		Format = format;
	}

	public String getRecognition() {
		return Recognition;
	}

	public void setRecognition(String recognition) {
		Recognition = recognition;
	}

	public Media getVoice() {
		return Voice;
	}

	public void setVoice(Media voice) {
		Voice = voice;
	}
	
	
}
