package top.zerotop.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

public class TypeConvertUtil {
	private static final Logger logger = LoggerFactory.getLogger(TypeConvertUtil.class);

	/**
	 * Xml 转化为 Map
	 * @param request
	 * @return
	 */
	public static Map<String, String> xmlToMap(HttpServletRequest request) {

		logger.info(" now begin convert ");
		Map<String, String> map = new HashMap<String, String>();
		InputStream ins = null;
		try {
			ins = request.getInputStream();
		} catch (IOException e1) {
			logger.info("e1");
		}

		Document doc = null;
		StringBuffer content = new StringBuffer();
		String line = null;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(ins, "UTF-8"));
			while ((line = br.readLine()) != null) {
				content.append(line + "\n");
			}
			br.close();
			doc = DocumentHelper.parseText(content.toString());
			
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
		}
		try {
			ins.close();
		} catch (IOException e1) {
			logger.info(e1.getMessage());
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
