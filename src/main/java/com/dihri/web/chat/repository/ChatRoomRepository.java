package com.dihri.web.chat.repository;

import com.dihri.web.chat.model.ChatRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * repository for ChatRoom entity
 */
public interface ChatRoomRepository extends CrudRepository<ChatRoom,Integer>,ChatRoomRepositoryCustom{

}
