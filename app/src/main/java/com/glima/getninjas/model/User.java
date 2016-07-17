package com.glima.getninjas.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustavo on 17/07/16.
 */
public class User {

    private String name;
    private String email;
    private List<String> phones = new ArrayList<>();

    public User(String name) {
        this.name = name;
    }

    public User(String name, String email, List<String> phones) {
        this(name);
        this.email = email;
        this.phones.addAll(phones);
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

    protected void updateInfo(String email, List<String> phones) {
        setEmail(email);
        setPhones(phones);
    }

    private void setEmail(String email) {
        this.email = email;
    }

    private void setPhones(List<String> phones) {
        this.phones.addAll(phones);
    }
}
