package top.zerotop.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

public class JsonUtils {
	private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

	private  static ObjectMapper objectMapper = new ObjectMapper();

	public static ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		objectMapper.findAndRegisterModules();
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		return objectMapper;
	}

	public static <T> T fromJson(String json, Class<T> clazz) {
		try {
			return objectMapper().readValue(json, clazz);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			logger.info("convert to object failed...");
			return null;
		}
	}

	public static String toJsonString(HttpServletRequest req){
		
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
