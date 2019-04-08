package com.ttn.linkSharing.co;

import com.ttn.linkSharing.enums.Visibility;

public class TopicCo {
    String topicName;
    Visibility visibility;

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    @Override
    public String toString() {
        return "TopicCo{" +
                "topicName='" + topicName + '\'' +
                ", visibility=" + visibility +
                '}';
    }
}
