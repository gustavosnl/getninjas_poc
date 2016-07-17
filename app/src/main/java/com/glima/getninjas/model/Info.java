package com.glima.getninjas.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustavo on 17/07/16.
 */
public class Info {

    private final Integer SINGLE_CONTENT = 1;

    private String label;
    private List<String> content = new ArrayList<>();

    public Info(String label, List<String> content) {
        this.label = label;
        this.content.addAll(content);
    }

    public String getLabel() {
        return label;
    }

    public List<String> getContent() {
        return content;
    }

    public Boolean hasSingleContent() {
        return SINGLE_CONTENT.equals(content.size());
    }
}
