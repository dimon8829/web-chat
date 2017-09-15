package com.dihri.web.chat.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Info for read message by user
 */
@Entity
@Table(name = "message_read")
@Getter
@Setter
@NoArgsConstructor
public class ReadMessage {
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false)
    private long id;
    @ManyToOne
    @JoinColumn(name = "id_user",nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "id_message", nullable = false)
    private Message message;
    @Column(name = "date_create", nullable = false)
    private Date dateCreate = new Date();

    public ReadMessage(User user, Message message) {
        this.user = user;
        this.message = message;
    }
}
