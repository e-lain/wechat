package top.zerotop.domain.material;
/**
 *@author 作者: zerotop
 *@createDate 创建时间: 2018年4月29日上午10:34:56
 */
public class ArticleItem{
	
	private String Title;
	
	private String Description;
	
	private String PicUrl;
	
	private String Url;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	@Override
	public String toString() {
		return "ArticleItem [Title=" + Title + ", Description=" + Description + ", PicUrl=" + PicUrl + ", Url=" + Url
				+ "]";
	}
	
	
}