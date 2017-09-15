package com.dihri.web.chat.service;

import com.dihri.web.chat.json.MessageJson;
import com.dihri.web.chat.json.ResponseItems;
import com.dihri.web.chat.json.RoomJson;
import com.dihri.web.chat.model.ChatRoom;
import com.dihri.web.chat.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service for working with chat
 */
public interface ChatService {
    /**
     * Create new chat room
     * @param name name for chat room
     * @return id ChatRoom
     */
    int addChatRoom(String name);

    /**
     * Get chat rooms
     * @param offset offset
     * @param count number of room requested
     * @return count total rooms and list rooms
     */
    ResponseItems<RoomJson> getChatRooms(int offset, int count);

    /**
     * Get chat room by id
     * @param idChatRoom id ChatRoom
     * @return count total rooms and list rooms
     */
    ResponseItems<RoomJson> getChatRooms(int idChatRoom);

    /**
     * Create new message
     * @param idChatRoom id ChatRoom
     * @param message message
     * @return id Message
     */
    long addMessage(int idChatRoom,String message);

    /**
     * Get messages
     * @param idChatRoom id ChatRoom
     * @param offset offset
     * @param count number of message requested
     * @return count total messages and list messages
     */
    ResponseItems<MessageJson> getMessages(int idChatRoom, int offset, int count);

    /**
     * Get json message by id
     * @param idMessage id messages
     * @return
     */
    ResponseItems<MessageJson> getMessages(long idMessage);

    /**
     * Read all messages from chat room
     * @param idUser id user
     * @param idChatRoom id ChatRoom
     */
    void readMessages(int idUser,int idChatRoom);
}
