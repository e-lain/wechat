package top.zerotop.wechat;

import com.alibaba.fastjson.JSON;
import com.sun.javafx.binding.StringFormatter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import top.zerotop.domain.AccessToken;
import top.zerotop.domain.material.ArticleItem;
import top.zerotop.domain.message.*;
import top.zerotop.domain.Media;
import top.zerotop.global.constrant.MessageTypeConstrant;
import top.zerotop.wechat.manager.MediaManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EventHandler {
    private final static Logger logger = LoggerFactory.getLogger(EventHandler.class);

    private static XStream xstream = new XStream(new DomDriver());
    private static String responseMsg;

    // 文本消息
    public static String textEvent(Map<String, String> map, String fromUserName, String toUserName) {
        logger.info("========> Text message");
        TextMessage textMessage = new TextMessage(fromUserName, toUserName);
        textMessage.setContent("收到消息: " + map.get("Content"));

        return makeXML(textMessage);
    }

    // 图片消息
    public static String imgEvent(Map<String, String> map, String fromUserName, String toUserName) {
        logger.info(" =======> Image message");
        ImageMessage imageMessage = new ImageMessage(fromUserName, toUserName);
        imageMessage.setImage(new Media(map.get("MediaId")));

        return makeXML(imageMessage);
    }

    // 语音消息
    public static String voiceEvent(Map<String, String> map, String fromUserName, String toUserName) {
        logger.info(" =======> Voice message");
        VoiceMessage voiceMessage = new VoiceMessage(fromUserName, toUserName);
        voiceMessage.setVoice(new Media(map.get("MediaId")));
        voiceMessage.setRecognition(map.get("Recognition"));

        return makeXML(voiceMessage);
    }

    public static String mssageEvent(Map<String, String> map, String fromUserName, String toUserName) {
        TextMessage textMessage = new TextMessage(fromUserName, toUserName);
        switch (map.get("Event")) {
            case MessageTypeConstrant.MESSAGE_EVENT_SUBSCRIBE:
                logger.info(" =======> subscribe message");
                textMessage.setContent("感谢您的关注");
                responseMsg = makeXML(textMessage);
                break;
            case MessageTypeConstrant.MESSAGE_LOCATION:
                logger.info(" =======> location event message");
                String content = StringFormatter.format("维度: %s, 经度: %s, 精度:%s.", map.get("Latitude"), map.get("Longitude"), map.get("Precision")).toString();
                textMessage.setContent(content);
                responseMsg = makeXML(textMessage);
                break;
            case MessageTypeConstrant.MESSAGE_EVENT_CLICK:
                responseMsg = clickEventHandler(map, fromUserName, toUserName);
                break;
        }
        return responseMsg;
    }

    private static String clickEventHandler(Map<String, String> map, String fromUserName, String toUserName) {
        logger.info(" =======> clickme event");
        //菜单栏点击
        switch (map.get("EventKey")) {
            case "clickme":
                Map<String, String> tempMap = new HashMap<>();
                tempMap.put("type", "news");
                tempMap.put("offset", "0");
                tempMap.put("count", "5");

                NewsMessage newMessage = new NewsMessage(fromUserName, toUserName);
                try {
                    List<ArticleItem> itemList =
                            MediaManager.batchgetMaterial(AccessToken.getAccessToken(), JSON.toJSONString(tempMap));
                    newMessage.setArticles(itemList);
                    newMessage.setArticleCount(itemList.size());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                responseMsg = makeXML(newMessage).replaceAll("top.zerotop.domain.material.ArticleItem", "item");
                break;
            default:
                logger.info(" =======> click message");
//            ImageMessage imageMessage = new ImageMessage(fromUserName, toUserName);
//            Media image = new Media("MediaId");
//            imageMessage.setImage(image);
                TextMessage textMessage = new TextMessage(fromUserName, toUserName);
                textMessage.setContent("获取click事件" + map.get("EventKey"));
                responseMsg = makeXML(textMessage);
        }

        return responseMsg;
    }

    private static String makeXML(BaseMessage message) {
        try {
            xstream.alias("xml", message.getClass());
            return xstream.toXML(message);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
