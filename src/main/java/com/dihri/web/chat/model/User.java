package com.dihri.web.chat.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Entity for Users
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "login", nullable = false,unique = true)
    private String login;
    @Column(name = "password", nullable = false)
    private String password;
    @OneToMany(mappedBy = "user")
    private Set<Message> messages;
    @OneToMany(mappedBy = "user")
    private Set<ReadMessage> readMessages;

    public User(int id) {
        this.id=id;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
