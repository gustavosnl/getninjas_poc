package com.glima.getninjas.model;

/**
 * Created by gustavo on 20/07/16.
 */
public class JobInfo {
    private String label;
    private String value;

    public JobInfo(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }
}
