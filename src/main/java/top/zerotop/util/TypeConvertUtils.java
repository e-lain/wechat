package top.zerotop.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.zerotop.domain.message.TextMessage;

public class TypeConvertUtils {
	private static final Logger logger = LoggerFactory.getLogger(TypeConvertUtils.class);

	/**
	 * Xml 转化为 Map
	 * @param request
	 * @return
	 */
	public static Map<String, String> xmlToMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<>();
		InputStream ins = null;
		Document doc = null;
		StringBuilder content = new StringBuilder();
		try {
			ins = request.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(ins, StandardCharsets.UTF_8));
			String line = null;
			while ((line = br.readLine()) != null) {
				content.append(line).append("\n");
			}
			br.close();
			doc = DocumentHelper.parseText(content.toString());
			if (doc == null) {
				return map;
			}
		} catch (Exception e1) {
			logger.info(e1.getMessage());
		}
		try {
			Element root = doc.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> list = root.elements();
			for (Element e : list) {
				map.put(e.getName(), e.getText());
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		} finally {
			if (ins != null) {
				try {
					ins.close();
				} catch (IOException e1) {
					logger.info(e1.getMessage());
				}
			}
		}
		return map;
	}

	/**
	 * 将testMessage 转为Xml 
	 * 
	 * @param textMessage
	 * @return
	 */
	public static String textMessageToXml(TextMessage textMessage) {
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}
}
