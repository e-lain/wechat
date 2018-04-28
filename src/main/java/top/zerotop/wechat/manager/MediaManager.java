package top.zerotop.wechat.manager;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import top.zerotop.domain.Article;
import top.zerotop.domain.Media;
import top.zerotop.wechat.TokenThread;
import top.zerotop.wechat.constrant.URLConstrant;
import top.zerotop.wechat.util.SendUtil;

public class MediaManager {
	
	public static final int MEDIA_TEMP = 0;
	
	public static final int MEDIA = 1; 
	
	private static Gson gson = new Gson();
	
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
//		MediaManager.uploadNewsMediaFile("9_7RX7mAM56LnWkR4Lp1b0Q6uhRGTkY2jvGo9HtGNU7F1diNEnD8QlyrB6SYCbaljqx0tYuFOBRmP3d_JrmELwE5s3CQ0R5AYH3MXfh0fu8YCoFsWxV4UhiKyBPOOs3cu-TMTCi63zs0Bc_BVbXEEdAJAAHH", 
//				articles);
	
		String accessToken = "9_jEP_NF42VMHEm4sUXMqMJ_GGh_DPZP45HifW-L0GZMrxNVwJrk1kyufDD2IxnhFcQtRNXRGC4VpI9oQUr93xPHvZ3ak-rSXyrspNe-tfvPWtpazK27gH-hVU_0xEpE21_nxVQ69iPLHMvsaERVJaAIAWAO";
		
		String data = "{"
				+"\"type\":\"news\","
				+"\"offset\":0,"
				+"\"count\":2"
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
    	
    	String url = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token="+accessToken;
    	
    	SendUtil.sendPost(url, "{\"articles\":"+gson.toJson(articles)+"}");
    	
    	return "";
    }
    
    public static String getMediaFile(String accessToken, String data){
    	
    	String url = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token="+accessToken;
    	List<Article> articles = new ArrayList<Article>();
    	try{
    		String json = SendUtil.sendPost(url, data);
    		System.out.println(json);
			JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
			com.google.gson.JsonArray jsonArray = jsonObject.getAsJsonArray("item");
			Article art = new Article();
			if(jsonArray != null){
				for (JsonElement a : jsonArray) {				
					art = gson.fromJson(a, new TypeToken<Article>() {}.getType());
					articles.add(art);
				}
			}
    	
		System.out.println(gson.toJson(articles));
    	}catch(Exception e){
    		e.printStackTrace();
    	}    	
    	return gson.toJson(articles);
    }
    
    
}
