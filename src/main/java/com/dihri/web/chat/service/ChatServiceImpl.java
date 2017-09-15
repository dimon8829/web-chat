package com.dihri.web.chat.service;

import com.dihri.web.chat.json.MessageJson;
import com.dihri.web.chat.json.ResponseItems;
import com.dihri.web.chat.json.RoomJson;
import com.dihri.web.chat.model.ChatRoom;
import com.dihri.web.chat.model.Message;
import com.dihri.web.chat.model.ReadMessage;
import com.dihri.web.chat.model.User;
import com.dihri.web.chat.repository.ChatRoomRepository;
import com.dihri.web.chat.repository.MessageRepository;
import com.dihri.web.chat.repository.ReadMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.websocket.server.ServerEndpoint;
import java.util.*;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ReadMessageRepository readMessageRepository;

    public static final String READ_MESSAGE_EXECUTOR="ReadMessageExecutor";

    @Override
    public int addChatRoom(String name) {
        ChatRoom chatRoom = new ChatRoom(name);
        return chatRoomRepository.save(chatRoom).getId();
    }

    @Override
    public ResponseItems<RoomJson> getChatRooms(int offset, int count) {
        List<ChatRoom> chatRooms = chatRoomRepository.findChatRooms(offset, count);
        List<Integer> listIdChatRooms = new ArrayList<>(count);
        chatRooms.forEach(item->listIdChatRooms.add(item.getId()));
        User user = userService.getAuthorizeUser();
        //calculate number not read message for every rooms
        Map<Integer,Long> mapNotReadMessage = getMapNotReadMessages(user.getId(),listIdChatRooms);
        //convert entity room to json representation
        List<RoomJson> roomJsons = new ArrayList<>(count);
        chatRooms.forEach(item->{
            int idChatRoom = item.getId();
            long numberNotReadMessages = 0;
            if(mapNotReadMessage.containsKey(idChatRoom)) numberNotReadMessages = mapNotReadMessage.get(idChatRoom);
            RoomJson roomJson = new RoomJson(item.getId(),item.getName(),numberNotReadMessages);
            roomJsons.add(roomJson);
        });
        long numberAllRooms = chatRoomRepository.findNumberChatRooms();
        return ResponseItems.<RoomJson>builder().items(roomJsons).count(numberAllRooms).build();
    }


    @Override
    public ResponseItems<RoomJson> getChatRooms(int idChatRoom) {
        ChatRoom chatRoom = chatRoomRepository.findOne(idChatRoom);
        RoomJson roomJson = new RoomJson(chatRoom.getId(),chatRoom.getName());
        return ResponseItems.<RoomJson>builder().count(1).items(Arrays.asList(roomJson)).build();
    }

    /**
     * Get map with not read messages for user
     * @param idUser id user
     * @param listIdChatRooms list with id ChatRoom
     * @return map: key - id ChatRoom, value - number not read messages
     */
    private Map<Integer,Long> getMapNotReadMessages(int idUser, List<Integer> listIdChatRooms) {
        if(listIdChatRooms==null || listIdChatRooms.size()==0) return new HashMap<>();
        List<Long> listIdReadMessages = readMessageRepository.findListIdMessagesByUserAndListIdChatRooms(idUser, listIdChatRooms);
        List<Message> listNotReadMessages;
        if(listIdReadMessages.size()==0) listNotReadMessages = messageRepository.findByListIdChatRoom(listIdChatRooms);
        else listNotReadMessages = messageRepository.findByListIdChatRoomExcludeListIdMessages(listIdChatRooms,listIdReadMessages);
        Map<Integer,Long> mapNotReadMessages = new HashMap<>();
        listNotReadMessages.forEach(item->{
            int idChatRoom = item.getChatRoom().getId();
            long numberNotReadMessagesInRoom=1;
            if(mapNotReadMessages.containsKey(idChatRoom)) numberNotReadMessagesInRoom=mapNotReadMessages.get(idChatRoom)+1;
            mapNotReadMessages.put(idChatRoom,numberNotReadMessagesInRoom);
        });
        return mapNotReadMessages;
    }

    @Override
    public long addMessage(int idChatRoom,String message) {
        User authorizeUser = userService.getAuthorizeUser();
        Message messageEntity = new Message(message,new ChatRoom(idChatRoom),authorizeUser);
        Message messageSave = messageRepository.save(messageEntity);
        ReadMessage readMessage = new ReadMessage(authorizeUser,messageSave);
        readMessageRepository.save(readMessage);
        return messageSave.getId();
    }

    @Override
    public ResponseItems<MessageJson> getMessages(int idChatRoom, int offset, int count) {
        List<Message> messages = messageRepository.findMessagesForChatRoom(idChatRoom, offset, count);
        //convert messages to json representation
        List<MessageJson> messageJsons = new ArrayList<>(count);
        messages.forEach(item->messageJsons.add(new MessageJson(item.getId(),item.getMessage(), item
        .getChatRoom().getId(),item.getUser().getLogin(),item.getDateCreate())));
        long numberAllMessages = messageRepository.findNumberMessagesForChatRoom(idChatRoom);
        return ResponseItems.<MessageJson>builder().items(messageJsons).count(numberAllMessages).build();
    }

    @Override
    public ResponseItems<MessageJson> getMessages(long idMessage) {
        Message message = messageRepository.findOne(idMessage);
        MessageJson messageJson = new MessageJson(message.getId(),message.getMessage(),message.getChatRoom().getId(),message.getUser().getLogin(),message.getDateCreate());
        return ResponseItems.<MessageJson>builder().count(1).items(Arrays.asList(messageJson)).build();
    }

    @Override
    @Async(ChatServiceImpl.READ_MESSAGE_EXECUTOR)
    public void readMessages(int idUser, int idChatRoom) {
        List<Integer> listIdChatRooms = Arrays.asList(idChatRoom);
        List<Long> listIdReadMessages = readMessageRepository.findListIdMessagesByUserAndListIdChatRooms(idUser, listIdChatRooms);
        List<Message> listNotReadMessages;
        if(listIdReadMessages.size()==0) listNotReadMessages = messageRepository.findByListIdChatRoom(listIdChatRooms);
        else listNotReadMessages = messageRepository.findByListIdChatRoomExcludeListIdMessages(listIdChatRooms,listIdReadMessages);
        User user = new User(idUser);
        listNotReadMessages.forEach(item->{
            ReadMessage readMessage = new ReadMessage(user,item);
            readMessageRepository.save(readMessage);
        });
    }
}
