package com.dihri.web.chat.repository;

import com.dihri.web.chat.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Custom repository for Messages
 */
public interface MessageRepositoryCustom {
    List<Message> findMessagesForChatRoom(int idChatRoom, int offset, int count);
    long findNumberMessagesForChatRoom(int idChatRoom);
}
