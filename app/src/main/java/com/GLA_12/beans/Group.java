package com.GLA_12.beans;

public class Group {
    private String description;
    private int episode_count;
    private int group_count;
    private Group[] groups;
    private String id;
    private String name;
    private Network[] network;
    private int type;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEpisode_count() {
        return episode_count;
    }

    public void setEpisode_count(int episode_count) {
        this.episode_count = episode_count;
    }

    public int getGroup_count() {
        return group_count;
    }

    public void setGroup_count(int group_count) {
        this.group_count = group_count;
    }

    public Group[] getGroups() {
        return groups;
    }

    public void setGroups(Group[] groups) {
        this.groups = groups;
    }

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

    public Network[] getNetwork() {
        return network;
    }

    public void setNetwork(Network[] network) {
        this.network = network;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}