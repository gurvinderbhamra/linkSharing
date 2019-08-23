package com.ttn.linkSharing.dto;

import com.ttn.linkSharing.entities.Topic;

import java.util.ArrayList;
import java.util.List;

public class TrendingTopicsDTO {

    List<Topic> trendingTopics = new ArrayList<>();

    public List<Topic> getTrendingTopics() {
        return trendingTopics;
    }
}
