package com.dihri.web.chat.model;



import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Entity for Messages
 */
@Entity
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false)
    private long id;
    @Column(name = "message",nullable = false,columnDefinition = "TEXT")
    private String message;
    @Column(name = "date_create", nullable = false)
    private Date dateCreate=new Date();
    @ManyToOne
    @JoinColumn(name = "id_chat_room",nullable = false)
    private ChatRoom chatRoom;
    @ManyToOne
    @JoinColumn(name = "id_user",nullable = false)
    private User user;
    @OneToMany(mappedBy = "message")
    private Set<ReadMessage> readMessages;

    public Message(String message, ChatRoom chatRoom, User user) {
        this.message = message;
        this.chatRoom = chatRoom;
        this.user = user;
    }
}
