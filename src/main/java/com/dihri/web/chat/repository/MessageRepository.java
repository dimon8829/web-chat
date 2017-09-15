package com.dihri.web.chat.repository;

import com.dihri.web.chat.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository for message
 */
public interface MessageRepository extends CrudRepository<Message,Long>,PagingAndSortingRepository<Message,Long>,MessageRepositoryCustom {
    @Query("select m from Message m where m.chatRoom.id in (:listIdRoom) and m.id not in (:listIdExcludeMessages)")
    List<Message> findByListIdChatRoomExcludeListIdMessages(@Param("listIdRoom") List<Integer> listIdRoom, @Param("listIdExcludeMessages") List<Long> listIdExcludeMessages);
    @Query("select m from Message m where m.chatRoom.id in (:listIdRoom)")
    List<Message> findByListIdChatRoom(@Param("listIdRoom") List<Integer> listIdRoom);
}
