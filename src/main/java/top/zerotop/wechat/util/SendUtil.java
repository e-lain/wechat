package top.zerotop.wechat.util;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonParser;


public class SendUtil {
	
    private static Gson gson = new Gson();
    
    private static JsonParser parser = new JsonParser();
    
    static URL realUrl = null;
    
    static HttpURLConnection conn = null;
	
	public static String sendPostSC(String url, String param) {
		
		OutputStream outt = null; // utf-8编码
		try {
			
			realUrl = new URL(url);
			conn = (HttpURLConnection) realUrl.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setInstanceFollowRedirects(true);
			// conn.setRequestProperty("user-agent", "Mozilla/5.0");
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
			conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded"); // 设置发送数据的格式
			conn.connect();
			
			outt = conn.getOutputStream();
			
			outt.write(gson.toJson(param).toString().getBytes("UTF-8"));
			System.out.println("param-- " +parser.parse(param));
			outt.flush();
			outt.close();
			
			System.out.println("----------------conn--------------------" );

			int code = conn.getResponseCode();
			System.out.println("code " + code);
			System.out.println("resmessage " + conn.getResponseMessage());
			InputStream is = null;
			if (code == 200) {
				is = conn.getInputStream();
			} else {
				is = conn.getErrorStream();
			}

			// 读取响应
			int length = (int) conn.getContentLength();// 获取长度
			System.out.println("length " + length);
			if (length != -1) {
				byte[] data = new byte[length];
				byte[] temp = new byte[512];
				int readLen = 0;
				int destPos = 0;
				while ((readLen = is.read(temp)) > 0) {
					System.arraycopy(temp, 0, data, destPos, readLen);
					destPos += readLen;
				}
				String resul = new String(data, "UTF-8"); // utf-8编码
				return resul;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null){
				conn.disconnect();
			}
		}

		return param;
	}
	
