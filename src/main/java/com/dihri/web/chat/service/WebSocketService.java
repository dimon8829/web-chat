package com.dihri.web.chat.service;

/**
 * Service for notification users
 */
public interface WebSocketService {
    void newMessage(long idMessage);
    void newRoom(int idRoom);
}
