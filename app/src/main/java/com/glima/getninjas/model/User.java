package com.glima.getninjas.model;

import java.util.List;

/**
 * Created by gustavo on 17/07/16.
 */
public class User {

    private String name;
    private String email;
    private List<String> phones;

    public User(String name) {
        this.name = name;
    }

    public User(String name, String email, List<String> phones) {
        this(name);
        this.email = email;
        this.phones = phones;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getPhones() {
        return phones;
    }
}
