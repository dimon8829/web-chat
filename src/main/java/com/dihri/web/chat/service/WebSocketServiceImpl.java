package com.dihri.web.chat.service;

import com.dihri.web.chat.json.MessageJson;
import com.dihri.web.chat.json.ResponseItems;
import com.dihri.web.chat.json.RoomJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
@Async(WebSocketServiceImpl.WEB_SOCKET_EXECUTOR)
public class WebSocketServiceImpl implements WebSocketService {

    @Autowired
    private ChatService chatService;
    @Autowired
    private SimpMessagingTemplate template;

    public static final String WEB_SOCKET_EXECUTOR="WebSocketExecutor";



    @Override
    public void newMessage(long idMessage) {
        ResponseItems<MessageJson> data = chatService.getMessages(idMessage);
        template.convertAndSend("/topic/message",data);
    }

    @Override
    public void newRoom(int idRoom) {
        ResponseItems<RoomJson> data = chatService.getChatRooms(idRoom);
        template.convertAndSend("/topic/room",data);
    }
}
