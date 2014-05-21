package com.zchen;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * @author Zhouce Chen
 * @version May 19, 2014
 */

@Endpoint
public class UserEndpoint {

    @PayloadRoot(localPart = "user")
    @ResponsePayload
    public User handle(@RequestPayload User user) throws Exception {
        user.setId(2);
        user.setName("Hello Spring!");
        return user;
    }

}
