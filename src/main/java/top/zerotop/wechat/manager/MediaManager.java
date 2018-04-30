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
import top.zerotop.domain.NewsMessage;
import top.zerotop.domain.material.Article;
import top.zerotop.domain.material.ArticleItem;
import top.zerotop.domain.material.Material;
import top.zerotop.wechat.TokenThread;
import top.zerotop.wechat.constrant.URLConstrant;
import top.zerotop.wechat.util.SendUtil;

public class MediaManager {
	
	public static final int MEDIA_TEMP = 0;
	
	public static final int MEDIA = 1; 
	
	private static Gson gson = new Gson();
	
	Map<String, String> map = new HashMap<String, String>();
	static XStream xstream = new XStream(new DomDriver());
	
	public static void main(String args[]){
		
//		 String re = MediaManager.uploadMediaFile("9_mv1gMhFg5mARJ4tapaRKghafUhmSIUNad3ztMy9J9sgVf8N9Z4bm-ti5yHBs_V0IiXMzyre918FVHBXA9l2oc8cnpWfH8YvBeHqQrd7llqbgTCb-fgbqAew-MJnDNa9W_ZcYMnPQwxGS9YjRLFYdAJAYSL", 
//				 "image", 
//				 "D:/me.jpg");
//		 
//		 System.out.println(re);
		
		/**
		 * 获取素材
		 */
//		String url = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=9_mv1gMhFg5mARJ4tapaRKghafUhmSIUNad3ztMy9J9sgVf8N9Z4bm-ti5yHBs_V0IiXMzyre918FVHBXA9l2oc8cnpWfH8YvBeHqQrd7llqbgTCb-fgbqAew-MJnDNa9W_ZcYMnPQwxGS9YjRLFYdAJAYSL";
//		
//		MediaManager.listMediaFile(url);
		
		/**
		 * 创建新的素材
		 */
//		List<Article> articles = new ArrayList<Article>();
//		Article article = new Article();
//		article.setAuthor("zerotop");
//		article.setContent("文章---第二篇永久素材--内容是我随便编的");
//		article.setDigest("第二篇文章");
//		article.setTitle("test");
//		article.setThumb_media_id("5ZaqY5prx25mSlK3XM-yBuBJm89j3El56IeQxJ0q8sk");
//		article.setShow_cover_pic((byte)1);
//		article.setContent_source_url("www.zerotop.top/blog/article/3270888");
//		articles.add(article);
//		MediaManager.uploadNewsMediaFile("9_hxDzj0PooTwCWKTIMYBR0UEx4zTCq6EpCXmebLPwNtQJdP11jX3pLgyQ8zTOhmtLtzLntnUgylElr4kUNA_D_jRc0KAtq-2XkxxIrMNroM6vW75LknKCQAX1WqDc5iG7nfkPENohwOZKUnWYUSRbAEAZXW", 
//				articles);
	
		/**
		 * 查看所有素材
		 */
		String accessToken = "9_msmi0hvwE72ma8JFhxr70sbSfQ_I1tSUZ-nge40sFYiIdF5tGMt1FDa-0cC4uv4KlWYvhdZY9wnTJH-4-1t2KSHtvW6wH6-Bb_uGd49HOnYe-xkOAqp2MY7pnM96dHrMbNxC4fxL9FBNT2bfEKEcAJALRL";
		
		String data = "{"
				+"\"type\":\"news\","
				+"\"offset\":0,"
				+"\"count\":5"
				+"}";
		
		MediaManager.getMediaFile(accessToken, data);
	}
    
	/** 
     * 上传临时素材
     * @param accessToken 
     * @param type 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb，主要用于视频与音乐格式的缩略图）
     * @param path 图片路径 
     * @return 
     */  
    public static String uploadTempMediaFile(String accessToken,String type,String path){  
        String result = null;  
        	
        String url =  URLConstrant.URL_MEDIA_TEMP_UPLOAD + accessToken + "&type="+type;
        
        try {  
            result = SendUtil.uploadTempMaterial(url, path);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return result;  
    }  
    
    /** 
     * 上传y永久素材
     * @param accessToken 
     * @param 
     * @return 
     */  
    public static String uploadMediaFile(String accessToken, String type,String path){  
        String result = null;  
//        TreeMap<String, String> params = new TreeMap<String, String>();  
//        params.put("access_token", accessToken);  
//        params.put("type", type);  
        
        
        String url = URLConstrant.URL_MEDIA_UPLOAD  + accessToken + "&type="+type;
        
        try {  
            result = SendUtil.uploadTempMaterial(url, path);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return result;  
    }  
    
//    {"media_id":"5ZaqY5prx25mSlK3XM-yBuBJm89j3El56IeQxJ0q8sk","url":"http:\/\/mmbiz.qpic.cn\/mmbiz_jpg\/vS1hjjKOH4TJjZwZwrQXb5rxJMshWteFJQQuW6nCiax2gQ3F4vfNqd6xVLOD2ic2ldaqoick1T3QYhXiboEnwO2Yyw\/0?wx_fmt=jpeg"}
    
    public static String listMediaFile(String url){
    	
    	Media media = new Media();
    	media.setMedia_id("5ZaqY5prx25mSlK3XM-yBuBJm89j3El56IeQxJ0q8sk");
//    	System.out.println(gson.toJson(media));
    	
    	System.out.println(SendUtil.sendPost(url, gson.toJson(media)));
    	
    	return url;
    }
      
    public static String uploadNewsMediaFile(String accessToken, List<Article> articles){
    	
    	System.out.println("{\"articles\":"+gson.toJson(articles)+"}");
    	
//    	String url = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token="+accessToken;
    	
    	try{
//    		SendUtil.sendPost(url, "{\"articles\":"+gson.toJson(articles)+"}");
    	} catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	return "";
    }
    
    public static List<ArticleItem> getMediaFile(String accessToken, String data){
    	
    	String url = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token="+accessToken;
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
							artItem.setPicUrl("www.zerotop.top/resource/image/e.jpg");
							artItem.setTitle("我的文章");
							artItem.setUrl(ait.getUrl());
							artItemList.add(artItem);
						}
					}
					
					System.out.println(artItem.toString());
					
				}
			}
			
			System.out.println("----------------------------------+++++++++++++++++++");
			
			xstream.alias("item", artItemList.getClass());
			String resM = xstream.toXML(artItemList);
			System.out.println(resM);
			
       	}catch(Exception e){
    		e.printStackTrace();
    	}    	
    	return artItemList;
    }
    
    
}
