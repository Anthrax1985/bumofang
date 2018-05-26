package com.websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;

public class WebsocketEndPoint extends TextWebSocketHandler {

    private static final ArrayList<WebSocketSession> engineList = new ArrayList<WebSocketSession>();

    // 连接 就绪时
    @Override
    public void afterConnectionEstablished(WebSocketSession session)
            throws Exception {
//        users.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session,
                                     TextMessage message) throws Exception {
        super.handleTextMessage(session, message);

        String msg = message.getPayload();
        if(msg.equals("IS_ENGINE")){
            engineList.add(session);
            TextMessage returnMessage = new TextMessage(" ADD ENGINE SUCCESS! ");
            session.sendMessage(returnMessage);
        }else if(msg.startsWith("PAD_SENDTO_ENGINE")){
            TextMessage pad_msg = new TextMessage(msg.substring(18));
            sendMsgToAllEngines(pad_msg);
        }
    }

    // 给所有用户发送 信息
    public void sendMsgToAllEngines(WebSocketMessage<?> message) throws Exception{
        for (WebSocketSession engineSession : engineList) {
            engineSession.sendMessage(message);
        }
    }

    // 关闭 连接时
    @Override
    public void afterConnectionClosed(WebSocketSession session,
                                      CloseStatus closeStatus) throws Exception {
        engineList.remove(session);
    }

}