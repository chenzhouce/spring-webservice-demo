package com.zchen;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

/**
 * @author Zhouce Chen
 * @version May 21, 2014
 */
public class Test {
    public static void main(String[] args) {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(User.class);
        WebServiceTemplate template = new WebServiceTemplate(marshaller);
        User user = new User(1, "chenzhouce");
        User returnUser = (User) template.marshalSendAndReceive("http://127.0.0.1:8080/userService/user.wsdl", user);
        System.out.println(returnUser);
    }
}
