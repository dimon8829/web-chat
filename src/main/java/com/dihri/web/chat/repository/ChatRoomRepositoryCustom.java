package com.dihri.web.chat.repository;

import com.dihri.web.chat.model.ChatRoom;

import java.util.List;

/**
 * Custom repository for ChatRoom
 */
public interface ChatRoomRepositoryCustom {
    List<ChatRoom> findChatRooms(int offset,int count);
    long findNumberChatRooms();
}