	public static String sendPost(String url, String param) {
		try {
			
			realUrl = new URL(url);
			conn = (HttpURLConnection) realUrl.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setInstanceFollowRedirects(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
			conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded"); // 设置发送数据的格式
			conn.connect();
			OutputStream outt = conn.getOutputStream(); // utf-8编码
			
		//为群发修改，其他接口问题之后调试
			outt.write((param).getBytes("UTF-8"));
//			outt.write(parser.parse(param).toString().getBytes("UTF-8"));
//			System.out.println("param: " +parser.parse(param));
			outt.flush();
			outt.close();
			
			System.out.println("----------------conn--------------------" );

			int code = conn.getResponseCode();
			System.out.println("code " + code);
			System.out.println("resmessage " + conn.getResponseMessage());
			InputStream is = null;
			if (code == 200) {
				is = conn.getInputStream();
			} else {
				is = conn.getErrorStream();
			}

			// 读取响应
			int length = (int) conn.getContentLength();// 获取长度
			System.out.println("length " + length);
			if (length != -1) {
				byte[] data = new byte[length];
				byte[] temp = new byte[512];
				int readLen = 0;
				int destPos = 0;
				while ((readLen = is.read(temp)) > 0) {
					System.arraycopy(temp, 0, data, destPos, readLen);
					destPos += readLen;
				}
				String resul = new String(data, "UTF-8"); // utf-8编码
				return resul;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null){
				conn.disconnect();
			}
		}

		return param;
	}
	
	
	public static String sendGet(String url, String param) {
		
		try {
			realUrl = new URL(url);
			conn = (HttpURLConnection) realUrl.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setInstanceFollowRedirects(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
			conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded"); // 设置发送数据的格式
			conn.connect();
//			OutputStream outt = conn.getOutputStream(); // utf-8编码
//			outt.write(parser.parse(param).toString().getBytes("UTF-8"));
//			System.out.println("param" +parser.parse(param));
			//outt.append((CharSequence) parser.parse(param).toString());
			//outt.flush();
//			outt.close();
			
			System.out.println("----------------conn--------------------" );

			int code = conn.getResponseCode();
			System.out.println("code " + code);
			System.out.println("resmessage " + conn.getResponseMessage());
			InputStream is = null;
			if (code == 200) {
				is = conn.getInputStream();
			} else {
				is = conn.getErrorStream();
			}

			// 读取响应
			//int length = (int) conn.getContentLength();// 获取长度
			int length = (int) conn.getContentLength();// 获取长度
			System.out.println("length " + length);
			if (length != -1) {
				byte[] data = new byte[length];
				byte[] temp = new byte[512];
				int readLen = 0;
				int destPos = 0;
				while ((readLen = is.read(temp)) > 0) {
					System.arraycopy(temp, 0, data, destPos, readLen);
					destPos += readLen;
				}
				String resul = new String(data, "UTF-8"); // utf-8编码
				return resul;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null){
				conn.disconnect();
			}
		}

		return param;
	}
	
	public static String sendGetImage(String url, String param) {
		try {
			
			realUrl = new URL(url);
			conn = (HttpURLConnection) realUrl.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setInstanceFollowRedirects(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
			conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded"); // 设置发送数据的格式
			conn.connect();

			System.out.println("----------------conn--------------------" );

			int code = conn.getResponseCode();
			System.out.println("code " + code);
			System.out.println("resmessage " + conn.getResponseMessage());
			InputStream inStream = null;
			if (code == 200) {
				inStream = conn.getInputStream();
			} else {
				inStream = conn.getErrorStream();
			}

			// 读取响应
			int length = (int) conn.getContentLength();// 获取长度
			System.out.println("length " + length);
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
	        //创建一个Buffer字符串  
	        byte[] buffer = new byte[1024];  
	        //每次读取的字符串长度，如果为-1，代表全部读取完毕  
	        int len = 0;  
	        //使用一个输入流从buffer里把数据读取出来  
	        while( (len=inStream.read(buffer)) != -1 ){  
	            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度  
	            outStream.write(buffer, 0, len);  
	        }  
	        //关闭输入流  
	        inStream.close();
			byte[] data = outStream.toByteArray();  
			File imageFile = new File("D:\\BeautyGirl.jpg");  
	        //创建输出流  
	        FileOutputStream foutStream = new FileOutputStream(imageFile);  
	        //写入数据  
	        foutStream.write(data);  
	        //关闭输出流  
	        foutStream.close();  
	        conn.disconnect();
//			if (length != -1) {
//				byte[] data = new byte[length];
//				byte[] temp = new byte[512];
//				int readLen = 0;
//				int destPos = 0;
//				while ((readLen = is.read(temp)) > 0) {
//					System.arraycopy(temp, 0, data, destPos, readLen);
//					destPos += readLen;
//				}
//				String resul = new String(data, "UTF-8"); // utf-8编码
//				return resul;
//			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null){
				conn.disconnect();
			}
		}

		return "getImage success";
	}
	
	/**
	 * 上传临时素材
	 * @param url    上传接口地址
	 * @param imgUrl 图片本地地址
	 * @return
	 */
	public static String uploadTempMaterial(String url, String imgUrl){
		File file = new File(imgUrl);
		OutputStream output = null;  
	    DataInputStream input = null;  
		try {
			if(!file.isFile()|| !file.exists()){  
	             throw new IOException("file is not exist");  
	        }  
			
			realUrl = new URL(url);
			conn = (HttpURLConnection) realUrl.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setInstanceFollowRedirects(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Charset", "UTF-8");
			
			String boundary = "----------" + System.currentTimeMillis(); 
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary); 
			
			output = new DataOutputStream(conn.getOutputStream());
			 StringBuilder sb = new StringBuilder();
	         sb.append("--"); // 必须多两道线
	         sb.append(boundary);
	         sb.append("\r\n");
	         sb.append("Content-Disposition: form-data;name=\"media\";filelength=\"" + file.length()
	                    + "\";filename=\""+ file.getName() + "\"\r\n");
	         sb.append("Content-Type:application/octet-stream\r\n\r\n");
	         byte[] head = sb.toString().getBytes("utf-8");
	            // 获得输出流
	         output = new DataOutputStream(conn.getOutputStream()); 
	         output.write(head);
	        // 文件正文部分  
	        // 把文件已流文件的方式 推入到url中  
	        input = new DataInputStream(new FileInputStream(file));  
	        int bytes = 0;
            byte[] bufferOut = new byte[1024];
            while ((bytes = input.read(bufferOut)) != -1) {
                output.write(bufferOut, 0, bytes);
            }
            input.close();
            
	        // 结尾部分
            byte[] foot = ("\r\n--" + boundary + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
            output.write(foot);
			
			conn.connect();
			//outt.append((CharSequence) parser.parse(param).toString());
			output.flush();

			output.close();

			int code = conn.getResponseCode();
			System.out.println("code " + code);
			System.out.println("resmessage " + conn.getResponseMessage());
			InputStream is = null;
			if (code == 200) {
				is = conn.getInputStream();
			} else {
				is = conn.getErrorStream();
			}

			// 读取响应
			int length = (int) conn.getContentLength();// 获取长度
			System.out.println("length " + length);
			if (length != -1) {
				byte[] data = new byte[length];
				byte[] temp = new byte[512];
				int readLen = 0;
				int destPos = 0;
				while ((readLen = is.read(temp)) > 0) {
					System.arraycopy(temp, 0, data, destPos, readLen);
					destPos += readLen;
				}
				String resul = new String(data, "UTF-8"); // utf-8编码
				return resul;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "uploadTempMaterial error";
		} finally {
			if(conn != null){
				conn.disconnect();
			}
		}
		return "uploadTempMaterial null";
	}
}
