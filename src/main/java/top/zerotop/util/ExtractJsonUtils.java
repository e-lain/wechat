package top.zerotop.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

public class ExtractJsonUtils {

	public static String extractJson(HttpServletRequest req){
		
		StringBuffer jsonString = new java.lang.StringBuffer();
		try {
			InputStream in = req.getInputStream();
			BufferedInputStream buf = new BufferedInputStream(in);
			byte[] buffer = new byte[1024];
			int iRead;
	
			while ((iRead = buf.read(buffer)) != -1) {
				jsonString.append(new String(buffer, 0, iRead, "utf-8"));
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return jsonString.toString();
	}
}
