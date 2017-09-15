package com.dihri.web.chat.repository;

import com.dihri.web.chat.model.ReadMessage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository for ReadMessage
 */
public interface ReadMessageRepository extends CrudRepository<ReadMessage,Long> {
    @Query("select r.message.id from ReadMessage r where r.user.id=:idUser and r.message.chatRoom.id in (:listIdChatRoom)")
    List<Long> findListIdMessagesByUserAndListIdChatRooms(@Param("idUser") int idUser, @Param("listIdChatRoom") List<Integer> listIdChatRoom);
}
