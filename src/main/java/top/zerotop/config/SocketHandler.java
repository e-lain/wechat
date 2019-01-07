package top.zerotop.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jupeng.wang on 2018/9/11.
 */
public class SocketHandler extends TextWebSocketHandler {
    private final static Logger logger = LoggerFactory.getLogger(TextWebSocketHandler.class);

    public final static List<WebSocketSession> socketSession = Collections.synchronizedList(new ArrayList<>());

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info("get message "+message.getPayload());
        for (WebSocketSession websession : socketSession) {
            websession.sendMessage(new TextMessage("收到新消息"+message.getPayload()));
        }

        super.handleTextMessage(session, message);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        if (session.isOpen()) {
            socketSession.add(session);
        }
        session.sendMessage(new TextMessage("id 为 "+ session.getId() + " 用户建立连接"));
        super.afterConnectionEstablished(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        socketSession.remove(session);
        super.afterConnectionClosed(session, status);
    }
}

