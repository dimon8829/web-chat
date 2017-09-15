package com.dihri.web.chat.repository;

import com.dihri.web.chat.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class MessageRepositoryImpl implements MessageRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Message> findMessagesForChatRoom(int idChatRoom, int offset, int count) {
        return entityManager.createQuery("select m from Message m where m.chatRoom.id=:idChatRoom order by m.dateCreate DESC")
                .setParameter("idChatRoom",idChatRoom)
                .setMaxResults(count)
                .setFirstResult(offset)
                .getResultList();
    }


    @Override
    public long findNumberMessagesForChatRoom(int idChatRoom) {
        return (long)entityManager.createQuery("select count(m.id) from Message m where m.chatRoom.id=:idChatRoom")
                .setParameter("idChatRoom",idChatRoom)
                .getSingleResult();
    }
}
