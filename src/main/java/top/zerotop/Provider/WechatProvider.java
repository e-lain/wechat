package top.zerotop.Provider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.sun.javafx.binding.StringFormatter;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import top.zerotop.wechat.EventHandler;
import top.zerotop.global.constrant.MessageTypeConstrant;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import javax.servlet.http.HttpServletRequest;

public class WechatProvider {
    private static Logger logger = LoggerFactory.getLogger(WechatProvider.class);

    private Map<String, String> map = new ConcurrentHashMap<>();
    private String responseMes;

    public String processRequest(HttpServletRequest request) {
        responseMes = "";
        Document doc = null;
        try {
            InputStream inputStream = request.getInputStream();
            String line;
            StringBuilder content = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while (null != (line = bufferedReader.readLine())) {
                content.append(line).append("\n");
            }
            bufferedReader.close();
            inputStream.close();
            doc = DocumentHelper.parseText(content.toString());
            if (doc == null) {
                return responseMes;
            }
        } catch (IOException | DocumentException e) {
            logger.info(e.getMessage());
            e.printStackTrace();
            return responseMes;
        }

        try {
            map.clear();
            Element root = doc.getRootElement();
            if (root != null) {
                List<Element> list = root.elements();
                if (CollectionUtils.isEmpty(list)) {
                    list.forEach(e -> map.put(e.getName(), e.getText()));
                    responseMes = createResponseMessage(map);
                }
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return responseMes;
    }

    private String createResponseMessage(Map<String, String> map) {
        // 发送信息内容
        String fromUserName = map.get("FromUserName");
        String toUserName = map.get("ToUserName");
        String msgType = map.get("MsgType");

        // 对消息进行处理
        switch (msgType) {
            case MessageTypeConstrant.MESSAGE_TEXT:
                responseMes = EventHandler.textEvent(map, fromUserName, toUserName);
                break;
            case MessageTypeConstrant.MESSAGE_IMAGE:
                responseMes = EventHandler.imgEvent(map, fromUserName, toUserName);
                break;
            case MessageTypeConstrant.MESSAGE_VOICE:
                responseMes = EventHandler.voiceEvent(map, fromUserName, toUserName);
                break;
            case MessageTypeConstrant.MESSAGE_EVENT:
                responseMes = EventHandler.mssageEvent(map, fromUserName, toUserName);
                break;
            case MessageTypeConstrant.MESSAGE_VIDEO:
                responseMes = EventHandler.mssageEvent(map, fromUserName, toUserName);
                break;
            default:
                responseMes = "";
        }

        if ("".equals(responseMes)) {
            responseMes = String.format("<xml>"
                            + "<ToUserName>%s</ToUserName>"
                            + "<FromUserName>%s</FromUserName>"
                            + "<CreateTime>%s</CreateTime>"
                            + "<MsgType>text</MsgType>"
                            + "<Content>收到消息：默认处理</Content>"
                            + "</xml>"
                    , fromUserName, toUserName, System.currentTimeMillis());
        }
        logger.info("res: {}", responseMes);
        return responseMes;
    }
}
