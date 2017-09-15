package com.dihri.web.chat.json;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Container for pagination response
 */
@Getter
@Setter
@Builder
public class ResponseItems<T> {
    private long count;
    private List<T> items;
}
