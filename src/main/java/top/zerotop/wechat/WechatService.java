package top.zerotop.wechat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import org.dom4j.DocumentException;
import top.zerotop.domain.AccessToken;
import top.zerotop.domain.ImageMessage;
import top.zerotop.domain.Media;
import top.zerotop.domain.NewsMessage;
import top.zerotop.domain.TextMessage;
import top.zerotop.domain.VoiceMessage;
import top.zerotop.domain.material.ArticleItem;
import top.zerotop.wechat.constrant.MessageTypeConstrant;
import top.zerotop.wechat.manager.MediaManager;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import javax.servlet.http.HttpServletRequest;

public class WechatService {

    private Map<String, String> map = new HashMap<>();
    private XStream xstream = new XStream(new DomDriver());
    private String responseMes;

    public String processRequest(HttpServletRequest request) {
        responseMes = "";
        InputStream inputStream = null;

        try {
            inputStream = request.getInputStream();
        } catch (IOException e1) {
            System.out.println("In WechatService [processRequest] get inputStream exception ");
        }

        Document doc = null;
        StringBuffer content = new StringBuffer();
        String line = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while (null != (line = bufferedReader.readLine())) {
                content.append(line + "\n");
            }
            bufferedReader.close();
            inputStream.close();
            doc = DocumentHelper.parseText(content.toString());
        } catch (IOException e2) {
            System.out.println("In WechatService [processRequest] close inputStream exception ");
        } catch (DocumentException e) {
            System.out.println("In WechatService [processRequest] close inputStream exception ");
        }


        try {
            Element root = doc.getRootElement();
            List<Element> list = root.elements();
            for (Element e : list) {
                map.put(e.getName(), e.getText());
            }
        } catch (Exception e) {
            System.out.println("In WechatService [processRequest] get root exception ");
        }

        responseMes = createResponseMessage();

        return responseMes;
    }

    String createResponseMessage() {
        // 发送信息内容
        String fromUserName = map.get("FromUserName");
        String toUserName = map.get("ToUserName");
        String msgType = map.get("MsgType");

        // 对消息进行处理
        switch (msgType) {
            case MessageTypeConstrant.MESSAGE_TEXT:
                // 文本消息
                System.out.println("======== Text message ======= ");
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
            case MessageTypeConstrant.MESSAGE_IMAGE:
                // 图片消息
                System.out.println(" ======= Image message ======= ");
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
            case MessageTypeConstrant.MESSAGE_VOICE:
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

                try {
                    xstream.alias("xml", voiceMessage.getClass());
                    responseMes = xstream.toXML(voiceMessage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            case MessageTypeConstrant.MESSAGE_EVENT:
                //点击事件
                System.out.println(" ======= click message ======= ");
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

        if (MessageTypeConstrant.MESSAGE_EVENT.equals(msgType) && MessageTypeConstrant.MESSAGE_EVENT_SUBSCRIBE.equals(map.get("Event"))) {
            System.out.println(" ======= Text message ======= ");
            TextMessage textMessage = new TextMessage();
            textMessage.setMsgType(msgType);
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(System.currentTimeMillis());
            textMessage.setContent("感谢您的关注");

            try {
                xstream.alias("xml", textMessage.getClass());
                responseMes = xstream.toXML(textMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //接收用户位置信息
        if (MessageTypeConstrant.MESSAGE_EVENT.equals(msgType) && MessageTypeConstrant.MESSAGE_LOCATION.equals(map.get("Event"))) {
            System.out.println(" ======= locationevent message ======= ");
            TextMessage textMessage = new TextMessage();
            textMessage.setMsgType(msgType);
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(System.currentTimeMillis());
            textMessage.setContent("维度:" + map.get("Latitude") + "  经度:" + map.get("Longitude") + "  精度:" + map.get("Precision"));

            try {
                xstream.alias("xml", textMessage.getClass());
                responseMes = xstream.toXML(textMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //菜单栏点击
        if (MessageTypeConstrant.MESSAGE_EVENT.equals(msgType)
                && MessageTypeConstrant.MESSAGE_EVENT_CLICK.equals(map.get("Event"))
                && "clickme".equals(map.get("EventKey"))) {
            System.out.println(" ======= clickme event ======= ");

            String data = "{"
                    + "\"type\":\"news\","
                    + "\"offset\":0,"
                    + "\"count\":5"
                    + "}";


            NewsMessage newMessage = new NewsMessage();
            newMessage.setToUserName(fromUserName);
            newMessage.setFromUserName(toUserName);
            newMessage.setCreateTime(System.currentTimeMillis());
            newMessage.setMsgType("news");
            try {
                List<ArticleItem> itemList =
                        MediaManager.batchgetMaterial(AccessToken.getAccessToken(), data);
                newMessage.setArticles(itemList);
                newMessage.setArticleCount(itemList.size());

                System.out.println(" ======= get itemlist ======= ");
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                xstream.alias("xml", newMessage.getClass());
                responseMes = xstream.toXML(newMessage);
                responseMes = responseMes.replaceAll("top.zerotop.domain.material.ArticleItem", "item");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        if (responseMes.equals("")) {
            responseMes = "<xml><ToUserName>" + fromUserName + "</ToUserName>"
                    + "<FromUserName>" + toUserName + "</FromUserName>"
                    + "<CreateTime>" + System.currentTimeMillis() + "</CreateTime>"
                    + "<MsgType>text</MsgType>"
                    + "<Content>收到消息：默认处理</Content>"
                    + "</xml>";
        }
        System.out.println(" ======= res  ");
        System.out.println(" res: \n" + responseMes);
        System.out.println(" ======= res ======= ");

        return responseMes;
    }
}
