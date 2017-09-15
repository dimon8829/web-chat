package com.dihri.web.chat.json;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sun.misc.Service;

/**
 * response
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private StatusResponse status;
    private Object data;

    public Response(StatusResponse status) {
        this.status = status;
    }

    public static Response success() {
        return new Response(StatusResponse.SUCCES);
    }

    public static Response success(Object data) {
        return new Response(StatusResponse.SUCCES,data);
    }
}
