package com.dihri.web.chat.json;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Json representation for message
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageJson {
    private long id;
    private String message;
    private int idChatRoom;
    private String author;
    private Date dateCreate;
}
