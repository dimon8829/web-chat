package com.dihri.web.chat.repository;

import com.dihri.web.chat.model.ChatRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;


@Repository
public class ChatRoomRepositoryImpl implements ChatRoomRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<ChatRoom> findChatRooms(int offset, int count) {
        return entityManager.createQuery("select c from ChatRoom c order by c.dateCreate DESC")
                .setMaxResults(count)
                .setFirstResult(offset)
                .getResultList();
    }

    @Override
    public long findNumberChatRooms() {
        return (long)entityManager.createQuery("select count(c.id) from ChatRoom c")
                .getSingleResult();
    }
}
