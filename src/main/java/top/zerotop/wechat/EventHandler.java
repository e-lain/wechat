package top.zerotop.wechat;

import com.alibaba.fastjson.JSON;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.zerotop.domain.AccessToken;
import top.zerotop.domain.material.ArticleItem;
import top.zerotop.domain.message.*;
import top.zerotop.domain.Media;
import top.zerotop.global.constrant.MessageTypeConstrant;
import top.zerotop.wechat.manager.MediaManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventHandler {
    private static Logger logger = LoggerFactory.getLogger(EventHandler.class);

    private static XStream xstream = new XStream(new DomDriver());
    private static String responseMsg;

    public static String textEvent(Map<String, String> map, String fromUserName, String toUserName, String msgType) {
        System.out.println("textEvent===");
        // 文本消息
        logger.info("======== Text message ======= ");
        TextMessage textMessage = new TextMessage();
        textMessage.setMsgType(msgType);
        textMessage.setToUserName(fromUserName);
        textMessage.setFromUserName(toUserName);
        textMessage.setCreateTime(System.currentTimeMillis());
        textMessage.setContent("收到消息: " + map.get("Content"));

        responseMsg =  makeXML(textMessage);
        System.out.println(responseMsg);

        return responseMsg;
    }

    public static String imgEvent(Map<String, String> map, String fromUserName, String toUserName, String msgType) {
        System.out.println("imgEvent===");
        // 图片消息
        logger.info(" ======= Image message ======= ");
        ImageMessage imageMessage = new ImageMessage();
        imageMessage.setToUserName(fromUserName);
        imageMessage.setFromUserName(toUserName);
        imageMessage.setCreateTime(System.currentTimeMillis());
        Media image = new Media(map.get("MediaId"));
        imageMessage.setImage(image);
        imageMessage.setMsgType(msgType);

        responseMsg =  makeXML(imageMessage);
        return responseMsg;
    }

    public static String voiceEvent(Map<String, String> map, String fromUserName, String toUserName, String msgType) {
        System.out.println("voiceEvent===");
        // 语音消息
        logger.info(" ======= Voice message ======= ");
        VoiceMessage voiceMessage = new VoiceMessage();
        voiceMessage.setToUserName(fromUserName);
        voiceMessage.setFromUserName(toUserName);
        voiceMessage.setCreateTime(System.currentTimeMillis());
        Media voice = new Media(map.get("MediaId"));
        voiceMessage.setVoice(voice);
        voiceMessage.setMsgType(msgType);
        voiceMessage.setRecognition(map.get("Recognition"));

        responseMsg =  makeXML(voiceMessage);
        return responseMsg;
    }



    public static String mssageEvent(Map<String, String> map, String fromUserName, String toUserName, String msgType) {
        System.out.println("mssageEvent===");
        TextMessage textMessage = new TextMessage();
        switch (map.get("Event")) {
            case MessageTypeConstrant.MESSAGE_EVENT_SUBSCRIBE :
                logger.info(" ======= Text message ======= ");
                textMessage.setMsgType(msgType);
                textMessage.setToUserName(fromUserName);
                textMessage.setFromUserName(toUserName);
                textMessage.setCreateTime(System.currentTimeMillis());
                textMessage.setContent("感谢您的关注");
                responseMsg =  makeXML(textMessage);
                break;

            case MessageTypeConstrant.MESSAGE_LOCATION :
                logger.info(" ======= locationevent message ======= ");
                textMessage = new TextMessage();
                textMessage.setMsgType(msgType);
                textMessage.setToUserName(fromUserName);
                textMessage.setFromUserName(toUserName);
                textMessage.setCreateTime(System.currentTimeMillis());
                textMessage.setContent("维度:" + map.get("Latitude") + "  经度:" + map.get("Longitude") + "  精度:" + map.get("Precision"));
                responseMsg =  makeXML(textMessage);
                break;

            case MessageTypeConstrant.MESSAGE_EVENT_CLICK :
                responseMsg =  clickEventHandler(map, fromUserName, toUserName, msgType);
                break;
        }

        return responseMsg;
    }

    private static String clickEventHandler(Map<String, String> map, String fromUserName, String toUserName, String msgType) {
        System.out.println("clickEventHandler===");
        //菜单栏点击
        if ("clickme".equals(map.get("EventKey"))) {
            logger.info(" ======= clickme event ======= ");
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("type","news");
            tempMap.put("offset","0");
            tempMap.put("count","5");


            NewsMessage newMessage = new NewsMessage();
            newMessage.setToUserName(fromUserName);
            newMessage.setFromUserName(toUserName);
            newMessage.setCreateTime(System.currentTimeMillis());
            newMessage.setMsgType("news");
            try {
                List<ArticleItem> itemList =
                        MediaManager.batchgetMaterial(AccessToken.getAccessToken(), JSON.toJSONString(map));
                newMessage.setArticles(itemList);
                newMessage.setArticleCount(itemList.size());
            } catch (Exception e) {
                e.printStackTrace();
            }
            responseMsg = makeXML(newMessage);
            responseMsg = responseMsg.replaceAll("top.zerotop.domain.material.ArticleItem", "item");
        }

        else {
            logger.info(" ======= click message ======= ");
            ImageMessage imageMessage = new ImageMessage();
            imageMessage.setToUserName(fromUserName);
            imageMessage.setFromUserName(toUserName);
            imageMessage.setCreateTime(System.currentTimeMillis());
            Media image = new Media("MediaId");
            imageMessage.setImage(image);
            imageMessage.setMsgType(msgType);

            responseMsg = makeXML(imageMessage);
        }
        return responseMsg;
    }

    private static String makeXML(BaseMessage message) {
        try {
            xstream.alias("xml", message.getClass());
            responseMsg = xstream.toXML(message);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
        }
        return responseMsg;
    }
}
