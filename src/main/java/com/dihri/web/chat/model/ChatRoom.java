package com.dihri.web.chat.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Entity for chats
 */
@Entity
@Table(name = "chat_rooms")
@Getter
@Setter
@NoArgsConstructor
public class ChatRoom {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "date_create", nullable = false)
    private Date dateCreate=new Date();
    @OneToMany(mappedBy = "chatRoom")
    private Set<Message> messages;

    public ChatRoom(String name) {
        this.name = name;
    }

    public ChatRoom(int id) {
        this.id = id;
    }
}
