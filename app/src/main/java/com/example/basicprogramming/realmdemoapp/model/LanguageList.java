package com.example.basicprogramming.realmdemoapp.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class LanguageList extends RealmObject {

    @PrimaryKey
    private String id;
    @Required
    private String name;
    @Required
    private String developed;
    @Required
    private String description;
    @Required
    private String latest;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeveloped() {
        return developed;
    }

    public void setDeveloped(String developed) {
        this.developed = developed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLatest() {
        return latest;
    }

    public void setLatest(String latest) {
        this.latest = latest;
    }
}
