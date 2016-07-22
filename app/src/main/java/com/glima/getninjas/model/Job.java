package com.glima.getninjas.model;


import java.util.ArrayList;
import java.util.List;

import static com.glima.getninjas.model.JobCondition.OFFER;

/**
 * Created by gustavo on 16/07/16.
 */
public class Job {

    private String id;
    private JobCondition condition;
    private Integer leadPrice;
    private String leadId;
    private String title;
    private Boolean isRead;
    private String creationDate;
    private User requestingUser;
    private Address requestingAddress;
    private Integer requestingDistance;
    private List<Info> jobInfo = new ArrayList<>();

    public Job(String id, JobCondition condition, String title, Boolean isRead, String creationDate, User User, Address Address, List<Info> jobInfo, Integer distance) {
        this.id = id;
        this.condition = condition;
        this.title = title;
        this.isRead = isRead;
        this.creationDate = creationDate;
        this.requestingUser = User;
        this.requestingAddress = Address;
        this.jobInfo.addAll(jobInfo);
        this.requestingDistance = distance;
    }

    public Job(Integer distance, Integer leadPrice, String title, List<Info> info, User user, Address address, String leadId) {
        this.requestingDistance = distance;
        this.leadPrice = leadPrice;
        this.title = title;
        this.jobInfo.addAll(info);
        this.requestingUser = user;
        this.requestingAddress = address;
        this.leadId = leadId;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCreationDate() {
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

    public JobCondition getCondition() {
        return condition;
    }

    public List<Info> getJobInfo() {
        return jobInfo;
    }

    public Integer getLeadPrice() {
        return leadPrice;
    }

    public String getLeadId() {
        return leadId;
    }

    public Boolean isOffer() {
        return !"".equals(leadId) || OFFER.equals(condition);
    }
}
