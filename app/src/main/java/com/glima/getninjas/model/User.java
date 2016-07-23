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

    public String getRawMainPhone() {
        String rawPhone = phones.get(0).replace("(", "").replace(")", "").replace("-", "").replace(" ", "");
        StringBuilder sb = new StringBuilder();
        return sb.append("+55").append(rawPhone).toString();
    }

}
