package com.kesteli.filip.lauzhack.data;

import java.util.List;
import java.util.Map;

/**
 * Created by Valemate on 12.11.2017..
 */

public class Texts {

    private String content;
    private int label;
    private Map<String, Integer> keywordsMap;

    public Texts() {
    }

    public Texts(String content, int label, Map<String, Integer> keywordsMap) {
        this.content = content;
        this.label = label;
        this.keywordsMap = keywordsMap;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public Map<String, Integer> getKeywordsMap() {
        return keywordsMap;
    }

    public void setKeywordsMap(Map<String, Integer> keywordsMap) {
        this.keywordsMap = keywordsMap;
    }
}
