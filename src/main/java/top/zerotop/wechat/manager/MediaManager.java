package top.zerotop.wechat.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import top.zerotop.domain.Media;
import top.zerotop.domain.material.Article;
import top.zerotop.domain.material.ArticleItem;
import top.zerotop.domain.material.Material;
import top.zerotop.wechat.constrant.URLConstrant;
import top.zerotop.wechat.util.SendUtil;

public class MediaManager {
	
	public static final int MEDIA_TEMP = 0;
	
	public static final int MEDIA = 1; 
	
	private static Gson gson = new Gson();
	
	Map<String, String> map = new HashMap<String, String>();
	static XStream xstream = new XStream(new DomDriver());
	
	public static void main(String args[]){
		
		/**
		 * 上传临时素材
		 */
//		 String re = MediaManager.uploadTempMediaFile("accessToken", 
//				 "image", 
//				 "D:/wangye.jpg");
//		 System.out.println(re);
		
		/**
		 * 获取素材
		 */
//		String accessToken = "accessToken";
//		String mediaid = "mediaid";
//		MediaManager.getMaterial(accessToken, mediaid);
		
		/**
		 * 创建新的素材
		 */
		
		
//		List<Article> articles = new ArrayList<Article>();
//		Article article = new Article();
//		article.setAuthor("zerotop");
//		article.setContent("<p data-v-e335c1d2=\"\">罗小黑战记<br><img src=\"http://www.zerotop.top/media/image/054104085441068E6A0A4D03B62C726E.jpg\" style=\"max-width: 100%;\"></p><p data-v-e335c1d2=\"\">小黑</p><p><br></p>");
//		article.setTitle("罗小黑战记群发版");
//		article.setDigest("罗小黑战记");
//		article.setThumb_media_id("ibIdmu1_flVW0x6kKg4TnGA6nAESalUQJbJYwErn1TaZPIiwKoA-yhl0gbgExLwz");
//		article.setShow_cover_pic((byte)1);
//		article.setContent_source_url("www.zerotop.top/blog/article/2343489");
////		article.setNeed_open_comment(1);
////		article.setOnly_fans_can_comment(1);
//		articles.add(article);
//		
//		MediaManager.addNews("accessToken", 
//				articles);
	
		/**
		 * 查看所有素材
		 */
//		String accessToken = "accesstoken";
//		
//		String data = "{"
//				+"\"type\":\"news\","
//				+"\"offset\":0,"
//				+"\"count\":5"
//				+"}";
//		
//		MediaManager.batchgetMaterial(accessToken, data);
		
		
		/**
		 * 修改永久素材
		 */
//		Article article = new Article();
//		article.setAuthor("zerotop");
//		article.setContent("<p data-v-e335c1d2=\"\">罗小黑战记<br><img src=\"http://www.zerotop.top/media/image/054104085441068E6A0A4D03B62C726E.jpg\" style=\"max-width: 100%;\"></p><p data-v-e335c1d2=\"\">小黑</p><p><br></p>");
//		article.setTitle("罗小黑战记");
//		article.setDigest("罗小黑战记精选");
//		article.setThumb_media_id("mediaid");
//		article.setShow_cover_pic((byte)1);
//		article.setContent_source_url("www.zerotop.top/blog/article/2343489");
//		article.setNeed_open_comment(1);
//		article.setOnly_fans_can_comment(1);
//		MediaManager.updateNews("accessToken", 
//				"mediaid", 0, article);
	}
    
