package com.glima.getninjas.model;

import java.util.Date;

/**
 * Created by gustavo on 16/07/16.
 */
public class Offer {

    private String id;
    private String title;
    private Boolean isRead;
    private Date creationDate;
    private User requestingUser;
    private Address requestingAddress;
    private Integer requestingDistance;


    public Offer(String id, String title, Boolean isRead, Date creationDate, User User, Address Address, Integer distance) {
        this.id = id;
        this.title = title;
        this.isRead = isRead;
        this.creationDate = creationDate;
        this.requestingUser = User;
        this.requestingAddress = Address;
        this.requestingDistance = distance;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public User getRequestingUser() {
        return requestingUser;
    }

    public Address getRequestingAddress() {
        return requestingAddress;
    }

    public Boolean isRead() {
        return isRead;
    }

    public Integer getRequestingDistance() {
        return requestingDistance;
    }
}
