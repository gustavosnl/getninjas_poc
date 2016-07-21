package com.glima.getninjas.model;

/**
 * Created by gustavo on 20/07/16.
 */
public enum JobCondition {

    OFFER("offer"),
    LEAD("lead");

    private String condition;

    JobCondition(String condition) {
        this.condition = condition;
    }

}