	/** 
     * 上传临时素材
     * @param accessToken 
     * @param type 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb，主要用于视频与音乐格式的缩略图）
     * @param path 图片路径 
     * @return 
     */  
    public static String uploadTempMediaFile(String accessToken,String type, String path){  
        String result = null;  
        	
        String url =  URLConstrant.URL_MEDIA_UPLOAD + accessToken + "&type="+type;
       
        try {  
            result = SendUtil.uploadTempMaterial(url, path);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return result;  
    }  
    
    /**
     * 新增永久素材
     * @param accessToken
     * @param type
     * @param path
     * @return
     */
    public static String addMaterial(String accessToken, String type, String path){  
        String result = null;  
//        TreeMap<String, String> params = new TreeMap<String, String>();  
//        params.put("access_token", accessToken);  
//        params.put("type", type);  
       
        String url = URLConstrant.URL_MATERIAL_ADD_MATERIAL  + accessToken + "&type="+type;
        
        try {  
            result = SendUtil.uploadTempMaterial(url, path);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return result;  
    }  
    
//    {"media_id":"5ZaqY5prx25mSlK3XM-yBuBJm89j3El56IeQxJ0q8sk","url":"http:\/\/mmbiz.qpic.cn\/mmbiz_jpg\/vS1hjjKOH4TJjZwZwrQXb5rxJMshWteFJQQuW6nCiax2gQ3F4vfNqd6xVLOD2ic2ldaqoick1T3QYhXiboEnwO2Yyw\/0?wx_fmt=jpeg"}
    
    /**
     * 根据mediaid获取素材
     * @param url 
     * @return
     */
    public static String getMaterial(String accessToken, String mediaid){
    	
    	String url = URLConstrant.URL_MATERIAL_GET_MATERIAL + accessToken;
    	
    	Media media = new Media();
    	media.setMedia_id(mediaid);
    	
    	System.out.println(SendUtil.sendPost(url, gson.toJson(media)));
    	
    	return url;
    }
     
    
    /**
     * 上传图文素材
     * @param accessToken
     * @param articles
     * @return
     */
    public static String addNews(String accessToken, List<Article> articles){
    	
    	System.out.println("{\"articles\":"+gson.toJson(articles)+"}");
    	
//    	String url = URLConstrant.URL_MATERIAL_ADD_NEWS + accessToken;
    	String url = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token="+ accessToken;
    	
    	try{
    		String res =  SendUtil.sendPost(url, "{\"articles\":"+gson.toJson(articles)+"}");
    		System.out.println(res);
    	} catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	return "";
    }
    /**
     * 修改永久素材
     * @param accessToken
     * @param mediaid         要修改的图文消息的id
     * @param index           要更新的文章在图文消息中的位置（多图文消息时，此字段才有意义），第一篇为0
     * @param article         文章对象
     * @return
     */
    public static String updateNews(String accessToken, String mediaid, int index ,Article article){
    	
    	String url = URLConstrant.URL_MATERIAL_UPDATE_NEWS + accessToken;
    	
    	String data = "{\"media_id\":"+mediaid+",\"index\":"+index+",\"articles\":"+gson.toJson(article)+"}";
    	
    	try{
    		String res =  SendUtil.sendPost(url, data);
    		System.out.println(res);
    	} catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	return "";
    }
    
    /**
     * 获取所有素材-- 并返回素材列表
     * 用于定制回复用户图文消息
     * @param accessToken
     * @param data  
     * @return
     */
    public static List<ArticleItem> batchgetMaterial(String accessToken, String data){
    	
    	String url = URLConstrant.URL_MATERIAL_BATCHGET_MATERIAL +accessToken;
    	List<Material> materialList = new ArrayList<Material>();
    	List<ArticleItem> artItemList = new ArrayList<ArticleItem>();
		Material me = new Material();
		ArticleItem artItem = new ArticleItem();
    	try{
    		String json = SendUtil.sendPost(url, data);
    		System.out.println(json);
			JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
			com.google.gson.JsonArray jsonArray = jsonObject.getAsJsonArray("item");
			
			if(jsonArray != null){
				for (JsonElement a : jsonArray) {				
					me = gson.fromJson(a, new TypeToken<Material>() {}.getType());
					materialList.add(me);	
					
					xstream.alias("art", me.getClass());
					String resMes = xstream.toXML(me);
					System.out.println(resMes+"-----------------");
					
					List<Article> alist = me.getContent().getNews_item();
					if(alist.size() >= 0){
						for(Article ait: alist ){
							artItem = new ArticleItem();
							artItem.setDescription("描述");
							artItem.setPicUrl("http://mmbiz.qpic.cn/mmbiz_jpg/vS1hjjKOH4TJjZwZwrQXb5rxJMshWteFJQQuW6nCiax2gQ3F4vfNqd6xVLOD2ic2ldaqoick1T3QYhXiboEnwO2Yyw/0?wx_fmt=jpeg");
							artItem.setTitle("我的文章");
							artItem.setUrl(ait.getUrl());
							artItemList.add(artItem);
						}
					}
					System.out.println(artItem.toString());
					
				}
			}
			
			System.out.println("-----------------------------");
//			xstream.alias("item", artItemList.getClass());
//			String resM = xstream.toXML(artItemList);
//			System.out.println(resM);
			
       	}catch(Exception e){
    		e.printStackTrace();
    	}    	
    	return artItemList;
    }
    
    
}
