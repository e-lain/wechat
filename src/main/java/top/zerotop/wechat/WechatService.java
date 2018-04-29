package top.zerotop.wechat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import top.zerotop.domain.ImageMessage;
import top.zerotop.domain.Media;
import top.zerotop.domain.NewsMessage;
import top.zerotop.domain.TextMessage;
import top.zerotop.domain.VoiceMessage;
import top.zerotop.domain.material.ArticleItem;
import top.zerotop.domain.material.Material;
import top.zerotop.wechat.constrant.MessageTypeConstrant;
import top.zerotop.wechat.manager.MediaManager;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import javax.servlet.http.HttpServletRequest;

public class WechatService {

	private static Gson gson = new Gson();
	
	Map<String, String> map = new HashMap<String, String>();
	XStream xstream = new XStream(new DomDriver());
	static final String defaultResponseMessage = "<xml><ToUserName>oXDH6wwHpgkMzThJxdBvTr0FGrkk</ToUserName>"
				+ "<FromUserName>gh_3f50e140f148</FromUserName>" 
				+ "<CreateTime>" + System.currentTimeMillis()+ "</CreateTime>"
				+ "<MsgType>text</MsgType>"
				+ "<Content>收到消息：默认处理</Content>" 
				+ "</xml>";
	String responseMes;

	public String processRequest(HttpServletRequest request) {
		responseMes = "";
		InputStream ins = null;
		try {
			ins = request.getInputStream();
		} catch (IOException e1) {
			System.out.println("In WechatService [processRequest] ins exception ------ ");
			e1.printStackTrace();
		}

		Document doc = null;
		try {
			StringBuffer content = new StringBuffer();
			BufferedReader br = new BufferedReader(new InputStreamReader(ins, "UTF-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				content.append(line + "\n");
			}
			br.close();
			doc = DocumentHelper.parseText(content.toString());
//			System.out.println("doc ----- \n" + doc.asXML().toString());
		} catch (Exception e1) {
			System.out.println("In WechatService [processRequest] doc exception ------ ");
			e1.printStackTrace();
		}
		try {
			Element root = doc.getRootElement();
//			System.out.println("\nroot --- \n" + root.asXML().toString());
			System.out.println("root --- ");
			@SuppressWarnings("unchecked")
			List<Element> list = root.elements();
			for (Element e : list) {
//				System.out.println("--- " + e.asXML().toString());
				map.put(e.getName(), e.getText());
			}
		} catch (Exception e) {
			System.out.println("In WechatService [processRequest] exception ------ ");
			e.printStackTrace();
		}
		try {
			ins.close();
		} catch (IOException e1) {
			System.out.println("In WechatService [processRequest] exception ------ ");
			e1.printStackTrace();
		}
		try {
			// map = WechatMessageUtil.xmlToMap(request);
			System.out.println("-------------------  map  -------------------- ");
			for (String str : map.keySet()) {
				System.out.println(str + " : " + map.get(str));
			}
			System.out.println("-------- map   ");
		} catch (Exception e) {
			System.out.println("In WechatService [processRequest] exception ------ ");
			e.printStackTrace();
		}

		// 发送信息内容
		String fromUserName = map.get("FromUserName");
		String toUserName = map.get("ToUserName");
		String msgType = map.get("MsgType");

		// 对消息进行处理
		// 文本消息
		if (MessageTypeConstrant.MESSAGE_TEXT.equals(msgType)) {
			System.out.println(" Text message ------ ");
			TextMessage textMessage = new TextMessage();
			textMessage.setMsgType(msgType);
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(System.currentTimeMillis());
			textMessage.setContent("收到消息: " + map.get("Content"));

			try {
				xstream.alias("xml", textMessage.getClass());
				responseMes = xstream.toXML(textMessage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 图片消息
		if (MessageTypeConstrant.MESSAGE_IMAGE.equals(msgType)) {
			System.out.println(" Image message ------ ");
			ImageMessage imageMessage = new ImageMessage();
			imageMessage.setToUserName(fromUserName);
			imageMessage.setFromUserName(toUserName);
			imageMessage.setCreateTime(System.currentTimeMillis());
			Media image = new Media(map.get("MediaId"));
			imageMessage.setImage(image);
			imageMessage.setMsgType(msgType);

			try {
				xstream.alias("xml", imageMessage.getClass());
				responseMes = xstream.toXML(imageMessage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 语音消息
		if (MessageTypeConstrant.MESSAGE_VOICE.equals(msgType)) {
			System.out.println(" Voice message  ------ ");
			VoiceMessage voiceMessage = new VoiceMessage();
			voiceMessage.setToUserName(fromUserName);
			voiceMessage.setFromUserName(toUserName);
			voiceMessage.setCreateTime(System.currentTimeMillis());
			Media voice = new Media(map.get("MediaId"));
			voiceMessage.setVoice(voice);
			voiceMessage.setMsgType(msgType);
			voiceMessage.setRecognition(map.get("Recognition"));

			try {
				xstream.alias("xml", voiceMessage.getClass());
				responseMes = xstream.toXML(voiceMessage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		//点击事件
		if (MessageTypeConstrant.MESSAGE_EVENT.equals(msgType)) {
			System.out.println(" click message ------ ");
			ImageMessage imageMessage = new ImageMessage();
			imageMessage.setToUserName(fromUserName);
			imageMessage.setFromUserName(toUserName);
			imageMessage.setCreateTime(System.currentTimeMillis());
			Media image = new Media("MediaId");
			imageMessage.setImage(image);
			imageMessage.setMsgType(msgType);

			try {
				xstream.alias("xml", imageMessage.getClass());
				responseMes = xstream.toXML(imageMessage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//接收用户位置信息
		if(MessageTypeConstrant.MESSAGE_EVENT.equals(msgType)&&MessageTypeConstrant.MESSAGE_LOCATION.equals(map.get("Event"))){
			System.out.println(" locationevent message ------ ");
			TextMessage textMessage = new TextMessage();
			textMessage.setMsgType(msgType);
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(System.currentTimeMillis());
			textMessage.setContent("维度:"+map.get("Latitude")+"  经度:"+map.get("Longitude")+"  精度:"+map.get("Precision"));

			try {
				xstream.alias("xml", textMessage.getClass());
				responseMes = xstream.toXML(textMessage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//菜单栏点击
		if(MessageTypeConstrant.MESSAGE_EVENT.equals(msgType)
				&&MessageTypeConstrant.MESSAGE_EVENT_CLICK.equals(map.get("Event"))
				&&"clickme".equals(map.get("EventKey"))){
			
						
			String data = "{"
					+"\"type\":\"news\","
					+"\"offset\":0,"
					+"\"count\":5"
					+"}";
			
			
			NewsMessage newMessage = new NewsMessage();
			newMessage.setToUserName(toUserName);
			newMessage.setFromUserName(fromUserName);
			newMessage.setCreateTime(System.currentTimeMillis());
			newMessage.setArticleCount(2);
			try{
				List<ArticleItem> itemList = 
						MediaManager.getMediaFile(TokenThread.accessToken.getAccessToken(), data);
				newMessage.setArticles(itemList);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			try {
				xstream.alias("xml", newMessage.getClass());
				responseMes = xstream.toXML(newMessage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		if(responseMes.equals("")){
			responseMes = defaultResponseMessage;
		}
		System.out.println("-------- res  ");	
		System.out.println(" res: \n" + responseMes);
		System.out.println("-------------------  res  -------------------- ");
		
		return responseMes;
	}
}
