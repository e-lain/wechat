package top.zerotop.domain.material;

import java.util.List;

/**
 *@author 作者: zerotop
 *@createDate 创建时间: 2018年4月29日上午10:44:55
 */
public class Material {
	
	private String media_id;
	
	private ContentItem content;

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	public ContentItem getContent() {
		return content;
	}

	public void setContent(ContentItem content) {
		this.content = content;
	}

	public class ContentItem{
		
		private List<Article> news_item;
		
		private Long create_time;
		
		private Long update_time;

		public List<Article> getNews_item() {
			return news_item;
		}

		public void setNews_item(List<Article> news_item) {
			this.news_item = news_item;
		}

		public Long getCreate_time() {
			return create_time;
		}

		public void setCreate_time(Long create_time) {
			this.create_time = create_time;
		}

		public Long getUpdate_time() {
			return update_time;
		}

		public void setUpdate_time(Long update_time) {
			this.update_time = update_time;
		}

		@Override
		public String toString() {
			return "ContentItem [news_item=" + news_item + ", create_time=" + create_time + ", update_time="
					+ update_time + "]";
		}
		
	}

	@Override
	public String toString() {
		return "Material [media_id=" + media_id + ", content=" + content + "]";
	}
	
}
