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
import top.zerotop.domain.message.NewsMessage;
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

                EventHandler.textEvent(map, fromUserName, toUserName, msgType);

            case MessageTypeConstrant.MESSAGE_IMAGE:

                EventHandler.imgEvent(map, fromUserName, toUserName, msgType);

            case MessageTypeConstrant.MESSAGE_VOICE:

                EventHandler.voiceEvent(map, fromUserName, toUserName, msgType);

            case MessageTypeConstrant.MESSAGE_EVENT:
                EventHandler.mssageEvent(map, fromUserName, toUserName, msgType);
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
