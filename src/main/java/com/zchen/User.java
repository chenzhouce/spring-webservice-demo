package com.zchen;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Zhouce Chen
 * @version May 19, 2014
 */
@XmlRootElement(name = "user")
public class User {
    private int id;

    private String name;

    public User() {
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Id: " + id + ", Name: " + name;
    }
}
