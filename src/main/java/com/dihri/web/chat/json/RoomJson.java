package com.dihri.web.chat.json;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * json representation for chat room
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomJson {
    private int id;
    private String name;
    private long numberUnreadMessages;

    public RoomJson(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
