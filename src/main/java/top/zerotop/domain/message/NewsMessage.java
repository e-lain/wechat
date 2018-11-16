package top.zerotop.domain.message;

import java.util.List;

import top.zerotop.domain.material.ArticleItem;
import top.zerotop.domain.message.BaseMessage;
import top.zerotop.global.constrant.MessageTypeConstrant;

/**
 *@author 作者: zerotop
 *@createDate 创建时间: 2018年4月28日下午1:47:26
 */
public class NewsMessage extends BaseMessage {
	
	private int ArticleCount;
	
	private List<ArticleItem> Articles;

	public NewsMessage(String fromUserName, String toUserName) {
		super(MessageTypeConstrant.MESSAGE_NEWS, fromUserName, toUserName);
	}

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<ArticleItem> getArticles() {
		return Articles;
	}

	public void setArticles(List<ArticleItem> articles) {
		Articles = articles;
	}

}