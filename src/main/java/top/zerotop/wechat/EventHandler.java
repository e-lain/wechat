package top.zerotop.wechat;

import com.alibaba.fastjson.JSON;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import top.zerotop.domain.AccessToken;
import top.zerotop.domain.material.ArticleItem;
import top.zerotop.domain.message.*;
import top.zerotop.domain.Media;
import top.zerotop.wechat.constrant.MessageTypeConstrant;
import top.zerotop.wechat.manager.MediaManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventHandler {

    private static XStream xstream = new XStream(new DomDriver());
    private static String responseMsg;

    public static String textEvent(Map<String, String> map, String fromUserName, String toUserName, String msgType) {
        // 文本消息
        System.out.println("======== Text message ======= ");
        TextMessage textMessage = new TextMessage();
        textMessage.setMsgType(msgType);
        textMessage.setToUserName(fromUserName);
        textMessage.setFromUserName(toUserName);
        textMessage.setCreateTime(System.currentTimeMillis());
        textMessage.setContent("收到消息: " + map.get("Content"));

        responseMsg =  makeXML(textMessage);
        return responseMsg;
    }

    public static String imgEvent(Map<String, String> map, String fromUserName, String toUserName, String msgType) {
        // 图片消息
        System.out.println(" ======= Image message ======= ");
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
        // 语音消息
        System.out.println(" ======= Voice message ======= ");
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

        switch (map.get("Event")) {
            case MessageTypeConstrant.MESSAGE_EVENT_SUBSCRIBE :
                System.out.println(" ======= Text message ======= ");
                TextMessage textMessage = new TextMessage();
                textMessage.setMsgType(msgType);
                textMessage.setToUserName(fromUserName);
                textMessage.setFromUserName(toUserName);
                textMessage.setCreateTime(System.currentTimeMillis());
                textMessage.setContent("感谢您的关注");

                responseMsg =  makeXML(textMessage);

            case MessageTypeConstrant.MESSAGE_LOCATION :
                System.out.println(" ======= locationevent message ======= ");
                textMessage = new TextMessage();
                textMessage.setMsgType(msgType);
                textMessage.setToUserName(fromUserName);
                textMessage.setFromUserName(toUserName);
                textMessage.setCreateTime(System.currentTimeMillis());
                textMessage.setContent("维度:" + map.get("Latitude") + "  经度:" + map.get("Longitude") + "  精度:" + map.get("Precision"));

                responseMsg =  makeXML(textMessage);

            case MessageTypeConstrant.MESSAGE_EVENT_CLICK :

                responseMsg =  clickEventHandler(map, fromUserName, toUserName, msgType);
        }

        return responseMsg;
    }

    private static String clickEventHandler(Map<String, String> map, String fromUserName, String toUserName, String msgType) {


        //菜单栏点击
        if ("clickme".equals(map.get("EventKey"))) {
            System.out.println(" ======= clickme event ======= ");
            
            Map<String, String> map = new HashMap<>();
            map.put("type","news");
            map.put("offset","0");
            map.put("count","5");


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

            System.out.println(" ======= click message ======= ");
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
