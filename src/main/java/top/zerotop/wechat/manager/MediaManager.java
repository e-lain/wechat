package top.zerotop.wechat.manager;

import top.zerotop.wechat.constrant.URLConstrant;
import top.zerotop.wechat.util.SendUtil;

public class MediaManager {
	
	
	public static void main(String args[]){
		
		 String re = MediaManager.uploadTempMediaFile("ACCESS_TOKEN", 
				 "image", 
				 "D:/me.jpg");
		 
		 System.out.println(re);
		
		
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
//        TreeMap<String, String> params = new TreeMap<String, String>();  
//        params.put("access_token", accessToken);  
//        params.put("type", type);  
        
        
        String url = URLConstrant.URL_MEDIA_UPLOAD  + accessToken + "&type=image";
        
        try {  
            result = SendUtil.uploadTempMaterial(url, path);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return result;  
    }  
      
}
