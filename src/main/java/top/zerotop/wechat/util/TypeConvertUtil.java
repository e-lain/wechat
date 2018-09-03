package top.zerotop.wechat.util;

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

import top.zerotop.domain.TextMessage;

public class TypeConvertUtil {

	/**
	 * Xml 转化为 Map
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, String> xmlToMap(HttpServletRequest request) {

		System.out.println("In WechatMessageutil [xmlToMap] ------ ");
		Map<String, String> map = new HashMap<String, String>();
		InputStream ins = null;
		try {
			ins = request.getInputStream();
		} catch (IOException e1) {
			System.out.println("In WechatMessageutil [xmlToMap] exception------ ");
			e1.printStackTrace();
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
			
//			System.out.println("doc ---- " + doc.asXML().toString());
		} catch (Exception e1) {
			System.out.println("In WechatMessageutil [xmlToMap] exception ------ "+e1.getMessage());
			e1.printStackTrace();
		}
		try {
			Element root = doc.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> list = root.elements();
			for (Element e : list) {
				map.put(e.getName(), e.getText());
			}
		} catch (Exception e) {
			System.out.println("In WechatMessageutil [xmlToMap] exception ------ ");
			e.printStackTrace();
		}
		try {
			ins.close();
		} catch (IOException e1) {
			System.out.println("In WechatMessageutil [xmlToMap] exception ------ ");
			e1.printStackTrace();
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
