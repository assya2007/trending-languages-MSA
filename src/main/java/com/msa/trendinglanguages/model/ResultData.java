package com.msa.trendinglanguages.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class ResultData {

    private String language ;

    private int repoCount ;

    private List<Item> repoList ;


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getRepoCount() {
        return repoCount;
    }

    public void setRepoCount(int repoCount) {
        this.repoCount = repoCount;
    }

    public List<Item> getRepoList() {
        return repoList;
    }

     public void setRepoList(List<Item> repoList) {
        this.repoList = repoList;
    }
}
