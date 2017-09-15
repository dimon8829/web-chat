package com.dihri.web.chat.controller;

import com.dihri.web.chat.json.*;
import com.dihri.web.chat.model.ChatRoom;
import com.dihri.web.chat.model.Message;
import com.dihri.web.chat.model.User;
import com.dihri.web.chat.service.ChatService;
import com.dihri.web.chat.service.UserService;
import com.dihri.web.chat.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * Creating rooms, sending and receiving messages
 */
@RestController
public class ChatControllerApi {

    @Autowired
    private ChatService chatService;
    @Autowired
    private WebSocketService webSocketService;
    @Autowired
    private UserService userService;

    @PostMapping(value = "/room")
    public Response addChatRoom(@RequestParam String name) {
        int idChatRoom = chatService.addChatRoom(name);
        webSocketService.newRoom(idChatRoom);
        return Response.success();
    }

    @GetMapping(value = "/room")
    public ResponseItems<RoomJson> getChatRooms(@RequestParam(required = false,defaultValue = "0") int offset, @RequestParam(required = false,defaultValue = "10") int count) {
        return chatService.getChatRooms(offset, count);
    }

    @PostMapping(value = "/message")
    public Response addMessage(@RequestParam String message, @RequestParam int id_chat_room) {
        long idMessage = chatService.addMessage(id_chat_room,message);
        webSocketService.newMessage(idMessage);
        return Response.success();
    }

    @GetMapping(value = "/message")
    public ResponseItems<MessageJson> getMessages(@RequestParam int id_chat_room, @RequestParam(required = false,defaultValue = "0") int offset, @RequestParam(required = false,defaultValue = "10") int count) {
        ResponseItems<MessageJson> data = chatService.getMessages(id_chat_room,offset,count);
        User user = userService.getAuthorizeUser();
        chatService.readMessages(user.getId(),id_chat_room);
        return data;
    }

}
